package com.appnext.core;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class AppnextAd extends C0908i {
    private String androidPackage = "";
    private String appSize = "";
    private String bId = "";
    private String buttonText = "";
    private String campaignGoal = "";
    private String campaignId = "";
    private String campaignType = "";
    private String categories = "";
    private String cb = "";
    private String country = "";
    private String cpt_list = "";
    private String desc = "";
    private String ecpm = "";
    private String idx = "";
    private String pbaBId = "";
    private String pbaZId = "";
    private String pixelImp = "";
    private String revenueRate = "";
    private String revenueType = "";
    private String storeDownloads = "";
    private String storeRating = "";
    private String supportedVersion = "";
    private String title = "";
    private String urlApp = "";
    private String urlImg = "";
    private String urlImgWide = "";
    private String urlVideo = "";
    private String urlVideo30Sec = "";
    private String urlVideo30SecHigh = "";
    private String urlVideoHigh = "";
    private String webview = SchemaSymbols.ATTVAL_FALSE_0;
    private String zId = "";

    protected AppnextAd() {
    }

    protected AppnextAd(AppnextAd appnextAd) {
        if (appnextAd != null) {
            this.desc = appnextAd.desc;
            this.title = appnextAd.title;
            this.urlImg = appnextAd.urlImg;
            this.urlImgWide = appnextAd.urlImgWide;
            this.urlApp = appnextAd.urlApp;
            this.bId = appnextAd.bId;
            this.campaignId = appnextAd.campaignId;
            this.cb = appnextAd.cb;
            this.zId = appnextAd.zId;
            this.androidPackage = appnextAd.androidPackage;
            this.pbaZId = appnextAd.pbaZId;
            this.pbaBId = appnextAd.pbaBId;
            setAdID(appnextAd.getAdID());
            this.revenueType = appnextAd.revenueType;
            this.revenueRate = appnextAd.revenueRate;
            this.categories = appnextAd.categories;
            this.idx = appnextAd.idx;
            this.pixelImp = appnextAd.pixelImp;
            this.country = appnextAd.country;
            this.campaignType = appnextAd.campaignType;
            this.supportedVersion = appnextAd.supportedVersion;
            this.storeRating = appnextAd.storeRating;
            this.storeDownloads = appnextAd.storeDownloads;
            this.appSize = appnextAd.appSize;
            this.urlVideo = appnextAd.urlVideo;
            this.urlVideoHigh = appnextAd.urlVideoHigh;
            this.urlVideo30Sec = appnextAd.urlVideo30Sec;
            this.urlVideo30SecHigh = appnextAd.urlVideo30SecHigh;
            this.campaignGoal = appnextAd.campaignGoal;
            this.buttonText = appnextAd.buttonText;
            this.ecpm = appnextAd.ecpm;
            this.cpt_list = appnextAd.cpt_list;
            this.webview = appnextAd.webview;
            setPlacementID(appnextAd.getPlacementID());
            setAdJSON(appnextAd.getAdJSON());
        }
    }

    protected String getRevenueType() {
        return this.revenueType;
    }

    protected String getRevenueRate() {
        return this.revenueRate;
    }

    public String getCategories() {
        return this.categories;
    }

    public String getAdDescription() {
        return this.desc;
    }

    public String getAdTitle() {
        return this.title;
    }

    public String getImageURL() {
        return this.urlImg;
    }

    public String getWideImageURL() {
        return this.urlImgWide;
    }

    public String getIdx() {
        return this.idx;
    }

    protected void setIdx(String str) {
        this.idx = str;
    }

    protected void setRevenueType(String str) {
        this.revenueType = str;
    }

    protected void setRevenueRate(String str) {
        this.revenueRate = str;
    }

    protected void setCategories(String str) {
        this.categories = str;
    }

    protected void setAdDesc(String str) {
        this.desc = str;
    }

    protected void setAdTitle(String str) {
        this.title = str;
    }

    public String getBannerID() {
        return this.bId;
    }

    protected void setBannerID(String str) {
        this.bId = str;
    }

    public String getCampaignID() {
        return this.campaignId;
    }

    protected void setCampaignID(String str) {
        this.campaignId = str;
    }

    protected String getCb() {
        return this.cb;
    }

    protected void setCb(String str) {
        this.cb = str;
    }

    protected String getZoneID() {
        return this.zId;
    }

    protected void setZoneID(String str) {
        this.zId = str;
    }

    public String getAdPackage() {
        return this.androidPackage;
    }

    protected void setAdPackage(String str) {
        this.androidPackage = str;
    }

    protected String getEpub() {
        return this.pbaZId;
    }

    protected void setEpub(String str) {
        this.pbaZId = str;
    }

    protected String getBpub() {
        return this.pbaBId;
    }

    protected void setBpub(String str) {
        this.pbaBId = str;
    }

    protected void setImageURL(String str) {
        this.urlImg = str;
    }

    protected void setWideImageURL(String str) {
        this.urlImgWide = str;
    }

    protected String getAppURL() {
        return this.urlApp;
    }

    protected void setAppURL(String str) {
        this.urlApp = str;
    }

    protected String getImpressionURL() {
        return this.pixelImp;
    }

    protected void setImpressionURL(String str) {
        this.pixelImp = str;
    }

    public String getCountry() {
        return this.country;
    }

    protected void setCounty(String str) {
        this.country = str;
    }

    public String getCampaignType() {
        return this.campaignType;
    }

    protected void setCampaignType(String str) {
        this.campaignType = str;
    }

    public String getSupportedVersion() {
        return this.supportedVersion;
    }

    protected void setSupportedVersion(String str) {
        this.supportedVersion = str;
    }

    public String getStoreRating() {
        return this.storeRating;
    }

    protected void setStoreRating(String str) {
        this.storeRating = str;
    }

    public String getStoreDownloads() {
        return this.storeDownloads;
    }

    protected void setStoreDownloads(String str) {
        this.storeDownloads = str;
    }

    public String getAppSize() {
        return this.appSize;
    }

    protected void setAppSize(String str) {
        this.appSize = str;
    }

    public String getVideoUrl() {
        return this.urlVideo;
    }

    protected void setVideoUrl(String str) {
        this.urlVideo = str;
    }

    public String getVideoUrlHigh() {
        return this.urlVideoHigh;
    }

    protected void setVideoUrlHigh(String str) {
        this.urlVideoHigh = str;
    }

    public String getVideoUrl30Sec() {
        return this.urlVideo30Sec;
    }

    protected void setVideoUrl30Sec(String str) {
        this.urlVideo30Sec = str;
    }

    public String getVideoUrlHigh30Sec() {
        return this.urlVideo30SecHigh;
    }

    protected void setVideoUrlHigh30Sec(String str) {
        this.urlVideo30SecHigh = str;
    }

    protected String getCampaignGoal() {
        return this.campaignGoal;
    }

    protected void setCampaignGoal(String str) {
        this.campaignGoal = str;
    }

    protected String getButtonText() {
        return this.buttonText;
    }

    protected void setButtonText(String str) {
        this.buttonText = str;
    }

    protected String getEcpm() {
        return this.ecpm;
    }

    protected void setEcpm(String str) {
        this.ecpm = str;
    }

    public float getECPM() {
        return Float.parseFloat(getEcpm());
    }

    public float getPPR() {
        return getECPM() / 1000.0f;
    }

    protected String getWebview() {
        return this.webview;
    }

    protected void setWebview(String str) {
        this.webview = str;
    }

    protected String getCptList() {
        return this.cpt_list;
    }

    protected void setCptList(String str) {
        this.cpt_list = str;
    }
}
