package com.fyber.mediation.vungle;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.fyber.ads.banners.mediation.BannerMediationAdapter;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.annotations.AdapterDefinition;
import com.fyber.mediation.vungle.interstitial.VungleInterstitialMediationAdapter;
import com.fyber.mediation.vungle.rv.VungleVideoMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VungleInitListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@AdapterDefinition(apiVersion = 5, name = "Vungle", sdkFeatures = {"banners", "blended"}, version = "5.3.2-r2")
public class VungleMediationAdapter extends MediationAdapter implements VungleInitListener {
    public static final String ADAPTER_NAME = "Vungle";
    public static final String ADAPTER_VERSION = "5.3.2-r2";
    public static final String APP_ID = "app.id";
    public static final String AUTO_ROTATION_ENABLED = "auto.rotation.enabled";
    public static final String BACK_BUTTON_ENABLED = "back.button.enabled";
    public static final String INCENTIVIZED_CANCEL_DIALOG_BUTTON = "cancel.dialog.button";
    public static final String INCENTIVIZED_CANCEL_DIALOG_TEXT = "cancel.dialog.text";
    public static final String INCENTIVIZED_CANCEL_DIALOG_TITLE = "cancel.dialog.title";
    public static final String INCENTIVIZED_KEEP_WATCHING = "keep.watching.text";
    public static final String INCENTIVIZED_MODE = "incentivized.mode";
    public static final String INT_PLACEMENT_ID = "int.placement.id";
    public static final String RV_PLACEMENT_ID = "rv.placement.id";
    public static final String SOUND_ENABLED = "sound.enabled";
    private static final String TAG = VungleMediationAdapter.class.getSimpleName();
    private Map<String, Object> configs;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private VungleInterstitialMediationAdapter mInterstitialMediationAdapter;
    private VungleMediationAdapter mMediationAdapter = this;
    private VungleVideoMediationAdapter mVideoMediationAdapter;

    public boolean startAdapter(Activity activity, Map<String, Object> configs) {
        FyberLogger.i(TAG, "Starting Vungle adapter");
        this.configs = configs;
        if (VERSION.SDK_INT < 14) {
            FyberLogger.e(TAG, "Vungle Adapter requires Android API level 14+. Adapter won't start.");
            return false;
        }
        String appId = (String) getConfiguration(configs, "app.id", String.class);
        if (StringUtils.nullOrEmpty(appId)) {
            FyberLogger.w(TAG, "'app.id' config is missing. Adapter won’t start");
            return false;
        }
        String rvId = (String) getConfiguration(configs, RV_PLACEMENT_ID, String.class);
        String intId = (String) getConfiguration(configs, INT_PLACEMENT_ID, String.class);
        ArrayList<String> placementsList = new ArrayList();
        if (StringUtils.notNullNorEmpty(rvId)) {
            FyberLogger.d(TAG, "RV placement id found: " + rvId);
            placementsList.add(rvId);
        }
        if (StringUtils.notNullNorEmpty(intId)) {
            FyberLogger.d(TAG, "Int placement id found: " + intId);
            placementsList.add(intId);
        }
        if (placementsList.isEmpty()) {
            FyberLogger.w(TAG, "At least one ad placement (interstitial or rewarded video) is required. Adapter won’t start");
            return false;
        }
        String[] placementsArray = new String[placementsList.size()];
        placementsList.toArray(placementsArray);
        this.mHandler.post(new 1(this, activity, appId, placementsArray, rvId, intId));
        return true;
    }

    @TargetApi(14)
    private void setAndRegisterActivityCallbacks(Application app) {
        app.registerActivityLifecycleCallbacks(new 2(this));
    }

    public String getName() {
        return "Vungle";
    }

    public String getVersion() {
        return ADAPTER_VERSION;
    }

    public VungleVideoMediationAdapter getVideoMediationAdapter() {
        return this.mVideoMediationAdapter;
    }

    public InterstitialMediationAdapter<VungleMediationAdapter> getInterstitialMediationAdapter() {
        return this.mInterstitialMediationAdapter;
    }

    public BannerMediationAdapter<VungleMediationAdapter> getBannerMediationAdapter() {
        return null;
    }

    protected Set<?> getListeners() {
        return null;
    }

    public void onSuccess() {
        FyberLogger.i(TAG, "Vungle initialized successfuly.");
    }

    public void onFailure(Throwable throwable) {
        FyberLogger.w(TAG, "Vungle failed to initialize. \nCause: " + throwable.getCause() + "\nMessage: " + throwable.getMessage());
    }

    public AdConfig getConfiguredAdConfig() {
        AdConfig adConfig = new AdConfig();
        setAutoOrientation(adConfig);
        setSoundEnabled(adConfig);
        setBackButtonEnabled(adConfig);
        setIncentivizedUserId(adConfig);
        setIncentivizedCancelDialogTitle(adConfig);
        setIncentivizedCancelDialogBodyText(adConfig);
        setIncentivizedCancelDialogCloseButtonText(adConfig);
        setIncentivizedCancelDialogKeepWatchingButtonText(adConfig);
        return adConfig;
    }

    private void setAutoOrientation(AdConfig adConfig) {
        Boolean enabled = (Boolean) getConfiguration(this.configs, AUTO_ROTATION_ENABLED, Boolean.class);
        if (enabled != null) {
            adConfig.setOrientation(enabled.booleanValue() ? Orientation.autoRotate : Orientation.matchVideo);
        }
    }

    private void setSoundEnabled(AdConfig adConfig) {
        Boolean enabled = (Boolean) getConfiguration(this.configs, SOUND_ENABLED, Boolean.class);
        if (enabled != null) {
            adConfig.setSoundEnabled(enabled.booleanValue());
        }
    }

    private void setBackButtonEnabled(AdConfig adConfig) {
        Boolean enabled = (Boolean) getConfiguration(this.configs, BACK_BUTTON_ENABLED, Boolean.class);
        if (enabled != null) {
            adConfig.setBackButtonImmediatelyEnabled(enabled.booleanValue());
        }
    }

    private void setIncentivizedUserId(AdConfig adConfig) {
        String userId = getUserId();
        if (StringUtils.notNullNorEmpty(getUserId())) {
            adConfig.setIncentivizedUserId(userId);
        }
    }

    private void setIncentivizedCancelDialogTitle(AdConfig adConfig) {
        String text = (String) getConfiguration(this.configs, INCENTIVIZED_CANCEL_DIALOG_TITLE, String.class);
        if (StringUtils.notNullNorEmpty(text)) {
            adConfig.setIncentivizedCancelDialogTitle(text);
        }
    }

    private void setIncentivizedCancelDialogBodyText(AdConfig adConfig) {
        String text = (String) getConfiguration(this.configs, INCENTIVIZED_CANCEL_DIALOG_TEXT, String.class);
        if (StringUtils.notNullNorEmpty(text)) {
            adConfig.setIncentivizedCancelDialogBodyText(text);
        }
    }

    private void setIncentivizedCancelDialogCloseButtonText(AdConfig adConfig) {
        String text = (String) getConfiguration(this.configs, INCENTIVIZED_CANCEL_DIALOG_BUTTON, String.class);
        if (StringUtils.notNullNorEmpty(text)) {
            adConfig.setIncentivizedCancelDialogCloseButtonText(text);
        }
    }

    private void setIncentivizedCancelDialogKeepWatchingButtonText(AdConfig adConfig) {
        String text = (String) getConfiguration(this.configs, INCENTIVIZED_KEEP_WATCHING, String.class);
        if (StringUtils.notNullNorEmpty(text)) {
            adConfig.setIncentivizedCancelDialogKeepWatchingButtonText(text);
        }
    }
}
