package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;
import com.mopub.common.util.Json;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Arrays;
import java.util.Map;

public class AdColonyInterstitial extends CustomEventInterstitial {
    public static final String ALL_ZONE_IDS_KEY = "allZoneIds";
    public static final String APP_ID_KEY = "appId";
    public static final String CLIENT_OPTIONS_KEY = "clientOptions";
    private static final String[] DEFAULT_ALL_ZONE_IDS = new String[]{"ZONE_ID_1", "ZONE_ID_2", "..."};
    private static final String DEFAULT_APP_ID = "YOUR_AD_COLONY_APP_ID_HERE";
    private static final String DEFAULT_CLIENT_OPTIONS = "version=YOUR_APP_VERSION_HERE,store:google";
    private static final String DEFAULT_ZONE_ID = "YOUR_CURRENT_ZONE_ID";
    private static final String TAG = "AdColonyInterstitial";
    public static final String ZONE_ID_KEY = "zoneId";
    private static String[] previousAdColonyAllZoneIds;
    private com.adcolony.sdk.AdColonyInterstitial mAdColonyInterstitial;
    private AdColonyInterstitialListener mAdColonyInterstitialListener;
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    private final Handler mHandler = new Handler();

    class C36411 implements Runnable {
        C36411() {
        }

        public void run() {
            AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
        }
    }

    class C36462 extends AdColonyInterstitialListener {

        class C36421 implements Runnable {
            C36421() {
            }

            public void run() {
                AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialLoaded();
            }
        }

        class C36432 implements Runnable {
            C36432() {
            }

            public void run() {
                AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        class C36443 implements Runnable {
            C36443() {
            }

            public void run() {
                AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialDismissed();
            }
        }

        class C36454 implements Runnable {
            C36454() {
            }

            public void run() {
                AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialShown();
            }
        }

        C36462() {
        }

        public void onRequestFilled(@NonNull com.adcolony.sdk.AdColonyInterstitial adColonyInterstitial) {
            AdColonyInterstitial.this.mAdColonyInterstitial = adColonyInterstitial;
            Log.d(AdColonyInterstitial.TAG, "AdColony interstitial ad has been successfully loaded.");
            AdColonyInterstitial.this.mHandler.post(new C36421());
        }

        public void onRequestNotFilled(@NonNull AdColonyZone zone) {
            Log.d(AdColonyInterstitial.TAG, "AdColony interstitial ad has no fill.");
            AdColonyInterstitial.this.mHandler.post(new C36432());
        }

        public void onClosed(@NonNull com.adcolony.sdk.AdColonyInterstitial ad) {
            Log.d(AdColonyInterstitial.TAG, "AdColony interstitial ad has been dismissed.");
            AdColonyInterstitial.this.mHandler.post(new C36443());
        }

        public void onOpened(@NonNull com.adcolony.sdk.AdColonyInterstitial ad) {
            Log.d(AdColonyInterstitial.TAG, "AdColony interstitial ad shown: " + ad.getZoneID());
            AdColonyInterstitial.this.mHandler.post(new C36454());
        }

        public void onExpiring(@NonNull com.adcolony.sdk.AdColonyInterstitial ad) {
            Log.d(AdColonyInterstitial.TAG, "AdColony interstitial ad is expiring; requesting new ad");
            AdColony.requestInterstitial(ad.getZoneID(), AdColonyInterstitial.this.mAdColonyInterstitialListener);
        }

        public void onClicked(@NonNull com.adcolony.sdk.AdColonyInterstitial ad) {
            AdColonyInterstitial.this.mCustomEventInterstitialListener.onInterstitialClicked();
        }
    }

    protected void loadInterstitial(@NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @Nullable Map<String, Object> map, @NonNull Map<String, String> serverExtras) {
        if (context == null || !(context instanceof Activity) || customEventInterstitialListener == null || serverExtras == null) {
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        String clientOptions = DEFAULT_CLIENT_OPTIONS;
        String appId = DEFAULT_APP_ID;
        String[] allZoneIds = DEFAULT_ALL_ZONE_IDS;
        String zoneId = DEFAULT_ZONE_ID;
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (extrasAreValid(serverExtras)) {
            clientOptions = (String) serverExtras.get("clientOptions");
            appId = (String) serverExtras.get("appId");
            allZoneIds = extractAllZoneIds(serverExtras);
            zoneId = (String) serverExtras.get("zoneId");
        }
        AdColonyAppOptions adColonyAppOptions = AdColonyAppOptions.getMoPubAppOptions(clientOptions);
        this.mAdColonyInterstitialListener = getAdColonyInterstitialListener();
        if (!isAdColonyConfigured()) {
            AdColony.configure((Activity) context, adColonyAppOptions, appId, allZoneIds);
        } else if (shouldReconfigure(previousAdColonyAllZoneIds, allZoneIds)) {
            AdColony.configure((Activity) context, adColonyAppOptions, appId, allZoneIds);
            previousAdColonyAllZoneIds = allZoneIds;
        }
        AdColony.requestInterstitial(zoneId, this.mAdColonyInterstitialListener);
    }

    protected void showInterstitial() {
        if (this.mAdColonyInterstitial == null || this.mAdColonyInterstitial.isExpired()) {
            Log.e(TAG, "AdColony interstitial ad is null or has expired");
            this.mHandler.post(new C36411());
            return;
        }
        this.mAdColonyInterstitial.show();
    }

    protected void onInvalidate() {
        if (this.mAdColonyInterstitial != null) {
            this.mAdColonyInterstitialListener = null;
            this.mAdColonyInterstitial.setListener(null);
            this.mAdColonyInterstitial.destroy();
            this.mAdColonyInterstitial = null;
        }
    }

    private boolean isAdColonyConfigured() {
        return !AdColony.getSDKVersion().isEmpty();
    }

    private AdColonyInterstitialListener getAdColonyInterstitialListener() {
        if (this.mAdColonyInterstitialListener != null) {
            return this.mAdColonyInterstitialListener;
        }
        return new C36462();
    }

    private boolean extrasAreValid(Map<String, String> extras) {
        return extras != null && extras.containsKey("clientOptions") && extras.containsKey("appId") && extras.containsKey("allZoneIds") && extras.containsKey("zoneId");
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

    private String[] extractAllZoneIds(Map<String, String> serverExtras) {
        String[] result = Json.jsonArrayToStringArray((String) serverExtras.get("allZoneIds"));
        if (result.length != 0) {
            return result;
        }
        return new String[]{""};
    }
}
