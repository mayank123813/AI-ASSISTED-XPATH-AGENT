package org.example.model;

public class ElementInfo {

    private String tagName;
    private String id;
    private String name;
    private String text;
    public ElementInfo(String tagName, String id, String name, String text) {
        this.tagName = tagName;
        this.id = id;
        this.name = name;
        this.text = text;
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
}
