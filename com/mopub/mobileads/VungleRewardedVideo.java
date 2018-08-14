package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.vungle.publisher.AdConfig;
import java.util.Map;

public class VungleRewardedVideo extends CustomEventRewardedVideo {
    public static final String APP_ID_KEY = "appId";
    public static final String PLACEMENT_IDS_KEY = "pids";
    public static final String PLACEMENT_ID_KEY = "pid";
    private static final String REWARDED_TAG = "Vungle Rewarded: ";
    private static final String[] VUNGLE_DEFAULT_ALL_PLACEMENT_IDS = new String[]{"PLACEMENT_ID_1", "PLACEMENT_ID_2", "..."};
    private static final String VUNGLE_DEFAULT_APP_ID = "YOUR_APP_ID_HERE";
    public static final String VUNGLE_NETWORK_ID_DEFAULT = "vngl_id";
    private static boolean sInitialized;
    private static VungleRouter sVungleRouter;
    private String mAdUnitId;
    private String mAppId;
    private String mCustomerId;
    private boolean mIsPlaying;
    private String mPlacementId;
    private String[] mPlacementIds;
    private VungleRewardedRouterListener mVungleRewardedRouterListener;

    public static class VungleMediationSettings implements MediationSettings {
        @Nullable
        private final String body;
        @Nullable
        private final String closeButtonText;
        private final int flexViewCloseTimeInSec;
        private final boolean isSoundEnabled;
        @Nullable
        private final String keepWatchingButtonText;
        private final int ordinalViewCount;
        @Nullable
        private final String title;
        @Nullable
        private final String userId;

        public static class Builder {
            @Nullable
            private String body;
            @Nullable
            private String closeButtonText;
            private int flexViewCloseTimeInSec = 0;
            private boolean isSoundEnabled = true;
            @Nullable
            private String keepWatchingButtonText;
            private int ordinalViewCount = 0;
            @Nullable
            private String title;
            @Nullable
            private String userId;

            public Builder withUserId(@NonNull String userId) {
                this.userId = userId;
                return this;
            }

            public Builder withCancelDialogTitle(@NonNull String title) {
                this.title = title;
                return this;
            }

            public Builder withCancelDialogBody(@NonNull String body) {
                this.body = body;
                return this;
            }

            public Builder withCancelDialogCloseButton(@NonNull String buttonText) {
                this.closeButtonText = buttonText;
                return this;
            }

            public Builder withCancelDialogKeepWatchingButton(@NonNull String buttonText) {
                this.keepWatchingButtonText = buttonText;
                return this;
            }

            public Builder withSoundEnabled(boolean isSoundEnabled) {
                this.isSoundEnabled = isSoundEnabled;
                return this;
            }

            public Builder withFlexViewCloseTimeInSec(int flexViewCloseTimeInSec) {
                this.flexViewCloseTimeInSec = flexViewCloseTimeInSec;
                return this;
            }

            public Builder withOrdinalViewCount(int ordinalViewCount) {
                this.ordinalViewCount = ordinalViewCount;
                return this;
            }

            public VungleMediationSettings build() {
                return new VungleMediationSettings();
            }
        }

        private VungleMediationSettings(@NonNull Builder builder) {
            this.userId = builder.userId;
            this.title = builder.title;
            this.body = builder.body;
            this.closeButtonText = builder.closeButtonText;
            this.keepWatchingButtonText = builder.keepWatchingButtonText;
            this.isSoundEnabled = builder.isSoundEnabled;
            this.flexViewCloseTimeInSec = builder.flexViewCloseTimeInSec;
            this.ordinalViewCount = builder.ordinalViewCount;
        }
    }

    private class VungleRewardedRouterListener implements VungleRouterListener {
        private VungleRewardedRouterListener() {
        }

        public void onAdEnd(@NonNull String placementReferenceId, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            if (VungleRewardedVideo.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Rewarded: onAdEnd - Placement ID: " + placementReferenceId + ", wasSuccessfulView: " + wasSuccessfulView + ", wasCallToActionClicked: " + wasCallToActionClicked);
                VungleRewardedVideo.this.mIsPlaying = false;
                if (wasSuccessfulView) {
                    MoPubRewardedVideoManager.onRewardedVideoCompleted(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId, MoPubReward.success("", MoPubReward.NO_REWARD_AMOUNT));
                }
                if (wasCallToActionClicked) {
                    MoPubRewardedVideoManager.onRewardedVideoClicked(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId);
                }
                MoPubRewardedVideoManager.onRewardedVideoClosed(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId);
                VungleRewardedVideo.sVungleRouter.removeRouterListener(VungleRewardedVideo.this.mPlacementId);
            }
        }

        public void onAdStart(@NonNull String placementReferenceId) {
            if (VungleRewardedVideo.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Rewarded: onAdStart - Placement ID: " + placementReferenceId);
                VungleRewardedVideo.this.mIsPlaying = true;
                MoPubRewardedVideoManager.onRewardedVideoStarted(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId);
            }
        }

        public void onUnableToPlayAd(@NonNull String placementReferenceId, String reason) {
            if (VungleRewardedVideo.this.mPlacementId.equals(placementReferenceId)) {
                MoPubLog.m12061d("Vungle Rewarded: onUnableToPlayAd - Placement ID: " + placementReferenceId + ", reason: " + reason);
                VungleRewardedVideo.this.mIsPlaying = false;
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId, MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        public void onAdAvailabilityUpdate(@NonNull String placementReferenceId, boolean isAdAvailable) {
            if (VungleRewardedVideo.this.mPlacementId.equals(placementReferenceId) && !VungleRewardedVideo.this.mIsPlaying) {
                if (isAdAvailable) {
                    MoPubLog.m12061d("Vungle Rewarded: rewarded video ad successfully loaded - Placement ID: " + placementReferenceId);
                    MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId);
                    return;
                }
                MoPubLog.m12061d("Vungle Rewarded: rewarded video ad is not loaded - Placement ID: " + placementReferenceId);
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(VungleRewardedVideo.class, VungleRewardedVideo.this.mPlacementId, MoPubErrorCode.NETWORK_NO_FILL);
            }
        }
    }

    public VungleRewardedVideo() {
        sVungleRouter = VungleRouter.getInstance();
        if (this.mVungleRewardedRouterListener == null) {
            this.mVungleRewardedRouterListener = new VungleRewardedRouterListener();
        }
    }

    @Nullable
    public LifecycleListener getLifecycleListener() {
        return sVungleRouter.getLifecycleListener();
    }

    @NonNull
    protected String getAdNetworkId() {
        return this.mPlacementId;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> serverExtras) throws Exception {
        boolean z = true;
        synchronized (VungleRewardedVideo.class) {
            if (sInitialized) {
                z = false;
            } else {
                if (!validateIdsInServerExtras(serverExtras)) {
                    this.mAppId = VUNGLE_DEFAULT_APP_ID;
                    this.mPlacementIds = VUNGLE_DEFAULT_ALL_PLACEMENT_IDS;
                }
                if (!sVungleRouter.isVungleInitialized()) {
                    sVungleRouter.initVungle(launcherActivity, this.mAppId, this.mPlacementIds);
                }
                sInitialized = true;
            }
        }
        return z;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        this.mIsPlaying = false;
        if (validateIdsInServerExtras(serverExtras)) {
            Object adUnitObject = localExtras.get(DataKeys.AD_UNIT_ID_KEY);
            if (adUnitObject instanceof String) {
                this.mAdUnitId = (String) adUnitObject;
            }
            Object customerIdObject = localExtras.get("Rewarded-Ad-Customer-Id");
            if ((customerIdObject instanceof String) && !TextUtils.isEmpty((String) customerIdObject)) {
                this.mCustomerId = (String) customerIdObject;
            }
            if (sVungleRouter.isVungleInitialized()) {
                sVungleRouter.loadAdForPlacement(this.mPlacementId, this.mVungleRewardedRouterListener);
                return;
            }
            MoPubLog.m12061d("Vungle Rewarded: There should not be this case. loadWithSdkInitialized is called before the SDK starts initialization for Placement ID: " + this.mPlacementId);
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(VungleRewardedVideo.class, this.mPlacementId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        this.mPlacementId = VUNGLE_NETWORK_ID_DEFAULT;
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(VungleRewardedVideo.class, this.mPlacementId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected boolean hasVideoAvailable() {
        return sVungleRouter.isAdPlayableForPlacement(this.mPlacementId);
    }

    protected void showVideo() {
        AdConfig adConfig = new AdConfig();
        setUpMediationSettingsForRequest(adConfig);
        sVungleRouter.playAdForPlacement(this.mPlacementId, adConfig);
        this.mIsPlaying = true;
    }

    protected void onInvalidate() {
        MoPubLog.m12061d("Vungle Rewarded: onInvalidate is called for Placement ID:" + this.mPlacementId);
        sVungleRouter.removeRouterListener(this.mPlacementId);
        this.mVungleRewardedRouterListener = null;
    }

    private boolean validateIdsInServerExtras(Map<String, String> serverExtras) {
        boolean isAllDataValid = true;
        if (serverExtras.containsKey("appId")) {
            this.mAppId = (String) serverExtras.get("appId");
            if (this.mAppId.isEmpty()) {
                MoPubLog.m12069w("Vungle Rewarded: App ID is empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Rewarded: AppID is not in serverExtras.");
            isAllDataValid = false;
        }
        if (serverExtras.containsKey("pid")) {
            this.mPlacementId = (String) serverExtras.get("pid");
            if (this.mPlacementId.isEmpty()) {
                MoPubLog.m12069w("Vungle Rewarded: Placement ID for this Ad Unit is empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Rewarded: Placement ID for this Ad Unit is not in serverExtras.");
            isAllDataValid = false;
        }
        if (serverExtras.containsKey("pids")) {
            this.mPlacementIds = ((String) serverExtras.get("pids")).replace(" ", "").split(",", 0);
            if (this.mPlacementIds.length == 0) {
                MoPubLog.m12069w("Vungle Rewarded: Placement IDs are empty.");
                isAllDataValid = false;
            }
        } else {
            MoPubLog.m12069w("Vungle Rewarded: Placement IDs for this Ad Unit is not in serverExtras.");
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
        MoPubLog.m12069w("Vungle Rewarded: Placement IDs for this Ad Unit is not in the array of Placement IDs");
        return false;
    }

    private void setUpMediationSettingsForRequest(AdConfig adConfig) {
        VungleMediationSettings globalMediationSettings = (VungleMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(VungleMediationSettings.class);
        VungleMediationSettings instanceMediationSettings = (VungleMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(VungleMediationSettings.class, this.mAdUnitId);
        if (instanceMediationSettings != null) {
            modifyAdConfig(adConfig, instanceMediationSettings);
        } else if (globalMediationSettings != null) {
            modifyAdConfig(adConfig, globalMediationSettings);
        }
    }

    private void modifyAdConfig(AdConfig adConfig, VungleMediationSettings mediationSettings) {
        if (!TextUtils.isEmpty(mediationSettings.body)) {
            adConfig.setIncentivizedCancelDialogBodyText(mediationSettings.body);
        }
        if (!TextUtils.isEmpty(mediationSettings.closeButtonText)) {
            adConfig.setIncentivizedCancelDialogCloseButtonText(mediationSettings.closeButtonText);
        }
        if (!TextUtils.isEmpty(mediationSettings.keepWatchingButtonText)) {
            adConfig.setIncentivizedCancelDialogKeepWatchingButtonText(mediationSettings.keepWatchingButtonText);
        }
        if (!TextUtils.isEmpty(mediationSettings.title)) {
            adConfig.setIncentivizedCancelDialogTitle(mediationSettings.title);
        }
        if (!TextUtils.isEmpty(this.mCustomerId)) {
            adConfig.setIncentivizedUserId(this.mCustomerId);
        } else if (!TextUtils.isEmpty(mediationSettings.userId)) {
            adConfig.setIncentivizedUserId(mediationSettings.userId);
        }
        adConfig.setSoundEnabled(mediationSettings.isSoundEnabled);
        adConfig.setFlexViewCloseTimeInSec(mediationSettings.flexViewCloseTimeInSec);
        adConfig.setOrdinalViewCount(mediationSettings.ordinalViewCount);
    }
}
