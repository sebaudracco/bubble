package com.appsgeyser.sdk.ads.nativeAd;

public class InmobiNativeIconModel {
    private float aspectRatio;
    private int height;
    private String url;
    private int width;

    public InmobiNativeIconModel(int width, int height, String url, float aspectRatio) {
        this.width = width;
        this.height = height;
        this.url = url;
        this.aspectRatio = aspectRatio;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
