package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.vungle.publisher.AdConfig;
import java.util.Map;

public class VungleInterstitial extends CustomEventInterstitial {
    public static final String APP_ID_KEY = "appId";
    public static final String FLEX_VIEW_CLOSE_TIME_KEY = "vungleFlexViewCloseTimeInSec";
    private static final String INTERSTITIAL_TAG = "Vungle Interstitial: ";
    public static final String ORDINAL_VIEW_COUNT_KEY = "vungleOrdinalViewCount";
    public static final String PLACEMENT_IDS_KEY = "pids";
    public static final String PLACEMENT_ID_KEY = "pid";
    public static final String SOUND_ENABLED_KEY = "vungleSoundEnabled";
    private static VungleRouter sVungleRouter;
    private AdConfig mAdConfig;
    private String mAppId;
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsPlaying;
    private String mPlacementId;
    private String[] mPlacementIds;
    private VungleInterstitialRouterListener mVungleRouterListener;

    class C36671 implements Runnable {
        C36671() {
        }

        public void run() {
            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        }
    }

    class C36682 implements Runnable {
        C36682() {
        }

        public void run() {
            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    private class VungleInterstitialRouterListener implements VungleRouterListener {

        class C36702 implements Runnable {
            C36702() {
            }

            public void run() {
                VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialShown();
            }
        }

        class C36713 implements Runnable {
            C36713() {
            }

            public void run() {
                VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        class C36724 implements Runnable {
            C36724() {
            }

            public void run() {
                VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialLoaded();
            }
        }

        class C36735 implements Runnable {
            C36735() {
            }

            public void run() {
                VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        private VungleInterstitialRouterListener() {
        }

        public void onAdEnd(@NonNull String placementReferenceId, boolean wasSuccessfulView, final boolean wasCallToActionClicked) {
            if (VungleInterstitial.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Interstitial: onAdEnd - Placement ID: " + placementReferenceId + ", wasSuccessfulView: " + wasSuccessfulView + ", wasCallToActionClicked: " + wasCallToActionClicked);
                VungleInterstitial.this.mIsPlaying = false;
                VungleInterstitial.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (wasCallToActionClicked) {
                            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialClicked();
                        }
                        VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialDismissed();
                    }
                });
                VungleInterstitial.sVungleRouter.removeRouterListener(VungleInterstitial.this.mPlacementId);
            }
        }

        public void onAdStart(@NonNull String placementReferenceId) {
            if (VungleInterstitial.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Interstitial: onAdStart - Placement ID: " + placementReferenceId);
                VungleInterstitial.this.mIsPlaying = true;
                VungleInterstitial.this.mHandler.post(new C36702());
            }
        }

        public void onUnableToPlayAd(@NonNull String placementReferenceId, String reason) {
            if (VungleInterstitial.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Interstitial: onUnableToPlayAd - Placement ID: " + placementReferenceId + ", reason: " + reason);
                VungleInterstitial.this.mIsPlaying = false;
                VungleInterstitial.this.mHandler.post(new C36713());
            }
        }

        public void onAdAvailabilityUpdate(@NonNull String placementReferenceId, boolean isAdAvailable) {
            if (VungleInterstitial.this.mPlacementId.equals(placementReferenceId) && !VungleInterstitial.this.mIsPlaying) {
                if (isAdAvailable) {
                    MoPubLog.m12061d("Vungle Interstitial: interstitial ad successfully loaded - Placement ID: " + placementReferenceId);
                    VungleInterstitial.this.mHandler.post(new C36724());
                    return;
                }
                MoPubLog.m12061d("Vungle Interstitial: interstitial ad is not loaded - Placement ID: " + placementReferenceId);
                VungleInterstitial.this.mHandler.post(new C36735());
            }
        }
    }

    public VungleInterstitial() {
        sVungleRouter = VungleRouter.getInstance();
    }

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        this.mIsPlaying = false;
        if (context == null) {
            this.mHandler.post(new C36671());
        } else if (validateIdsInServerExtras(serverExtras)) {
            if (this.mVungleRouterListener == null) {
                this.mVungleRouterListener = new VungleInterstitialRouterListener();
            }
            if (!sVungleRouter.isVungleInitialized()) {
                sVungleRouter.initVungle(context, this.mAppId, this.mPlacementIds);
            }
            if (localExtras != null) {
                this.mAdConfig = new AdConfig();
                Object isSoundEnabled = localExtras.get(SOUND_ENABLED_KEY);
                if (isSoundEnabled instanceof Boolean) {
                    this.mAdConfig.setSoundEnabled(((Boolean) isSoundEnabled).booleanValue());
                }
                Object flexViewCloseTimeInSec = localExtras.get(FLEX_VIEW_CLOSE_TIME_KEY);
                if (flexViewCloseTimeInSec instanceof Integer) {
                    this.mAdConfig.setFlexViewCloseTimeInSec(((Integer) flexViewCloseTimeInSec).intValue());
                }
                Object ordinalViewCount = localExtras.get(ORDINAL_VIEW_COUNT_KEY);
                if (ordinalViewCount instanceof Integer) {
                    this.mAdConfig.setOrdinalViewCount(((Integer) ordinalViewCount).intValue());
                }
            }
            sVungleRouter.loadAdForPlacement(this.mPlacementId, this.mVungleRouterListener);
        } else {
            this.mHandler.post(new C36682());
        }
    }

    protected void showInterstitial() {
        if (sVungleRouter.isAdPlayableForPlacement(this.mPlacementId)) {
            sVungleRouter.playAdForPlacement(this.mPlacementId, this.mAdConfig);
            this.mIsPlaying = true;
            return;
        }
        MoPubLog.m12061d("Vungle Interstitial: SDK tried to show a Vungle interstitial ad before it finished loading. Please try again.");
        this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
    }

    protected void onInvalidate() {
        MoPubLog.m12061d("Vungle Interstitial: onInvalidate is called for Placement ID:" + this.mPlacementId);
        sVungleRouter.removeRouterListener(this.mPlacementId);
        this.mVungleRouterListener = null;
        this.mAdConfig = null;
    }

    private boolean validateIdsInServerExtras(Map<String, String> serverExtras) {
        boolean isAllDataValid = true;
        if (serverExtras.containsKey("appId")) {
            this.mAppId = (String) serverExtras.get("appId");
            if (this.mAppId.isEmpty()) {
                MoPubLog.m12069w("Vungle Interstitial: App ID is empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Interstitial: AppID is not in serverExtras.");
            isAllDataValid = false;
        }
        if (serverExtras.containsKey("pid")) {
            this.mPlacementId = (String) serverExtras.get("pid");
            if (this.mPlacementId.isEmpty()) {
                MoPubLog.m12069w("Vungle Interstitial: Placement ID for this Ad Unit is empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Interstitial: Placement ID for this Ad Unit is not in serverExtras.");
            isAllDataValid = false;
        }
        if (serverExtras.containsKey("pids")) {
            this.mPlacementIds = ((String) serverExtras.get("pids")).replace(" ", "").split(",", 0);
            if (this.mPlacementIds.length == 0) {
                MoPubLog.m12069w("Vungle Interstitial: Placement IDs are empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Interstitial: Placement IDs for this Ad Unit is not in serverExtras.");
            isAllDataValid = false;
        }
        if (!isAllDataValid) {
            return isAllDataValid;
        }
        boolean foundInList = false;
        for (String pid : this.mPlacementIds) {
            if (pid.equals(this.mPlacementId)) {
                foundInList = true;
            }
        }
        if (foundInList) {
            return isAllDataValid;
        }
        MoPubLog.m12069w("Vungle Interstitial: Placement IDs for this Ad Unit is not in the array of Placement IDs");
        return false;
    }
}
