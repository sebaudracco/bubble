package com.appsgeyser.sdk.configuration.models;

import com.google.gson.annotations.SerializedName;

public class AdNetworkSdkModel {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("banner_id")
    private String bannerId;
    @SerializedName("custom_placement_id")
    private String customPlacementId;
    @SerializedName("active")
    private boolean isActive;
    @SerializedName("loading_timeout")
    private int loadingTimeout;
    @SerializedName("placement_id")
    private String placementId;
    @SerializedName("priority")
    private int priority;
    @SerializedName("uniq_id")
    private String uniqueId;

    public AdNetworkSdkModel(boolean isActive, String placementId, String bannerId, String uniqueId, String appId, String customPlacementId, int priority, int loadingTimeout) {
        this.isActive = isActive;
        this.placementId = placementId;
        this.bannerId = bannerId;
        this.uniqueId = uniqueId;
        this.appId = appId;
        this.customPlacementId = customPlacementId;
        this.priority = priority;
        this.loadingTimeout = loadingTimeout;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public String getBannerId() {
        return this.bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCustomPlacementId() {
        return this.customPlacementId;
    }

    public void setCustomPlacementId(String customPlacementId) {
        this.customPlacementId = customPlacementId;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getLoadingTimeout() {
        return this.loadingTimeout;
    }

    public void setLoadingTimeout(int loadingTimeout) {
        this.loadingTimeout = loadingTimeout;
    }
}
