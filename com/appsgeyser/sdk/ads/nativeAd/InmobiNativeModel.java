package com.appsgeyser.sdk.ads.nativeAd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;

public class InmobiNativeModel {
    private String cta;
    private String description;
    private InmobiNativeIconModel icon;
    private String landingUrl;
    private float rating;
    private String title;

    public static InmobiNativeModel parseFromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return (InmobiNativeModel) gson.fromJson(reader, InmobiNativeModel.class);
    }

    public InmobiNativeModel(String title, String description, InmobiNativeIconModel icon, String landingUrl, String cta, float rating) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.landingUrl = landingUrl;
        this.cta = cta;
        this.rating = rating;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLandingUrl() {
        return this.landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public String getCta() {
        return this.cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public InmobiNativeIconModel getIcon() {
        return this.icon;
    }

    public void setIcon(InmobiNativeIconModel icon) {
        this.icon = icon;
    }
}
