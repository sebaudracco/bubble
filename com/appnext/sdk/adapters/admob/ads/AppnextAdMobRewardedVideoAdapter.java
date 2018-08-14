package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import java.io.Serializable;

public class AppnextAdMobRewardedVideoAdapter implements MediationRewardedVideoAdAdapter {
    private MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;
    AppnextRewardedVideoAd ad;

    class C11581 implements OnAdError {
        C11581() {
        }

        public void adError(String str) {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdFailedToLoad(AppnextAdMobRewardedVideoAdapter.this, 0);
            }
        }
    }

    class C11592 implements OnAdClicked {
        C11592() {
        }

        public void adClicked() {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdClicked(AppnextAdMobRewardedVideoAdapter.this);
            }
        }
    }

    class C11603 implements OnAdClosed {
        C11603() {
        }

        public void onAdClosed() {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdClosed(AppnextAdMobRewardedVideoAdapter.this);
            }
        }
    }

    class C11614 implements OnAdLoaded {
        C11614() {
        }

        public void adLoaded(String str) {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdLoaded(AppnextAdMobRewardedVideoAdapter.this);
            }
        }
    }

    class C11625 implements OnAdOpened {
        C11625() {
        }

        public void adOpened() {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdOpened(AppnextAdMobRewardedVideoAdapter.this);
            }
        }
    }

    class C11646 implements OnVideoEnded {

        class C11631 implements RewardItem {
            C11631() {
            }

            public String getType() {
                return "";
            }

            public int getAmount() {
                return 1;
            }
        }

        C11646() {
        }

        public void videoEnded() {
            if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onRewarded(AppnextAdMobRewardedVideoAdapter.this, new C11631());
            }
        }
    }

    private class AppnextRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "321";

        public AppnextRewardedVideoAd(Context context, String str) {
            super(context, str);
        }

        public AppnextRewardedVideoAd(Context context, String str, RewardedConfig rewardedConfig) {
            super(context, str, rewardedConfig);
        }

        public String getTID() {
            return TID;
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        Serializable serializable = null;
        this._mediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        if (bundle != null) {
            try {
                Serializable serializable2 = bundle.getSerializable("AppnextConfiguration");
                serializable = bundle.getSerializable("AppnextRewardPostback");
                String string = bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            } catch (Throwable th) {
                Log.e("AppnextAdMob", "AppnextAdMobRewardedVideoAdapter createAd: " + th.getMessage());
                if (this._mediationRewardedVideoAdListener != null) {
                    this._mediationRewardedVideoAdListener.onInitializationFailed(this, 0);
                    return;
                }
                return;
            }
        }
        serializable2 = null;
        string = str;
        if (bundle2 != null) {
            if (bundle2.containsKey("AppnextConfiguration")) {
                serializable2 = bundle2.getSerializable("AppnextConfiguration");
            }
            if (bundle2.containsKey("AppnextRewardPostback")) {
                serializable = bundle2.getSerializable("AppnextRewardPostback");
            }
            if (bundle2.containsKey(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD)) {
                string = bundle2.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            }
        }
        if (serializable2 == null || !(serializable2 instanceof RewardedConfig)) {
            this.ad = new AppnextRewardedVideoAd(context, string);
        } else {
            this.ad = new AppnextRewardedVideoAd(context, string, (RewardedConfig) serializable2);
        }
        if (serializable != null && (serializable instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        this.ad.setOnAdErrorCallback(new C11581());
        this.ad.setOnAdClickedCallback(new C11592());
        this.ad.setOnAdClosedCallback(new C11603());
        this.ad.setOnAdLoadedCallback(new C11614());
        this.ad.setOnAdOpenedCallback(new C11625());
        this.ad.setOnVideoEndedCallback(new C11646());
        if (this._mediationRewardedVideoAdListener != null) {
            this._mediationRewardedVideoAdListener.onInitializationSucceeded(this);
        }
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.ad != null) {
            this.ad.loadAd();
        }
        Serializable serializable = null;
        if (bundle2 != null) {
            serializable = bundle2.getSerializable("AppnextRewardPostback");
        } else if (bundle != null) {
            serializable = bundle.getSerializable("AppnextRewardPostback");
        }
        if (serializable != null && (serializable instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
    }

    public void showVideo() {
        if (this.ad != null) {
            this.ad.showAd();
        }
    }

    public boolean isInitialized() {
        return this.ad != null;
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
