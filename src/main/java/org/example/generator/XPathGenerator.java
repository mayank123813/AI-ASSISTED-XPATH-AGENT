package org.example.generator;

import org.example.model.ElementInfo;

public class XPathGenerator {
    public String generate(ElementInfo element){

        //data-id
        if(isValid(element.getDataId())){
            return "//*[@data-id='"+element.getDataId()+"']";
        }
        if(isValid(element.getId()) && !isDynamic(element.getId())){
            return "//*[@id='"+element.getId()+"']";
        }

        if(isValid(element.getId()) && isDynamic(element.getId())){
            String stablePart=getStablePart(element.getId());
            if(!stablePart.isEmpty()){
                return "//*[contains(@id,'"+stablePart+"')]";
            }
        }

        if(isValid(element.getName())){
            return "//*[@name='"+element.getName()+"']";
        }

        //placeholder
        if(isValid(element.getPlaceholder())){
            return "//*[@placeholder='"+element.getPlaceholder()+"']";
        }
        // className
        if(isValid(element.getClassName())){
            return "//*[contains(@class,'"+element.getClassName()+"')]";
        }

        if(element.getText()!=null && !element.getText().isEmpty()){
            return "//"+element.getTagName()+"[contains(text(),'"+element.getText().trim()+"')]";
        }
        return "//"+element.getTagName();
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
