package com.appsgeyser.sdk.ads.fastTrack;

import com.google.gson.annotations.SerializedName;

public class FastTrackSdkModel {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("banner_placement_id")
    private String bannerPlacementId;
    @SerializedName("banner_refresh_timer")
    private Integer bannerRefreshTimer;
    @SerializedName("custom_banner_activated")
    private boolean customBannerActivated;
    @SerializedName("custom_fullscreen_activated")
    private boolean customFullscreenActivated;
    @SerializedName("custom_rewarded_activated")
    private boolean customRewardedActivated;
    @SerializedName("fullscreen_intensity")
    private Integer fullscreenIntensity;
    @SerializedName("fullscreen_placement_id")
    private String fullscreenPlacementId;
    @SerializedName("name")
    private String name;
    @SerializedName("pub_ids")
    private String[] publisherIds;
    @SerializedName("rewarded_video_placement_id")
    private String rewardedVideoPlacementId;
    @SerializedName("token")
    private String token;

    public FastTrackSdkModel(String name, String appId, String[] publisherIds, String token, String fullscreenPlacementId, Integer fullscreenIntensity, String bannerPlacementId, Integer bannerRefreshTimer, String rewardedVideoPlacementId, boolean customBannerActivated, boolean customFullscreenActivated, boolean customRewardedActivated) {
        this.name = name;
        this.appId = appId;
        this.publisherIds = publisherIds;
        this.token = token;
        this.fullscreenPlacementId = fullscreenPlacementId;
        this.fullscreenIntensity = fullscreenIntensity;
        this.bannerPlacementId = bannerPlacementId;
        this.bannerRefreshTimer = bannerRefreshTimer;
        this.rewardedVideoPlacementId = rewardedVideoPlacementId;
        this.customBannerActivated = customBannerActivated;
        this.customFullscreenActivated = customFullscreenActivated;
        this.customRewardedActivated = customRewardedActivated;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String[] getPublisherIds() {
        return this.publisherIds;
    }

    public void setPublisherIds(String[] publisherIds) {
        this.publisherIds = publisherIds;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullscreenPlacementId() {
        return this.fullscreenPlacementId;
    }

    public void setFullscreenPlacementId(String fullscreenPlacementId) {
        this.fullscreenPlacementId = fullscreenPlacementId;
    }

    public Integer getFullscreenIntensity() {
        return this.fullscreenIntensity;
    }

    public void setFullscreenIntensity(Integer fullscreenIntensity) {
        this.fullscreenIntensity = fullscreenIntensity;
    }

    public String getBannerPlacementId() {
        return this.bannerPlacementId;
    }

    public void setBannerPlacementId(String bannerPlacementId) {
        this.bannerPlacementId = bannerPlacementId;
    }

    public Integer getBannerRefreshTimer() {
        return this.bannerRefreshTimer;
    }

    public void setBannerRefreshTimer(Integer bannerRefreshTimer) {
        this.bannerRefreshTimer = bannerRefreshTimer;
    }

    public String getRewardedVideoPlacementId() {
        return this.rewardedVideoPlacementId;
    }

    public void setRewardedVideoPlacementId(String rewardedVideoPlacementId) {
        this.rewardedVideoPlacementId = rewardedVideoPlacementId;
    }

    public boolean isCustomBannerActivated() {
        return this.customBannerActivated;
    }

    public void setCustomBannerActivated(boolean customBannerActivated) {
        this.customBannerActivated = customBannerActivated;
    }

    public boolean isCustomFullscreenActivated() {
        return this.customFullscreenActivated;
    }

    public void setCustomFullscreenActivated(boolean customFullscreenActivated) {
        this.customFullscreenActivated = customFullscreenActivated;
    }

    public boolean isCustomRewardedActivated() {
        return this.customRewardedActivated;
    }

    public void setCustomRewardedActivated(boolean customRewardedActivated) {
        this.customRewardedActivated = customRewardedActivated;
    }
}
