package org.example.scorer;

import java.util.HashMap;
import java.util.Map;

public class LocatorScorer {
    private final Map<String, Integer> attributeScores = new HashMap<>();

    public LocatorScorer() {

        attributeScores.put("id", 100);
        attributeScores.put("data-id", 95);
        attributeScores.put("name", 90);
        attributeScores.put("placeholder", 70);
        attributeScores.put("text", 60);
        attributeScores.put("class", 40);
        attributeScores.put("tag", 10);
    }

    public int calculateScore(String xpath) {

        int score = 0;

        if (xpath.contains("@id")) {
            score += attributeScores.get("id");
        }

        if (xpath.contains("@data-id")) {
            score += attributeScores.get("data-id");
        }

        if (xpath.contains("@name")) {
            score += attributeScores.get("name");
        }

        if (xpath.contains("@placeholder")) {
            score += attributeScores.get("placeholder");
        }

        if (xpath.contains("text()")) {
            score += attributeScores.get("text");
        }

        if (xpath.contains("contains(@class")) {
            score += attributeScores.get("class");
        }

        // Penalize indexed XPath
        if (xpath.matches("^\\(.*\\)\\[\\d+\\]$")) {
            score -= 30;
        }
        return score;
    }
}