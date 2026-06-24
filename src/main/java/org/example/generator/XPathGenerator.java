package org.example.generator;

import org.example.model.ElementInfo;

import java.util.ArrayList;
import java.util.List;

public class XPathGenerator {
    public List<String> generate(ElementInfo element){

        List<String> locators = new ArrayList<>();
        //data-id
        if(isValid(element.getDataId())){
            locators.add("//*[@data-id='"+element.getDataId()+"']");
        }
        if(isValid(element.getId()) && !isDynamic(element.getId())){
             locators.add("//*[@id='"+element.getId()+"']");
        }

        if(isValid(element.getId()) && isDynamic(element.getId())){
            String stablePart=getStablePart(element.getId());
            if(!stablePart.isEmpty()){
                locators.add("//*[contains(@id,'"+stablePart+"')]");
            }
        }

        if(isValid(element.getName())){
            locators.add("//*[@name='"+element.getName()+"']");
        }

        //placeholder
        if(isValid(element.getPlaceholder())){
            locators.add("//*[@placeholder='"+element.getPlaceholder()+"']");
        }
        // className
        if(isValid(element.getClassName())){
            locators.add("//*[contains(@class,'"+element.getClassName()+"')]");
        }

        if(element.getText()!=null && !element.getText().isEmpty()){
            locators.add("//"+element.getTagName()+"[contains(text(),'"+element.getText().trim()+"')]");
        }
        else{
            locators.add("//"+element.getTagName());
        }
        return locators;
    }

    private boolean isValid(String value){
        return value!=null && !value.trim().isEmpty();
    }
    private boolean isDynamic(String id){
        return id.matches(".*\\d{3,}.*");
    }
    private String getStablePart(String id){
        return id.replaceAll("\\d+","");
    }
}
