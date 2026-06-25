package org.example.model;

import lombok.Getter;

public class ElementInfo {

    private String tagName;
    private String id;
    private String name;
    private String text;
    private String placeholder;
    private String dataId;
    private String className;
    private int position;
    public ElementInfo(String tagName, String id, String name, String text,String placeholder,
                       String dataId,String className,int position) {
        this.tagName = tagName;
        this.id = id;
        this.name = name;
        this.text = text;
        this.className=className;
        this.dataId=dataId;
        this.placeholder=placeholder;
        this.position=position;
    }

    public String getTagName() {
        return tagName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
    public String getPlaceholder(){
        return placeholder;
    }
    public String getDataId(){
        return dataId;
    }
    public String getClassName(){
        return className;
    }
    public int getPosition(){
        return position;
    }
    @Override
    public String toString() {
        return "ElementInfo{" +
                "tagName='" + tagName + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", dataId='" + dataId + '\'' +
                ", className='" + className + '\'' +
                ", position=" + position +
                '}';
    }
}
