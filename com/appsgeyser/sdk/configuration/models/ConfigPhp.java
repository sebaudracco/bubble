package com.appsgeyser.sdk.configuration.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.util.Map;

public class ConfigPhp implements Parcelable {
    public static final Creator<ConfigPhp> CREATOR = new C12921();
    @SerializedName("about_screen_description")
    private String aboutScreenDescription;
    @SerializedName("about_screen_description_type")
    private String aboutScreenDescriptionType;
    private FastTrackSdkModel activeAdsSDK;
    private Map<String, AdNetworkSdkModel> adsNetworkSdk;
    private ConfigPhpSdkModel appsgeyserSdk;
    private ConfigPhpSdkModel areaMetricsSdk;
    private int countOfTry;
    private String country;
    private ConfigPhpSdkModel cuebiqSdk;
    private ConfigPhpSdkModel elephantDataSdk;
    private String eulaBeginning;
    private long fullScreenDelay;
    private int fullscreenBannerCountToShow;
    private Map<String, AdNetworkSdkModel> fullscreenSdk;
    private ConfigPhpSdkModel instantCoffeeSdk;
    @SerializedName("enable_about_screen")
    private boolean isAboutScreenEnabled;
    @SerializedName("startup_confirmation_dialog")
    private boolean isAdvertisingTermsDialog;
    private boolean isOnResumeFSEnabled;
    private boolean isOnTouchFSEnabled;
    private boolean isTakeOffFSEnabled;
    private ConfigPhpSdkModel mobiInfoSdk;
    private ConfigPhpSdkModel oneAudienceSdk;
    private String oneSignalAppId;
    private ConfigPhpSdkModel profiler42mattersSdk;
    private ConfigPhpSdkModel revealSdk;
    private Map<String, AdNetworkSdkModel> rewardedVideoSdk;
    @SerializedName("startup_dialog_allowing_use_if_decline")
    private boolean startupELUAConfirmationDialogAllow;
    private Map<String, String> statUrls;

    static class C12921 implements Creator<ConfigPhp> {
        C12921() {
        }

        public ConfigPhp createFromParcel(Parcel in) {
            return new ConfigPhp(in);
        }

        public ConfigPhp[] newArray(int size) {
            return new ConfigPhp[size];
        }
    }

    private ConfigPhp(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = "default";
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
        this.oneAudienceSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.cuebiqSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.mobiInfoSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.instantCoffeeSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.elephantDataSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.profiler42mattersSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.areaMetricsSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.revealSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.appsgeyserSdk = (ConfigPhpSdkModel) in.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.isAboutScreenEnabled = in.readByte() != (byte) 0;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.isAdvertisingTermsDialog = z;
        this.country = in.readString();
        this.eulaBeginning = in.readString();
        this.oneSignalAppId = in.readString();
        this.countOfTry = in.readInt();
        this.aboutScreenDescriptionType = in.readString();
        this.aboutScreenDescription = in.readString();
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.startupELUAConfirmationDialogAllow = z2;
    }

    public static ConfigPhp parseFromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return (ConfigPhp) gson.fromJson(reader, ConfigPhp.class);
    }

    public ConfigPhp(ConfigPhpSdkModel oneAudienceSdk, ConfigPhpSdkModel cuebiqSdk, ConfigPhpSdkModel mobiInfoSdk, ConfigPhpSdkModel instantCoffeeSdk, ConfigPhpSdkModel elephantDataSdk, ConfigPhpSdkModel profiler42mattersSdk, ConfigPhpSdkModel areaMetricsSdk, ConfigPhpSdkModel revealSdk, ConfigPhpSdkModel appsgeyserSdk, String country, String eulaBeginning, String oneSignalAppId, int countOfTry, Map<String, String> statUrls, boolean isAboutScreenEnabled, boolean isAdvertisingTermsDialog, FastTrackSdkModel activeAdsSDK, String aboutScreenDescription, String aboutScreenDescriptionType, boolean startupELUAConfirmationDialogAllow) {
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = "default";
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
        this.oneAudienceSdk = oneAudienceSdk;
        this.cuebiqSdk = cuebiqSdk;
        this.mobiInfoSdk = mobiInfoSdk;
        this.instantCoffeeSdk = instantCoffeeSdk;
        this.elephantDataSdk = elephantDataSdk;
        this.profiler42mattersSdk = profiler42mattersSdk;
        this.areaMetricsSdk = areaMetricsSdk;
        this.revealSdk = revealSdk;
        this.appsgeyserSdk = appsgeyserSdk;
        this.country = country;
        this.eulaBeginning = eulaBeginning;
        this.oneSignalAppId = oneSignalAppId;
        this.countOfTry = countOfTry;
        this.statUrls = statUrls;
        this.isAboutScreenEnabled = isAboutScreenEnabled;
        this.isAdvertisingTermsDialog = isAdvertisingTermsDialog;
        this.activeAdsSDK = activeAdsSDK;
        this.aboutScreenDescription = aboutScreenDescription;
        this.aboutScreenDescriptionType = aboutScreenDescriptionType;
        this.startupELUAConfirmationDialogAllow = startupELUAConfirmationDialogAllow;
    }

    public ConfigPhp() {
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = "default";
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
    }

    public ConfigPhpSdkModel getOneAudienceSdk() {
        return this.oneAudienceSdk;
    }

    public void setOneAudienceSdk(ConfigPhpSdkModel oneAudienceSdk) {
        this.oneAudienceSdk = oneAudienceSdk;
    }

    public ConfigPhpSdkModel getCuebiqSdk() {
        return this.cuebiqSdk;
    }

    public ConfigPhpSdkModel getInstantCoffeeSdk() {
        return this.instantCoffeeSdk;
    }

    public ConfigPhpSdkModel getElephantDataSdk() {
        return this.elephantDataSdk;
    }

    public ConfigPhpSdkModel getProfiler42mattersSdk() {
        return this.profiler42mattersSdk;
    }

    public ConfigPhpSdkModel getAreaMetricsSdk() {
        return this.areaMetricsSdk;
    }

    public ConfigPhpSdkModel getRevealSdk() {
        return this.revealSdk;
    }

    public ConfigPhpSdkModel getAppsgeyserSdk() {
        return this.appsgeyserSdk;
    }

    public String getAboutScreenDescriptionType() {
        return this.aboutScreenDescriptionType;
    }

    public String getAboutScreenDescription() {
        return this.aboutScreenDescription;
    }

    public boolean getStartupELUAConfirmationDialogAllow() {
        return this.startupELUAConfirmationDialogAllow;
    }

    public void setCuebiqSdk(ConfigPhpSdkModel cuebiqSdk) {
        this.cuebiqSdk = cuebiqSdk;
    }

    public void setInstantCoffeeSdk(ConfigPhpSdkModel instantCoffeeSdk) {
        this.instantCoffeeSdk = instantCoffeeSdk;
    }

    public void setElephantData(ConfigPhpSdkModel elephantDataSdk) {
        this.elephantDataSdk = elephantDataSdk;
    }

    public void setProfiler42mattersSdk(ConfigPhpSdkModel profiler42mattersSdk) {
        this.profiler42mattersSdk = profiler42mattersSdk;
    }

    public void setAreaMetricsSdk(ConfigPhpSdkModel areaMetricsSdk) {
        this.areaMetricsSdk = areaMetricsSdk;
    }

    public void setRevealSdk(ConfigPhpSdkModel revealSdk) {
        this.revealSdk = revealSdk;
    }

    public void setAppsgeyserSdk(ConfigPhpSdkModel appsgeyserSdk) {
        this.appsgeyserSdk = appsgeyserSdk;
    }

    public ConfigPhpSdkModel getMobiInfoSdk() {
        return this.mobiInfoSdk;
    }

    public void setMobiInfoSdk(ConfigPhpSdkModel mobiInfoSdk) {
        this.mobiInfoSdk = mobiInfoSdk;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEulaBeginning() {
        return this.eulaBeginning;
    }

    public void setEulaBeginning(String eulaBeginning) {
        this.eulaBeginning = eulaBeginning;
    }

    public int getCountOfTry() {
        return this.countOfTry;
    }

    public void setCountOfTry(int countOfTry) {
        this.countOfTry = countOfTry;
    }

    public Map<String, String> getStatUrls() {
        return this.statUrls;
    }

    public void setStatUrls(Map<String, String> statUrls) {
        this.statUrls = statUrls;
    }

    public boolean isAboutScreenEnabled() {
        return this.isAboutScreenEnabled;
    }

    public void setAboutScreenEnabled(boolean aboutScreenEnabled) {
        this.isAboutScreenEnabled = aboutScreenEnabled;
    }

    public String getOneSignalAppId() {
        return this.oneSignalAppId;
    }

    public void setOneSignalAppId(String oneSignalAppId) {
        this.oneSignalAppId = oneSignalAppId;
    }

    public boolean isAdvertisingTermsDialog() {
        return this.isAdvertisingTermsDialog;
    }

    public void setAdvertisingTermsDialog(boolean advertisingTermsDialog) {
        this.isAdvertisingTermsDialog = advertisingTermsDialog;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(this.oneAudienceSdk, i);
        parcel.writeParcelable(this.cuebiqSdk, i);
        parcel.writeParcelable(this.mobiInfoSdk, i);
        parcel.writeParcelable(this.instantCoffeeSdk, i);
        parcel.writeParcelable(this.elephantDataSdk, i);
        parcel.writeParcelable(this.profiler42mattersSdk, i);
        parcel.writeParcelable(this.areaMetricsSdk, i);
        parcel.writeParcelable(this.revealSdk, i);
        parcel.writeParcelable(this.appsgeyserSdk, i);
        parcel.writeByte((byte) (this.isAboutScreenEnabled ? 1 : 0));
        if (this.isAdvertisingTermsDialog) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.country);
        parcel.writeString(this.eulaBeginning);
        parcel.writeString(this.oneSignalAppId);
        parcel.writeInt(this.countOfTry);
        parcel.writeString(this.aboutScreenDescriptionType);
        parcel.writeString(this.aboutScreenDescription);
        if (!this.startupELUAConfirmationDialogAllow) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public Map<String, AdNetworkSdkModel> getAdsNetworkSdk() {
        return this.adsNetworkSdk;
    }

    public void setAdsNetworkSdk(Map<String, AdNetworkSdkModel> adsNetworkSdk) {
        this.adsNetworkSdk = adsNetworkSdk;
    }

    public boolean isOfferWallEnabled() {
        if (this.adsNetworkSdk == null || this.adsNetworkSdk.size() <= 0) {
            return false;
        }
        for (AdNetworkSdkModel model : this.adsNetworkSdk.values()) {
            if (model.isActive()) {
                return true;
            }
        }
        return false;
    }

    public boolean isRewardedVideoEnabled() {
        if (this.rewardedVideoSdk == null || this.rewardedVideoSdk.size() <= 0) {
            return false;
        }
        for (AdNetworkSdkModel model : this.rewardedVideoSdk.values()) {
            if (model.isActive()) {
                return true;
            }
        }
        return false;
    }

    public Map<String, AdNetworkSdkModel> getRewardedVideoSdk() {
        return this.rewardedVideoSdk;
    }

    public void setRewardedVideoSdk(Map<String, AdNetworkSdkModel> rewardedVideoSdk) {
        this.rewardedVideoSdk = rewardedVideoSdk;
    }

    public long getFullScreenDelay() {
        return this.fullScreenDelay;
    }

    public void setFullScreenDelay(long fullScreenDelay) {
        this.fullScreenDelay = fullScreenDelay;
    }

    public Map<String, AdNetworkSdkModel> getFullscreenSdk() {
        return this.fullscreenSdk;
    }

    public boolean isTakeOffFSEnabled() {
        return this.isTakeOffFSEnabled;
    }

    public boolean isOnResumeFSEnabled() {
        return this.isOnResumeFSEnabled;
    }

    public boolean isOnTouchFSEnabled() {
        return this.isOnTouchFSEnabled;
    }

    public int getFullscreenBannerCountToShow() {
        return this.fullscreenBannerCountToShow;
    }

    public void setFullscreenBannerCountToShow(int fullscreenBannerCountToShow) {
        this.fullscreenBannerCountToShow = fullscreenBannerCountToShow;
    }

    public FastTrackSdkModel getActiveAdsSDK() {
        return this.activeAdsSDK;
    }
}
