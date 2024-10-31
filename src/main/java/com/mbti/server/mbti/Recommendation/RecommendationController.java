package com.mbti.server.mbti.Recommendation;

import com.mbti.server.mbti.dto.MbtiRequest;
import com.mbti.server.mbti.openAIService.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/get")
    public ResponseEntity<String> getRecommendation(@RequestBody MbtiRequest request) {  // @RequestBody를 사용
        String mbti = request.getMbti();
        String situation = request.getSituation();
        String jsonResponse = openAIService.getRecommendation(mbti, situation);
        return ResponseEntity.ok(jsonResponse); // 클라이언트에 JSON 형식으로 응답 반환
    }

}
