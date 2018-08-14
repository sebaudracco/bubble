package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.silvermob.sdk.BannerAd;
import com.silvermob.sdk.BannerAd.BannerAdListener;
import java.util.Map;
import java.util.logging.Logger;

@Keep
public class SilvermobBanner extends CustomEventBanner implements BannerAdListener {
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private CustomEventBannerListener listener;
    private Logger logger = Logger.getLogger("SilvermobMopubConnector");

    protected void loadBanner(@Nullable Context context, @Nullable CustomEventBannerListener listener, @Nullable Map<String, Object> localExtras, @Nullable Map<String, String> serverExtras) {
        this.listener = listener;
        if (listener == null) {
            this.logger.warning("Failed to request banner ad, [listener] is null");
            return;
        }
        this.listener = listener;
        if (context == null) {
            this.logger.warning("Failed to request banner ad, [context] is null");
            listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (!(context instanceof Activity)) {
            this.logger.warning("Failed to request banner ad, [context] is not an Activity instance");
            listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (localExtras == null) {
            this.logger.warning("Failed to request banner ad, [localExtras] is null");
            listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (serverExtras == null) {
            this.logger.warning("Failed to request banner ad, [serverExtras] is null");
            listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else {
            String placementId = serverExtras.containsKey(PLACEMENT_ID_KEY) ? (String) serverExtras.get(PLACEMENT_ID_KEY) : "";
            if (TextUtils.isEmpty(placementId)) {
                this.logger.warning("Failed to request banner ad, [adUnitId] is empty");
                listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            Object width = localExtras.get(DataKeys.AD_WIDTH);
            if (width == null || !(width instanceof Integer)) {
                this.logger.warning("Failed to request banner ad, requested [width] is incorrect");
                listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            Object height = localExtras.get(DataKeys.AD_HEIGHT);
            if (height == null || !(height instanceof Integer)) {
                this.logger.warning("Failed to request banner ad, requested [height] is incorrect");
                listener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            new BannerAd(context).requestBannerAd(placementId, this);
        }
    }

    protected void onInvalidate() {
    }

    public void onAdLoaded(View view) {
        if (this.listener != null) {
            this.listener.onBannerLoaded(view);
        }
    }

    public void onNoAd() {
        if (this.listener != null) {
            this.listener.onBannerFailed(MoPubErrorCode.NO_FILL);
        }
    }

    public void onClicked() {
        if (this.listener != null) {
            this.listener.onBannerClicked();
        }
    }

    public void onError() {
        if (this.listener != null) {
            this.listener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    public void onShown() {
    }
}
