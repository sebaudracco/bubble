package com.appsgeyser.sdk.ads.nativeAd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;

public class AppsgeyserNativeAdModel {
    private String actionButtonText;
    private String adDescription;
    private String adIconUrl;
    private String adTitle;
    private String adUrl;
    private String rating;

    public static AppsgeyserNativeAdModel parseFromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return (AppsgeyserNativeAdModel) gson.fromJson(reader, AppsgeyserNativeAdModel.class);
    }

    public AppsgeyserNativeAdModel(String actionButtonText, String adTitle, String adDescription, String adIconUrl, String adUrl, String rating) {
        this.actionButtonText = actionButtonText;
        this.adTitle = adTitle;
        this.adDescription = adDescription;
        this.adIconUrl = adIconUrl;
        this.adUrl = adUrl;
        this.rating = rating;
    }

    public String getActionButtonText() {
        return this.actionButtonText;
    }

    public void setActionButtonText(String actionButtonText) {
        this.actionButtonText = actionButtonText;
    }

    public String getAdTitle() {
        return this.adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdDescription() {
        return this.adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public String getAdIconUrl() {
        return this.adIconUrl;
    }

    public void setAdIconUrl(String adIconUrl) {
        this.adIconUrl = adIconUrl;
    }

    public String getAdUrl() {
        return this.adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
