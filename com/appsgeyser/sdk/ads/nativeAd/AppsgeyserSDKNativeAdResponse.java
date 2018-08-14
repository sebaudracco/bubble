package com.appsgeyser.sdk.ads.nativeAd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.util.List;

public class AppsgeyserSDKNativeAdResponse {
    List<AppsgeyserNativeAdModel> appsgeyserNativeAdModels;

    public static AppsgeyserSDKNativeAdResponse parseFromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return (AppsgeyserSDKNativeAdResponse) gson.fromJson(reader, AppsgeyserSDKNativeAdResponse.class);
    }

    public AppsgeyserSDKNativeAdResponse(List<AppsgeyserNativeAdModel> appsgeyserNativeAdModels) {
        this.appsgeyserNativeAdModels = appsgeyserNativeAdModels;
    }

    public List<AppsgeyserNativeAdModel> getAppsgeyserNativeAdModels() {
        return this.appsgeyserNativeAdModels;
    }

    public void setAppsgeyserNativeAdModels(List<AppsgeyserNativeAdModel> appsgeyserNativeAdModels) {
        this.appsgeyserNativeAdModels = appsgeyserNativeAdModels;
    }
}
