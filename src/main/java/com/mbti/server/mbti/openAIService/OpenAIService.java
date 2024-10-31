package com.mbti.server.mbti.openAIService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://api.openai.com/v1/chat/completions"; // 변경된 URL

    private static final int MAX_RETRIES = 5;
    private static final long INITIAL_WAIT_TIME_MS = 1000; // 1초

    public String getRecommendation(String mbti, String situation) {
        String prompt = String.format(
                "The user's MBTI is %s, and the situation is %s. Please recommend several travel destinations and cars suitable for this user. Explain in detail the characteristics and advantages of each recommended destination and car. Please respond in Korean.",
                mbti, situation
        );


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        int attempt = 0;
        long waitTime = INITIAL_WAIT_TIME_MS;

        while (attempt < MAX_RETRIES) {
            try {
                // 요청 본문 생성
                JSONObject requestBody = new JSONObject();
                requestBody.put("model", "gpt-3.5-turbo");
                requestBody.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", prompt)));
                requestBody.put("temperature", 0.8);
                requestBody.put("max_tokens", 750);

                // API 호출
                HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

                String responseBody = response.getBody();
                System.out.println("API 응답: " + responseBody); // 추가된 로깅

                if (responseBody.startsWith("{")) { // JSON 시작 확인
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    if (jsonResponse.has("choices")) {
                        JSONArray choices = jsonResponse.getJSONArray("choices");
                        if (choices.length() > 0) {
                            String text = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();

                            // JSON 응답 생성
                            JSONObject jsonResponseObject = new JSONObject();
                            jsonResponseObject.put("recommendation", text);

                            return jsonResponseObject.toString(); // 성공적으로 추천을 생성한 경우
                        } else {
                            return new JSONObject().put("error", "추천할 내용이 없습니다.").toString();
                        }
                    } else {
                        return new JSONObject().put("error", "API 응답에 선택지가 없습니다: " + responseBody).toString();
                    }
                } else {
                    return new JSONObject().put("error", "유효하지 않은 응답 형식입니다: " + responseBody).toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject().put("error", "추천 요청 생성 중 오류가 발생했습니다.").toString();
            } catch (Exception e) {
                e.printStackTrace();
                attempt++;

                if (attempt < MAX_RETRIES) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    waitTime *= 2; // 대기 시간을 지수적으로 증가
                } else {
                    System.err.println("최대 재시도 횟수 초과");
                    return new JSONObject().put("error", "추천을 생성하는 중 문제가 발생했습니다.").toString();
                }
            }
        }

        return new JSONObject().put("error", "추천을 생성하는 중 문제가 발생했습니다.").toString();
    }
}
