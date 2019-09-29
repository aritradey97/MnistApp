package com.btechviral.mnistapp;

public class Item {
    private String type, definition, example, url;
    public Item(String type, String definition, String example, String url){
        this.type = type;
        this.definition = definition;
        this.example = example;
        this.url = url;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
