package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import java.util.Map;

public class FacebookBanner extends CustomEventBanner implements AdListener {
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private CustomEventBannerListener mBannerListener;
    private AdView mFacebookBanner;

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.mBannerListener = customEventBannerListener;
        if (serverExtrasAreValid(serverExtras)) {
            String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
            if (localExtrasAreValid(localExtras)) {
                AdSize adSize = calculateAdSize(((Integer) localExtras.get(DataKeys.AD_WIDTH)).intValue(), ((Integer) localExtras.get(DataKeys.AD_HEIGHT)).intValue());
                if (adSize == null) {
                    this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                    return;
                }
                AdSettings.setMediationService("MOPUB_4.20.0");
                this.mFacebookBanner = new AdView(context, placementId, adSize);
                this.mFacebookBanner.setAdListener(this);
                this.mFacebookBanner.disableAutoRefresh();
                this.mFacebookBanner.loadAd();
                return;
            }
            this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected void onInvalidate() {
        if (this.mFacebookBanner != null) {
            Views.removeFromParent(this.mFacebookBanner);
            this.mFacebookBanner.destroy();
            this.mFacebookBanner = null;
        }
    }

    public void onAdLoaded(Ad ad) {
        this.mBannerListener.onBannerLoaded(this.mFacebookBanner);
    }

    public void onError(Ad ad, AdError error) {
        if (error == AdError.NO_FILL) {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
        } else if (error == AdError.INTERNAL_ERROR) {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        } else {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.UNSPECIFIED);
        }
    }

    public void onAdClicked(Ad ad) {
        this.mBannerListener.onBannerClicked();
    }

    public void onLoggingImpression(Ad ad) {
        MoPubLog.d("Facebook banner ad logged impression.");
    }

    private boolean serverExtrasAreValid(Map<String, String> serverExtras) {
        String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
        return placementId != null && placementId.length() > 0;
    }

    private boolean localExtrasAreValid(@NonNull Map<String, Object> localExtras) {
        return (localExtras.get(DataKeys.AD_WIDTH) instanceof Integer) && (localExtras.get(DataKeys.AD_HEIGHT) instanceof Integer);
    }

    @Nullable
    private AdSize calculateAdSize(int width, int height) {
        if (height <= AdSize.BANNER_320_50.getHeight()) {
            return AdSize.BANNER_320_50;
        }
        if (height <= AdSize.BANNER_HEIGHT_90.getHeight()) {
            return AdSize.BANNER_HEIGHT_90;
        }
        if (height <= AdSize.RECTANGLE_HEIGHT_250.getHeight()) {
            return AdSize.RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    @Deprecated
    AdView getAdView() {
        return this.mFacebookBanner;
    }
}
