package com.mbti.server.mbti.dto;  // 적절한 패키지 경로에 배치하세요

public class MbtiRequest {
    private String mbti;       // MBTI 유형
    private String situation;  // 상황 (예: 혼자, 커플, 가족)

    // 기본 생성자
    public MbtiRequest() {}

    // getter 및 setter 메서드
    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
