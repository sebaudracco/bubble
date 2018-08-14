package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
import com.mopub.common.BaseLifecycleListener;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.util.Json;
import com.mopub.mobileads.CustomEventRewardedVideo.CustomEventRewardedVideoListener;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AdColonyRewardedVideo extends CustomEventRewardedVideo {
    public static final String ALL_ZONE_IDS_KEY = "allZoneIds";
    public static final String APP_ID_KEY = "appId";
    public static final String CLIENT_OPTIONS_KEY = "clientOptions";
    private static final String[] DEFAULT_ALL_ZONE_IDS = new String[]{"ZONE_ID_1", "ZONE_ID_2", "..."};
    private static final String DEFAULT_APP_ID = "YOUR_AD_COLONY_APP_ID_HERE";
    private static final String DEFAULT_CLIENT_OPTIONS = "version=YOUR_APP_VERSION_HERE,store:google";
    private static final String DEFAULT_ZONE_ID = "YOUR_CURRENT_ZONE_ID";
    private static final String TAG = "AdColonyRewardedVideo";
    public static final String ZONE_ID_KEY = "zoneId";
    private static String[] previousAdColonyAllZoneIds;
    private static boolean sInitialized = false;
    private static LifecycleListener sLifecycleListener = new BaseLifecycleListener();
    private static WeakHashMap<String, AdColonyInterstitial> sZoneIdToAdMap = new WeakHashMap();
    AdColonyInterstitial mAd;
    private AdColonyAdOptions mAdColonyAdOptions = new AdColonyAdOptions();
    private AdColonyAppOptions mAdColonyAppOptions = new AdColonyAppOptions();
    private AdColonyListener mAdColonyListener;
    @Nullable
    private String mAdUnitId;
    private final Handler mHandler = new Handler();
    private boolean mIsLoading = false;
    private final ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
    private String mZoneId;

    class C36481 implements Runnable {

        class C36471 implements Runnable {
            C36471() {
            }

            public void run() {
                if (AdColonyRewardedVideo.this.hasVideoAvailable()) {
                    Log.d(AdColonyRewardedVideo.TAG, "AdColony rewarded ad has been successfully loaded.");
                    MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(AdColonyRewardedVideo.class, AdColonyRewardedVideo.this.mZoneId);
                    return;
                }
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AdColonyRewardedVideo.class, AdColonyRewardedVideo.this.mZoneId, MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        C36481() {
        }

        public void run() {
            if (AdColonyRewardedVideo.this.isAdAvailable(AdColonyRewardedVideo.this.mZoneId)) {
                AdColonyRewardedVideo.this.mAd = (AdColonyInterstitial) AdColonyRewardedVideo.sZoneIdToAdMap.get(AdColonyRewardedVideo.this.mZoneId);
                AdColonyRewardedVideo.this.mIsLoading = false;
                AdColonyRewardedVideo.this.mScheduledThreadPoolExecutor.shutdownNow();
                AdColonyRewardedVideo.this.mHandler.post(new C36471());
            }
        }
    }

    public static final class AdColonyGlobalMediationSettings implements MediationSettings {
        @Nullable
        private final String mUserId;

        public AdColonyGlobalMediationSettings(@Nullable String userId) {
            this.mUserId = userId;
        }

        @Nullable
        public String getUserId() {
            return this.mUserId;
        }
    }

    public static final class AdColonyInstanceMediationSettings implements MediationSettings {
        private final boolean mWithConfirmationDialog;
        private final boolean mWithResultsDialog;

        public AdColonyInstanceMediationSettings(boolean withConfirmationDialog, boolean withResultsDialog) {
            this.mWithConfirmationDialog = withConfirmationDialog;
            this.mWithResultsDialog = withResultsDialog;
        }

        public boolean withConfirmationDialog() {
            return this.mWithConfirmationDialog;
        }

        public boolean withResultsDialog() {
            return this.mWithResultsDialog;
        }
    }

    private static class AdColonyListener extends AdColonyInterstitialListener implements AdColonyRewardListener, CustomEventRewardedVideoListener {
        private static final String TAG = "AdColonyListener";
        private AdColonyAdOptions mAdOptions;

        AdColonyListener(AdColonyAdOptions adOptions) {
            this.mAdOptions = adOptions;
        }

        public void onReward(@NonNull AdColonyReward a) {
            MoPubReward reward;
            if (a.success()) {
                Log.d(TAG, "AdColonyReward name: " + a.getRewardName());
                Log.d(TAG, "AdColonyReward amount: " + a.getRewardAmount());
                reward = MoPubReward.success(a.getRewardName(), a.getRewardAmount());
            } else {
                Log.d(TAG, "AdColonyReward failed");
                reward = MoPubReward.failure();
            }
            MoPubRewardedVideoManager.onRewardedVideoCompleted(AdColonyRewardedVideo.class, a.getZoneID(), reward);
        }

        public void onRequestFilled(@NonNull AdColonyInterstitial adColonyInterstitial) {
            AdColonyRewardedVideo.sZoneIdToAdMap.put(adColonyInterstitial.getZoneID(), adColonyInterstitial);
        }

        public void onRequestNotFilled(@NonNull AdColonyZone zone) {
            Log.d(TAG, "AdColony rewarded ad has no fill.");
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AdColonyRewardedVideo.class, zone.getZoneID(), MoPubErrorCode.NETWORK_NO_FILL);
        }

        public void onClosed(@NonNull AdColonyInterstitial ad) {
            Log.d(TAG, "AdColony rewarded ad has been dismissed.");
            MoPubRewardedVideoManager.onRewardedVideoClosed(AdColonyRewardedVideo.class, ad.getZoneID());
        }

        public void onOpened(@NonNull AdColonyInterstitial ad) {
            Log.d(TAG, "AdColony rewarded ad shown: " + ad.getZoneID());
            MoPubRewardedVideoManager.onRewardedVideoStarted(AdColonyRewardedVideo.class, ad.getZoneID());
        }

        public void onExpiring(@NonNull AdColonyInterstitial ad) {
            Log.d(TAG, "AdColony rewarded ad is expiring; requesting new ad");
            AdColony.requestInterstitial(ad.getZoneID(), ad.getListener(), this.mAdOptions);
        }

        public void onClicked(@NonNull AdColonyInterstitial ad) {
            MoPubRewardedVideoManager.onRewardedVideoClicked(AdColonyRewardedVideo.class, ad.getZoneID());
        }
    }

    @Nullable
    public CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return this.mAdColonyListener;
    }

    @Nullable
    public LifecycleListener getLifecycleListener() {
        return sLifecycleListener;
    }

    @NonNull
    public String getAdNetworkId() {
        return this.mZoneId;
    }

    protected void onInvalidate() {
        this.mScheduledThreadPoolExecutor.shutdownNow();
        AdColonyInterstitial ad = (AdColonyInterstitial) sZoneIdToAdMap.get(this.mZoneId);
        if (ad != null) {
            ad.setListener(null);
            ad.destroy();
            sZoneIdToAdMap.remove(this.mZoneId);
            Log.d(TAG, "AdColony rewarded video destroyed");
        }
    }

    public boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> serverExtras) throws Exception {
        boolean z = true;
        synchronized (AdColonyRewardedVideo.class) {
            if (sInitialized) {
                z = false;
            } else {
                String adColonyClientOptions = DEFAULT_CLIENT_OPTIONS;
                String adColonyAppId = DEFAULT_APP_ID;
                String[] adColonyAllZoneIds = DEFAULT_ALL_ZONE_IDS;
                if (extrasAreValid(serverExtras)) {
                    adColonyClientOptions = (String) serverExtras.get("clientOptions");
                    adColonyAppId = (String) serverExtras.get("appId");
                    adColonyAllZoneIds = extractAllZoneIds(serverExtras);
                }
                setUpGlobalSettings();
                this.mAdColonyAppOptions = AdColonyAppOptions.getMoPubAppOptions(adColonyClientOptions);
                if (!isAdColonyConfigured()) {
                    previousAdColonyAllZoneIds = adColonyAllZoneIds;
                    AdColony.configure(launcherActivity, this.mAdColonyAppOptions, adColonyAppId, adColonyAllZoneIds);
                }
                sInitialized = true;
            }
        }
        return z;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        this.mZoneId = DEFAULT_ZONE_ID;
        if (extrasAreValid(serverExtras)) {
            this.mZoneId = (String) serverExtras.get("zoneId");
            String adColonyClientOptions = (String) serverExtras.get("clientOptions");
            String adColonyAppId = (String) serverExtras.get("appId");
            String[] adColonyAllZoneIds = extractAllZoneIds(serverExtras);
            if (shouldReconfigure(previousAdColonyAllZoneIds, adColonyAllZoneIds)) {
                this.mAdColonyAppOptions = AdColonyAppOptions.getMoPubAppOptions(adColonyClientOptions);
                AdColony.configure(activity, this.mAdColonyAppOptions, adColonyAppId, adColonyAllZoneIds);
                previousAdColonyAllZoneIds = adColonyAllZoneIds;
            }
        }
        Object adUnitObject = localExtras.get(DataKeys.AD_UNIT_ID_KEY);
        if (adUnitObject != null && (adUnitObject instanceof String)) {
            this.mAdUnitId = (String) adUnitObject;
        }
        sZoneIdToAdMap.put(this.mZoneId, null);
        setUpAdOptions();
        this.mAdColonyListener = new AdColonyListener(this.mAdColonyAdOptions);
        AdColony.setRewardListener(this.mAdColonyListener);
        AdColony.requestInterstitial(this.mZoneId, this.mAdColonyListener, this.mAdColonyAdOptions);
        scheduleOnVideoReady();
    }

    private static boolean shouldReconfigure(String[] previousZones, String[] newZones) {
        if (previousZones == null) {
            return true;
        }
        if (newZones == null) {
            return false;
        }
        if (previousZones.length != newZones.length) {
            return true;
        }
        Arrays.sort(previousZones);
        Arrays.sort(newZones);
        if (Arrays.equals(previousZones, newZones)) {
            return false;
        }
        return true;
    }

    private void setUpAdOptions() {
        this.mAdColonyAdOptions.enableConfirmationDialog(getConfirmationDialogFromSettings());
        this.mAdColonyAdOptions.enableResultsDialog(getResultsDialogFromSettings());
    }

    private boolean isAdColonyConfigured() {
        return !AdColony.getSDKVersion().isEmpty();
    }

    public boolean hasVideoAvailable() {
        return (this.mAd == null || this.mAd.isExpired()) ? false : true;
    }

    public void showVideo() {
        if (hasVideoAvailable()) {
            this.mAd.show();
        } else {
            MoPubRewardedVideoManager.onRewardedVideoPlaybackError(AdColonyRewardedVideo.class, this.mZoneId, MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
        }
    }

    private boolean extrasAreValid(Map<String, String> extras) {
        return extras != null && extras.containsKey("clientOptions") && extras.containsKey("appId") && extras.containsKey("allZoneIds") && extras.containsKey("zoneId");
    }

    private String[] extractAllZoneIds(Map<String, String> serverExtras) {
        String[] result = Json.jsonArrayToStringArray((String) serverExtras.get("allZoneIds"));
        if (result.length != 0) {
            return result;
        }
        return new String[]{""};
    }

    private void setUpGlobalSettings() {
        AdColonyGlobalMediationSettings globalMediationSettings = (AdColonyGlobalMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(AdColonyGlobalMediationSettings.class);
        if (globalMediationSettings != null && globalMediationSettings.getUserId() != null) {
            this.mAdColonyAppOptions.setUserID(globalMediationSettings.getUserId());
        }
    }

    private boolean getConfirmationDialogFromSettings() {
        AdColonyInstanceMediationSettings settings = (AdColonyInstanceMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(AdColonyInstanceMediationSettings.class, this.mAdUnitId);
        return settings != null && settings.withConfirmationDialog();
    }

    private boolean getResultsDialogFromSettings() {
        AdColonyInstanceMediationSettings settings = (AdColonyInstanceMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(AdColonyInstanceMediationSettings.class, this.mAdUnitId);
        return settings != null && settings.withResultsDialog();
    }

    private void scheduleOnVideoReady() {
        Runnable runnable = new C36481();
        if (!this.mIsLoading) {
            this.mScheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
            this.mIsLoading = true;
        }
    }

    private boolean isAdAvailable(String zoneId) {
        return sZoneIdToAdMap.get(zoneId) != null;
    }
}
