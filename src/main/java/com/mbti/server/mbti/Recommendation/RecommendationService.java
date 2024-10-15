package com.mbti.server.mbti.Recommendation;

import com.mbti.server.mbti.Recommendation.Recommendation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    public List<Recommendation> getRecommendations(String mbtiType) {
        List<Recommendation> recommendations = new ArrayList<>();

        // MBTI에 따른 추천 로직
        switch (mbtiType) {
            case "INTJ":
                recommendations.add(new Recommendation("car", "Tesla Model S", "지능형 전기차로 혁신적인 기술을 자랑합니다."));
                recommendations.add(new Recommendation("destination", "제주도", "아름다운 자연 경관과 조용한 분위기를 제공합니다."));
                break;

            case "ENFJ":
                recommendations.add(new Recommendation("car", "Honda CR-V", "가족과 친구들과의 여행에 적합한 공간을 제공합니다."));
                recommendations.add(new Recommendation("destination", "파리", "문화와 예술의 중심지로, 다양한 명소를 경험할 수 있습니다."));
                break;

            case "ISTP":
                recommendations.add(new Recommendation("car", "Ford Mustang", "스포츠카의 상징으로, 스릴 넘치는 주행 경험을 제공합니다."));
                recommendations.add(new Recommendation("destination", "알프스", "아웃도어 활동과 아름다운 경관을 제공합니다."));
                break;

            // 다른 MBTI 유형에 대한 추천 추가 가능
            default:
                recommendations.add(new Recommendation("car", "기타 차종", "추천할 차종이 없습니다."));
                recommendations.add(new Recommendation("destination", "기타 여행지", "추천할 여행지가 없습니다."));
                break;
        }

        return recommendations;
    }
}