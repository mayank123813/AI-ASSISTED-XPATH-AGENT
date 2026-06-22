package org.example;

import org.example.model.ElementInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://selectorshub.com/xpath-practice-page/");
            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);
            Elements  inputs = doc.select("input");

            for(Element input:inputs){
                ElementInfo elementInfo=new ElementInfo(
                        input.tagName(), input.id(), input.attr("name"),input.text()
                );
                System.out.println(elementInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}