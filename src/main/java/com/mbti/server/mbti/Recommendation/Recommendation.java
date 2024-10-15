package com.mbti.server.mbti.Recommendation;

public class Recommendation {
    private String type;  // 추천 유형: "car" 또는 "destination"
    private String name;  // 추천 이름
    private String description;  // 추천 설명

    public Recommendation(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    // Getter와 Setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
