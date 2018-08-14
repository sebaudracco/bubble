package com.mobfox.sdk.nativeads;

public class TextItem {
    String text;
    String type;

    public TextItem(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }
}
