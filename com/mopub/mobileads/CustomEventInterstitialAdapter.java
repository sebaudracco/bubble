package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.WebViewCacheService.Config;
import com.mopub.mobileads.factories.CustomEventInterstitialFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventInterstitialAdapter implements CustomEventInterstitialListener {
    public static final int DEFAULT_INTERSTITIAL_TIMEOUT_DELAY = 30000;
    private long mBroadcastIdentifier;
    private Context mContext;
    private CustomEventInterstitial mCustomEventInterstitial;
    private CustomEventInterstitialAdapterListener mCustomEventInterstitialAdapterListener;
    private final Handler mHandler = new Handler();
    private boolean mInvalidated;
    private Map<String, Object> mLocalExtras;
    private final MoPubInterstitial mMoPubInterstitial;
    private Map<String, String> mServerExtras;
    private final Runnable mTimeout;

    public CustomEventInterstitialAdapter(@NonNull MoPubInterstitial moPubInterstitial, @NonNull String className, @NonNull Map<String, String> serverExtras, long broadcastIdentifier, @Nullable AdReport adReport) {
        Preconditions.checkNotNull(serverExtras);
        this.mMoPubInterstitial = moPubInterstitial;
        this.mBroadcastIdentifier = broadcastIdentifier;
        this.mContext = this.mMoPubInterstitial.getActivity();
        this.mTimeout = new 1(this);
        MoPubLog.d("Attempting to invoke custom event: " + className);
        try {
            this.mCustomEventInterstitial = CustomEventInterstitialFactory.create(className);
            this.mServerExtras = new TreeMap(serverExtras);
            this.mLocalExtras = this.mMoPubInterstitial.getLocalExtras();
            if (this.mMoPubInterstitial.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubInterstitial.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(broadcastIdentifier));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
        } catch (Exception e) {
            MoPubLog.d("Couldn't locate or instantiate custom event: " + className + ".");
            this.mMoPubInterstitial.onCustomEventInterstitialFailed(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    void loadInterstitial() {
        if (!isInvalidated() && this.mCustomEventInterstitial != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventInterstitial.loadInterstitial(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception e) {
                MoPubLog.d("Loading a custom event interstitial threw an exception.", e);
                onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    void showInterstitial() {
        if (!isInvalidated() && this.mCustomEventInterstitial != null) {
            try {
                this.mCustomEventInterstitial.showInterstitial();
            } catch (Exception e) {
                MoPubLog.d("Showing a custom event interstitial threw an exception.", e);
                onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    void invalidate() {
        if (this.mCustomEventInterstitial != null) {
            try {
                this.mCustomEventInterstitial.onInvalidate();
            } catch (Exception e) {
                MoPubLog.d("Invalidating a custom event interstitial threw an exception.", e);
            }
        }
        this.mCustomEventInterstitial = null;
        this.mContext = null;
        this.mServerExtras = null;
        this.mLocalExtras = null;
        this.mCustomEventInterstitialAdapterListener = null;
        Config config = WebViewCacheService.popWebViewConfig(Long.valueOf(this.mBroadcastIdentifier));
        if (config != null) {
            config.getWebView().destroy();
        }
        this.mInvalidated = true;
    }

    boolean isInvalidated() {
        return this.mInvalidated;
    }

    void setAdapterListener(CustomEventInterstitialAdapterListener listener) {
        this.mCustomEventInterstitialAdapterListener = listener;
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        if (this.mMoPubInterstitial == null || this.mMoPubInterstitial.getAdTimeoutDelay() == null || this.mMoPubInterstitial.getAdTimeoutDelay().intValue() < 0) {
            return 30000;
        }
        return this.mMoPubInterstitial.getAdTimeoutDelay().intValue() * 1000;
    }

    public void onInterstitialLoaded() {
        if (!isInvalidated()) {
            cancelTimeout();
            if (this.mCustomEventInterstitialAdapterListener != null) {
                this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialLoaded();
            }
        }
    }

    public void onInterstitialFailed(MoPubErrorCode errorCode) {
        if (!isInvalidated() && this.mCustomEventInterstitialAdapterListener != null) {
            if (errorCode == null) {
                errorCode = MoPubErrorCode.UNSPECIFIED;
            }
            cancelTimeout();
            this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialFailed(errorCode);
        }
    }

    public void onInterstitialShown() {
        if (!isInvalidated() && this.mCustomEventInterstitialAdapterListener != null) {
            this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialShown();
        }
    }

    public void onInterstitialClicked() {
        if (!isInvalidated() && this.mCustomEventInterstitialAdapterListener != null) {
            this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialClicked();
        }
    }

    public void onLeaveApplication() {
        onInterstitialClicked();
    }

    public void onInterstitialDismissed() {
        if (!isInvalidated() && this.mCustomEventInterstitialAdapterListener != null) {
            this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialDismissed();
        }
    }

    @Deprecated
    void setCustomEventInterstitial(CustomEventInterstitial interstitial) {
        this.mCustomEventInterstitial = interstitial;
    }
}
