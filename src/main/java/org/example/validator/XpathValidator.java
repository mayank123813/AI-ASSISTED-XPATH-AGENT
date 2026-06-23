package org.example.validator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class XpathValidator {
    public static boolean isUnique(WebDriver driver,String xpath){
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        return list.size()==1;
    }
}
