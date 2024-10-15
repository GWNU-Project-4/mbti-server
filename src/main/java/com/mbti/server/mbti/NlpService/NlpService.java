package com.mbti.server.mbti.NlpService;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class NlpService {

    private final DocumentCategorizerME categorize;
    private final NameFinderME nameFinder;

    public NlpService() throws Exception {
        // 모델 파일 로드
        InputStream modelInputStream = getClass().getResourceAsStream("/langdetect-183.bin");
        DoccatModel model = new DoccatModel(modelInputStream);
        this.categorize = new DocumentCategorizerME(model);

        // 이름 인식 모델 로드
        InputStream nameModelStream = getClass().getResourceAsStream("/langdetect-183.bin");
        this.nameFinder = new NameFinderME(new TokenNameFinderModel(nameModelStream));
    }

    public String categorizeText(String[] text) {
        // 텍스트 분류 로직 구현
        double[] outcomes = categorize.categorize(text);
        String category = categorize.getBestCategory(outcomes);
        return category;
    }

    public String[] findNames(String text) {
        // 이름 인식 로직 구현
        String[] tokens = text.split(" ");
        Span[] nameSpans = nameFinder.find(tokens);
        String[] names = new String[nameSpans.length];
        for (int i = 0; i < nameSpans.length; i++) {
            names[i] = tokens[nameSpans[i].getStart()];
        }
        return names;
    }
}