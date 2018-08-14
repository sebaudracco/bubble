package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ReflectionTarget;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.CustomEventBannerFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventBannerAdapter implements CustomEventBannerListener {
    public static final int DEFAULT_BANNER_TIMEOUT_DELAY = 10000;
    private Context mContext;
    private CustomEventBanner mCustomEventBanner;
    private final Handler mHandler;
    private int mImpressionMinVisibleDips = Integer.MIN_VALUE;
    private int mImpressionMinVisibleMs = Integer.MIN_VALUE;
    private boolean mInvalidated;
    private boolean mIsVisibilityImpressionTrackingEnabled = false;
    private Map<String, Object> mLocalExtras;
    private MoPubView mMoPubView;
    private Map<String, String> mServerExtras;
    private boolean mStoredAutorefresh;
    private final Runnable mTimeout;
    @Nullable
    private BannerVisibilityTracker mVisibilityTracker;

    public CustomEventBannerAdapter(@NonNull MoPubView moPubView, @NonNull String className, @NonNull Map<String, String> serverExtras, long broadcastIdentifier, @Nullable AdReport adReport) {
        Preconditions.checkNotNull(serverExtras);
        this.mHandler = new Handler();
        this.mMoPubView = moPubView;
        this.mContext = moPubView.getContext();
        this.mTimeout = new 1(this);
        MoPubLog.d("Attempting to invoke custom event: " + className);
        try {
            this.mCustomEventBanner = CustomEventBannerFactory.create(className);
            this.mServerExtras = new TreeMap(serverExtras);
            parseBannerImpressionTrackingHeaders();
            this.mLocalExtras = this.mMoPubView.getLocalExtras();
            if (this.mMoPubView.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubView.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(broadcastIdentifier));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
            this.mLocalExtras.put(DataKeys.AD_WIDTH, Integer.valueOf(this.mMoPubView.getAdWidth()));
            this.mLocalExtras.put(DataKeys.AD_HEIGHT, Integer.valueOf(this.mMoPubView.getAdHeight()));
            this.mLocalExtras.put(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED, Boolean.valueOf(this.mIsVisibilityImpressionTrackingEnabled));
        } catch (Exception e) {
            MoPubLog.d("Couldn't locate or instantiate custom event: " + className + ".");
            this.mMoPubView.loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    @ReflectionTarget
    void loadAd() {
        if (!isInvalidated() && this.mCustomEventBanner != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventBanner.loadBanner(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception e) {
                MoPubLog.d("Loading a custom event banner threw an exception.", e);
                onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    @ReflectionTarget
    void invalidate() {
        if (this.mCustomEventBanner != null) {
            try {
                this.mCustomEventBanner.onInvalidate();
            } catch (Exception e) {
                MoPubLog.d("Invalidating a custom event banner threw an exception", e);
            }
        }
        if (this.mVisibilityTracker != null) {
            try {
                this.mVisibilityTracker.destroy();
            } catch (Exception e2) {
                MoPubLog.d("Destroying a banner visibility tracker threw an exception", e2);
            }
        }
        this.mContext = null;
        this.mCustomEventBanner = null;
        this.mLocalExtras = null;
        this.mServerExtras = null;
        this.mInvalidated = true;
    }

    boolean isInvalidated() {
        return this.mInvalidated;
    }

    @Deprecated
    @VisibleForTesting
    int getImpressionMinVisibleDips() {
        return this.mImpressionMinVisibleDips;
    }

    @Deprecated
    @VisibleForTesting
    int getImpressionMinVisibleMs() {
        return this.mImpressionMinVisibleMs;
    }

    @Deprecated
    @VisibleForTesting
    boolean isVisibilityImpressionTrackingEnabled() {
        return this.mIsVisibilityImpressionTrackingEnabled;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    BannerVisibilityTracker getVisibilityTracker() {
        return this.mVisibilityTracker;
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        if (this.mMoPubView == null || this.mMoPubView.getAdTimeoutDelay() == null || this.mMoPubView.getAdTimeoutDelay().intValue() < 0) {
            return 10000;
        }
        return this.mMoPubView.getAdTimeoutDelay().intValue() * 1000;
    }

    private void parseBannerImpressionTrackingHeaders() {
        String impressionMinVisibleDipsString = (String) this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS);
        String impressionMinVisibleMsString = (String) this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS);
        if (!TextUtils.isEmpty(impressionMinVisibleDipsString) && !TextUtils.isEmpty(impressionMinVisibleMsString)) {
            try {
                this.mImpressionMinVisibleDips = Integer.parseInt(impressionMinVisibleDipsString);
            } catch (NumberFormatException e) {
                MoPubLog.d("Cannot parse integer from header Banner-Impression-Min-Pixels");
            }
            try {
                this.mImpressionMinVisibleMs = Integer.parseInt(impressionMinVisibleMsString);
            } catch (NumberFormatException e2) {
                MoPubLog.d("Cannot parse integer from header Banner-Impression-Min-Ms");
            }
            if (this.mImpressionMinVisibleDips > 0 && this.mImpressionMinVisibleMs >= 0) {
                this.mIsVisibilityImpressionTrackingEnabled = true;
            }
        }
    }

    public void onBannerLoaded(View bannerView) {
        if (!isInvalidated()) {
            cancelTimeout();
            if (this.mMoPubView != null) {
                this.mMoPubView.nativeAdLoaded();
                if (this.mIsVisibilityImpressionTrackingEnabled) {
                    this.mVisibilityTracker = new BannerVisibilityTracker(this.mContext, this.mMoPubView, bannerView, this.mImpressionMinVisibleDips, this.mImpressionMinVisibleMs);
                    this.mVisibilityTracker.setBannerVisibilityTrackerListener(new 2(this));
                }
                this.mMoPubView.setAdContentView(bannerView);
                if (!this.mIsVisibilityImpressionTrackingEnabled && !(bannerView instanceof HtmlBannerWebView)) {
                    this.mMoPubView.trackNativeImpression();
                }
            }
        }
    }

    public void onBannerFailed(MoPubErrorCode errorCode) {
        if (!isInvalidated() && this.mMoPubView != null) {
            if (errorCode == null) {
                errorCode = MoPubErrorCode.UNSPECIFIED;
            }
            cancelTimeout();
            this.mMoPubView.loadFailUrl(errorCode);
        }
    }

    public void onBannerExpanded() {
        if (!isInvalidated()) {
            this.mStoredAutorefresh = this.mMoPubView.getAutorefreshEnabled();
            this.mMoPubView.setAutorefreshEnabled(false);
            this.mMoPubView.adPresentedOverlay();
        }
    }

    public void onBannerCollapsed() {
        if (!isInvalidated()) {
            this.mMoPubView.setAutorefreshEnabled(this.mStoredAutorefresh);
            this.mMoPubView.adClosed();
        }
    }

    public void onBannerClicked() {
        if (!isInvalidated() && this.mMoPubView != null) {
            this.mMoPubView.registerClick();
        }
    }

    public void onLeaveApplication() {
        onBannerClicked();
    }
}
