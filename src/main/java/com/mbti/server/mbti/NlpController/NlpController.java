package com.mbti.server.mbti.NlpController;

import com.mbti.server.mbti.Recommendation.Recommendation;
import com.mbti.server.mbti.NlpService.NlpService;
import com.mbti.server.mbti.Recommendation.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nlp")
public class NlpController {

    private final NlpService nlpService;
    private final RecommendationService recommendationService;

    public NlpController(NlpService nlpService, RecommendationService recommendationService) {
        this.nlpService = nlpService;
        this.recommendationService = recommendationService;
    }

    @PostMapping("/categorize")
    public String categorize(@RequestBody String[] text) {
        return nlpService.categorizeText(text);
    }

    @PostMapping("/find-names")
    public String[] findNames(@RequestBody String text) {
        return nlpService.findNames(text);
    }

    @GetMapping("/recommendations/{mbtiType}")
    public List<Recommendation> getRecommendations(@PathVariable String mbtiType) {
        return recommendationService.getRecommendations(mbtiType);
    }
}