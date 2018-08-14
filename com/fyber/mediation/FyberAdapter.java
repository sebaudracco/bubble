package com.fyber.mediation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings;
import com.fyber.ads.AdFormat;
import com.fyber.currency.VirtualCurrencyErrorResponse;
import com.fyber.currency.VirtualCurrencyResponse;
import com.fyber.mediation.model.FyberAdapterModel;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.RewardedVideoRequester;
import com.fyber.requesters.VirtualCurrencyCallback;
import com.fyber.requesters.VirtualCurrencyRequester;
import com.fyber.utils.FyberLogger;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.unity3d.player.UnityPlayer;
import java.lang.ref.WeakReference;

public class FyberAdapter implements MediationRewardedVideoAdAdapter, VirtualCurrencyCallback, RequestCallback {
    private static final String ADAPTER_VERSION = "1.1.0";
    private static final String COMPATIBLE_VERSION = "8.4.2";
    private static final String TAG = FyberAdapter.class.getSimpleName();
    private static final String UNITY_GAME_OBJECT_NAME = "FyberAdGameObject";
    private WeakReference<Activity> mActivityRef;
    private Fyber$Settings mFyberSettings;
    MediationRewardedVideoAdListener mMediationAdListener;
    private Intent mRvIntent;

    private class FyberReward implements RewardItem {
        private final int mAmount;
        private final String mType;

        public FyberReward(String type, int amount) {
            this.mType = type;
            this.mAmount = amount;
        }

        public int getAmount() {
            return this.mAmount;
        }

        public String getType() {
            return this.mType;
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String userId, MediationRewardedVideoAdListener listener, Bundle serverParameters, Bundle networkExtras) {
        Log.i(TAG, "Starting Fyber-AdMob adapter version 1.1.0");
        if (!COMPATIBLE_VERSION.equals(Fyber.RELEASE_VERSION_STRING)) {
            Log.e(TAG, "Invalid Fyber SDK version. The AdMob-Fyber adapter requires Fyber SDK in version 8.4.2");
        } else if (context instanceof Activity) {
            this.mActivityRef = new WeakReference((Activity) context);
            this.mMediationAdListener = listener;
            String appId = serverParameters.getString(FyberBundleExtras.APP_ID.getKey());
            if (appId == null || appId.isEmpty()) {
                Log.w(TAG, "AppId value is empty. Adapter will NOT start.");
                return;
            }
            String token = serverParameters.getString(FyberBundleExtras.TOKEN.getKey());
            if (token == null) {
                token = "";
            }
            try {
                Class<?> unityPlayerClass = Class.forName("com.unity3d.player.UnityPlayer");
                UnityPlayer.UnitySendMessage(UNITY_GAME_OBJECT_NAME, "SetAppId", appId);
                UnityPlayer.UnitySendMessage(UNITY_GAME_OBJECT_NAME, "SetToken", token);
                UnityPlayer.UnitySendMessage(UNITY_GAME_OBJECT_NAME, "StartFyberSdk", "<not used>");
                this.mMediationAdListener.onInitializationSucceeded(this);
            } catch (ClassNotFoundException e) {
                boolean isDebugLoggingEnabled = false;
                boolean isShowRequestNotificationEnabled = false;
                boolean isShowRewardNotificationEnabled = false;
                if (networkExtras != null) {
                    isDebugLoggingEnabled = networkExtras.getBoolean(FyberNetworkExtras.DEBUG_LOGGING.getKey(), false);
                    isShowRequestNotificationEnabled = networkExtras.getBoolean(FyberNetworkExtras.SHOW_TOAST_AFTER_REQUEST.getKey(), false);
                    isShowRewardNotificationEnabled = networkExtras.getBoolean(FyberNetworkExtras.SHOW_TOAST_AFTER_REWARD.getKey(), false);
                }
                FyberLogger.enableLogging(isDebugLoggingEnabled);
                this.mFyberSettings = Fyber.with(appId, (Activity) this.mActivityRef.get()).withSecurityToken(token).start();
                this.mFyberSettings.notifyUserOnReward(isShowRewardNotificationEnabled);
                this.mFyberSettings.notifyUserOnCompletion(isShowRequestNotificationEnabled);
                this.mMediationAdListener.onInitializationSucceeded(this);
            }
        } else {
            Log.w(TAG, "You need to pass an Activity to Fyber SDK. Adapter will NOT start.");
        }
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle serverParameters, Bundle networkExtras) {
        RewardedVideoRequester.create(this).withVirtualCurrencyRequester(VirtualCurrencyRequester.create(this)).request(((Activity) this.mActivityRef.get()).getApplicationContext());
    }

    public void showVideo() {
        if (this.mRvIntent != null) {
            FyberAdapterModel.getInstance().setFyberAdapter(this);
            ((Activity) this.mActivityRef.get()).startActivity(this.mRvIntent);
            this.mRvIntent = null;
        }
    }

    public boolean isInitialized() {
        return this.mFyberSettings != null;
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onError(VirtualCurrencyErrorResponse virtualCurrencyErrorResponse) {
        FyberLogger.m8449e(TAG, "VCS error received - " + virtualCurrencyErrorResponse.getErrorMessage());
    }

    public void onSuccess(VirtualCurrencyResponse virtualCurrencyResponse) {
        FyberLogger.m8448d(TAG, "VCS coins received - " + virtualCurrencyResponse.getDeltaOfCoins());
        if (this.mMediationAdListener != null) {
            this.mMediationAdListener.onRewarded(this, new FyberReward(virtualCurrencyResponse.getCurrencyName(), (int) virtualCurrencyResponse.getDeltaOfCoins()));
        }
    }

    public void onRequestError(RequestError requestError) {
        this.mRvIntent = null;
        FyberLogger.m8448d(TAG, "RV request error: " + requestError.getDescription());
    }

    public void onAdAvailable(Intent intent) {
        this.mRvIntent = intent;
        this.mRvIntent = new Intent((Context) this.mActivityRef.get(), AdMobFyberVideoActivity.class);
        FyberLogger.m8448d(TAG, "RV is available - FyberAdapter");
        if (this.mMediationAdListener != null) {
            this.mMediationAdListener.onAdLoaded(this);
        }
    }

    public void onAdNotAvailable(AdFormat adFormat) {
        this.mRvIntent = null;
        FyberLogger.m8448d(TAG, "Ad not available");
        if (this.mMediationAdListener != null) {
            this.mMediationAdListener.onAdFailedToLoad(this, 3);
        }
    }
}
