package org.example;

import org.example.generator.XPathGenerator;
import org.example.model.ElementInfo;
import org.example.validator.XpathValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            XPathGenerator generator = new XPathGenerator();

            driver.get("https://selectorshub.com/xpath-practice-page/");

            String html = driver.getPageSource();

            Document doc = Jsoup.parse(html);

            Elements inputs = doc.select("input");

            for (Element input : inputs) {

                ElementInfo elementInfo = new ElementInfo(
                        input.tagName(),
                        input.id(),
                        input.attr("name"),
                        input.text(),
                        input.attr("placeholder"),
                        input.attr("data-id"),
                        input.className()
                );

                List<String> locators =
                        generator.generate(elementInfo);

                String finalXpath = null;

                for (String xpath : locators) {

                    boolean unique =
                            XpathValidator.isUnique(driver, xpath);

                    System.out.println("Trying -> " + xpath);
                    System.out.println("Unique -> " + unique);

                    if (unique) {
                        finalXpath = xpath;
                        break;
                    }
                }

                System.out.println("Element -> " + elementInfo);
                System.out.println("Final XPath -> " + finalXpath);
                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}