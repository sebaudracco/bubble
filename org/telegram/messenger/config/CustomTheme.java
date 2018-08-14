package org.telegram.messenger.config;

public class CustomTheme {
    private Integer actionBarColor;
    private Integer actionColor;
    private boolean applyTheme;
    private Integer backgroundColor;
    private Integer messageBarColor;
    private String name;
    private Integer receivedColor;
    private Integer sentColor;
    private Integer textColor;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActionBarColor() {
        return this.actionBarColor;
    }

    public void setActionBarColor(Integer actionBarColor) {
        this.actionBarColor = actionBarColor;
    }

    public Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getMessageBarColor() {
        return this.messageBarColor;
    }

    public void setMessageBarColor(Integer messageBarColor) {
        this.messageBarColor = messageBarColor;
    }

    public Integer getReceivedColor() {
        return this.receivedColor;
    }

    public void setReceivedColor(Integer receivedColor) {
        this.receivedColor = receivedColor;
    }

    public Integer getSentColor() {
        return this.sentColor;
    }

    public void setSentColor(Integer sentColor) {
        this.sentColor = sentColor;
    }

    public Integer getTextColor() {
        return this.textColor;
    }

    public void setTextColor(Integer textColor) {
        this.textColor = textColor;
    }

    public Integer getActionColor() {
        return this.actionColor;
    }

    public void setActionColor(Integer actionColor) {
        this.actionColor = actionColor;
    }

    public boolean isApplyTheme() {
        return this.applyTheme;
    }

    public void setApplyTheme(boolean applyTheme) {
        this.applyTheme = applyTheme;
    }
}
