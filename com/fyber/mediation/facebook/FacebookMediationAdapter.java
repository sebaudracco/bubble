package com.fyber.mediation.facebook;

import android.app.Activity;
import android.os.Build.VERSION;
import com.adcolony.sdk.AdColonyAppOptions;
import com.facebook.ads.AdSettings;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.annotations.AdapterDefinition;
import com.fyber.mediation.facebook.banner.FacebookBannerMediationAdapter;
import com.fyber.mediation.facebook.interstitial.FacebookInterstitialMediationAdapter;
import com.fyber.mediation.facebook.rv.FacebookRewardedVideoAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AdapterDefinition(apiVersion = 5, name = "FacebookAudienceNetwork", sdkFeatures = {"banners", "blended"}, version = "4.27.1-r1")
public class FacebookMediationAdapter extends MediationAdapter {
    public static final String ADAPTER_NAME = "FacebookAudienceNetwork";
    public static final String ADAPTER_VERSION = "4.27.1-r1";
    public static final String BANNER_PLACEMENT_ID_KEY = "bannerPlacementId";
    public static final String INT_PLACEMENT_ID_KEY = "placementId";
    public static final String RV_PLACEMENT_ID_KEY = "rewardedPlacementId";
    private static final String TAG = FacebookMediationAdapter.class.getSimpleName();
    public static final String TEST_DEVICE_HASH_KEY = "testDeviceHash";
    private FacebookBannerMediationAdapter bannerMediationAdapter;
    private Map<String, Object> configs;
    private FacebookInterstitialMediationAdapter interstitialMediationAdapter;
    private FacebookRewardedVideoAdapter rewardedVideoAdapter;

    public boolean startAdapter(Activity activity, Map<String, Object> configs) {
        FyberLogger.i(TAG, "Starting mediation adapter Facebook Audience Network 4.27.1-r1");
        this.configs = configs;
        if (VERSION.SDK_INT < 15) {
            FyberLogger.e(TAG, "Facebook Audience Network requires Android API level 15+. Adapter won't start.");
            return false;
        }
        String rvPlacementId = (String) getConfiguration(configs, RV_PLACEMENT_ID_KEY, String.class);
        String intPlacementId = (String) getConfiguration(configs, "placementId", String.class);
        String bannerPlacementId = (String) getConfiguration(configs, BANNER_PLACEMENT_ID_KEY, String.class);
        for (String tHashId : getConfigListOfDevices()) {
            FyberLogger.i(TAG, "Adding test device hash: " + tHashId);
            AdSettings.addTestDevice(tHashId);
        }
        this.interstitialMediationAdapter = new FacebookInterstitialMediationAdapter(this, intPlacementId);
        if (StringUtils.nullOrEmpty(rvPlacementId)) {
            FyberLogger.w(TAG, "The `rewardedPlacementId` parameter is missing. The rewarded video adapter won’t start");
        } else {
            this.rewardedVideoAdapter = new FacebookRewardedVideoAdapter(this, activity, rvPlacementId);
        }
        if (StringUtils.nullOrEmpty(bannerPlacementId)) {
            FyberLogger.w(TAG, "The `bannerPlacementId` parameter is missing. The banner adapter won’t start");
        } else {
            this.bannerMediationAdapter = new FacebookBannerMediationAdapter(this, bannerPlacementId);
        }
        AdSettings.setMediationService(AdColonyAppOptions.FYBER);
        return true;
    }

    public String getName() {
        return "FacebookAudienceNetwork";
    }

    public String getVersion() {
        return "4.27.1-r1";
    }

    public FacebookRewardedVideoAdapter getVideoMediationAdapter() {
        return this.rewardedVideoAdapter;
    }

    public FacebookInterstitialMediationAdapter getInterstitialMediationAdapter() {
        return this.interstitialMediationAdapter;
    }

    public FacebookBannerMediationAdapter getBannerMediationAdapter() {
        return this.bannerMediationAdapter;
    }

    protected Set<?> getListeners() {
        return null;
    }

    private List<String> getConfigListOfDevices() {
        List<String> zoneIds = new LinkedList();
        String zoneIdString = (String) getConfiguration(this.configs, TEST_DEVICE_HASH_KEY, String.class);
        if (StringUtils.notNullNorEmpty(zoneIdString)) {
            return Arrays.asList(zoneIdString.split(","));
        }
        return zoneIds;
    }
}
