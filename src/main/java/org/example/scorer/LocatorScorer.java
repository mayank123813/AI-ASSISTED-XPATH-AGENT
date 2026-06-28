package org.example.scorer;

public class LocatorScorer {

    public int calculateScore(String xpath) {

        int baseScore = 0;
        int bonus = 0;

        // Highest priority attribute
        if (xpath.contains("@id")) {
            baseScore = Math.max(baseScore, 100);
        }

        if (xpath.contains("@data-id")) {
            if (baseScore == 0)
                baseScore = 95;
            else
                bonus += 5;
        }

        if (xpath.contains("@name")) {
            if (baseScore == 0)
                baseScore = 90;
            else
                bonus += 5;
        }

        if (xpath.contains("@placeholder")) {
            if (baseScore == 0)
                baseScore = 70;
            else
                bonus += 5;
        }

        if (xpath.contains("text()")) {
            if (baseScore == 0)
                baseScore = 60;
            else
                bonus += 5;
        }

        if (xpath.contains("contains(@class")) {
            if (baseScore == 0)
                baseScore = 40;
            else
                bonus += 5;
        }

        // Indexed XPath is least preferred
        if (xpath.matches("^\\(.*\\)\\[\\d+\\]$")) {
            baseScore -= 20;
        }

        return baseScore + bonus;
    }
}