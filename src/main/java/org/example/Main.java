package org.example;

import org.example.generator.XPathGenerator;
import org.example.model.ElementInfo;
import org.example.scorer.LocatorScorer;
import org.example.validator.XpathValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            XPathGenerator generator = new XPathGenerator();
            LocatorScorer scorer=new LocatorScorer();

            driver.get("https://selectorshub.com/xpath-practice-page/");

            String html = driver.getPageSource();

            Document doc = Jsoup.parse(html);

            Elements inputs = doc.select("input");

            Map<String,Integer> occurrenceMap =
                    new HashMap<>();


            for(int i=0;i<inputs.size();i++) {

                Element input = inputs.get(i);
                String key;

                if(!input.attr("name").isEmpty()){

                    key = "NAME:" + input.attr("name");

                }
                else if(!input.id().isEmpty()){

                    key = "ID:" + input.id();

                }
                else if(!input.attr("placeholder").isEmpty()){

                    key = "PLACEHOLDER:" +
                            input.attr("placeholder");

                }
                else{

                    key = "TAG:" + input.tagName();

                }
                int occurrence =
                        occurrenceMap.getOrDefault(
                                key,
                                0
                        ) + 1;
                ElementInfo elementInfo = new ElementInfo(
                        input.tagName(),
                        input.id(),
                        input.attr("name"),
                        input.text(),
                        input.attr("placeholder"),
                        input.attr("data-id"),
                        input.className(),occurrence
                );


                occurrenceMap.put(
                        key,
                        occurrence
                );
                List<String> locators =
                        generator.generate(elementInfo);

                String bestXpath = null;
                int bestScore = -1;

                for (String xpath : locators) {

                    boolean unique =
                            XpathValidator.isUnique(driver, xpath);

                    int count =
                            XpathValidator.getMatchCount(driver, xpath);

                    System.out.println("Trying -> " + xpath);
                    System.out.println("Unique -> " + unique);
                    System.out.println("Count -> " + count);

                    if (unique) {

                        int score =
                                scorer.calculateScore(xpath);

                        System.out.println("Score -> " + score);

                        if (score > bestScore) {

                            bestScore = score;
                            bestXpath = xpath;
                        }
                    }
                }

                if (bestXpath == null) {

                    bestXpath =
                            generator.generateIndexedXpath(elementInfo);

                    System.out.println("Fallback XPath -> " + bestXpath);
                }

                System.out.println("Element -> " + elementInfo);
                System.out.println("Best XPath -> " + bestXpath);
                System.out.println("Best Score -> " + bestScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}