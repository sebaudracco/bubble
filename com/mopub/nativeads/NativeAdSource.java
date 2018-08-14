package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsgeyser.sdk.configuration.Constants;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import java.util.ArrayList;
import java.util.List;

class NativeAdSource {
    private static final int CACHE_LIMIT = 1;
    private static final int EXPIRATION_TIME_MILLISECONDS = 14400000;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    @VisibleForTesting
    static final int[] RETRY_TIME_ARRAY_MILLISECONDS = new int[]{1000, 3000, 5000, 25000, 60000, MAXIMUM_RETRY_TIME_MILLISECONDS};
    @NonNull
    private final AdRendererRegistry mAdRendererRegistry;
    @Nullable
    private AdSourceListener mAdSourceListener;
    @VisibleForTesting
    int mCurrentRetries;
    @Nullable
    private MoPubNative mMoPubNative;
    @NonNull
    private final MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @NonNull
    private final List<TimestampWrapper<NativeAd>> mNativeAdCache;
    @NonNull
    private final Handler mReplenishCacheHandler;
    @NonNull
    private final Runnable mReplenishCacheRunnable;
    @VisibleForTesting
    boolean mRequestInFlight;
    @Nullable
    private RequestParameters mRequestParameters;
    @VisibleForTesting
    boolean mRetryInFlight;
    @VisibleForTesting
    int mSequenceNumber;

    NativeAdSource() {
        this(new ArrayList(1), new Handler(), new AdRendererRegistry());
    }

    @VisibleForTesting
    NativeAdSource(@NonNull List<TimestampWrapper<NativeAd>> nativeAdCache, @NonNull Handler replenishCacheHandler, @NonNull AdRendererRegistry adRendererRegistry) {
        this.mNativeAdCache = nativeAdCache;
        this.mReplenishCacheHandler = replenishCacheHandler;
        this.mReplenishCacheRunnable = new 1(this);
        this.mAdRendererRegistry = adRendererRegistry;
        this.mMoPubNativeNetworkListener = new 2(this);
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    int getAdRendererCount() {
        return this.mAdRendererRegistry.getAdRendererCount();
    }

    public int getViewTypeForAd(@NonNull NativeAd nativeAd) {
        return this.mAdRendererRegistry.getViewTypeForAd(nativeAd);
    }

    void registerAdRenderer(@NonNull MoPubAdRenderer moPubNativeAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubNativeAdRenderer);
        if (this.mMoPubNative != null) {
            this.mMoPubNative.registerAdRenderer(moPubNativeAdRenderer);
        }
    }

    @Nullable
    public MoPubAdRenderer getAdRendererForViewType(int viewType) {
        return this.mAdRendererRegistry.getRendererForViewType(viewType);
    }

    void setAdSourceListener(@Nullable AdSourceListener adSourceListener) {
        this.mAdSourceListener = adSourceListener;
    }

    void loadAds(@NonNull Activity activity, @NonNull String adUnitId, RequestParameters requestParameters) {
        loadAds(requestParameters, new MoPubNative(activity, adUnitId, this.mMoPubNativeNetworkListener));
    }

    @VisibleForTesting
    void loadAds(RequestParameters requestParameters, MoPubNative moPubNative) {
        clear();
        for (MoPubAdRenderer renderer : this.mAdRendererRegistry.getRendererIterable()) {
            moPubNative.registerAdRenderer(renderer);
        }
        this.mRequestParameters = requestParameters;
        this.mMoPubNative = moPubNative;
        replenishCache();
    }

    void clear() {
        if (this.mMoPubNative != null) {
            this.mMoPubNative.destroy();
            this.mMoPubNative = null;
        }
        this.mRequestParameters = null;
        for (TimestampWrapper<NativeAd> timestampWrapper : this.mNativeAdCache) {
            ((NativeAd) timestampWrapper.mInstance).destroy();
        }
        this.mNativeAdCache.clear();
        this.mReplenishCacheHandler.removeMessages(0);
        this.mRequestInFlight = false;
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    @Nullable
    NativeAd dequeueAd() {
        long now = SystemClock.uptimeMillis();
        if (!(this.mRequestInFlight || this.mRetryInFlight)) {
            this.mReplenishCacheHandler.post(this.mReplenishCacheRunnable);
        }
        while (!this.mNativeAdCache.isEmpty()) {
            TimestampWrapper<NativeAd> responseWrapper = (TimestampWrapper) this.mNativeAdCache.remove(0);
            if (now - responseWrapper.mCreatedTimestamp < Constants.PULL_ALARM_PERIOD) {
                return (NativeAd) responseWrapper.mInstance;
            }
        }
        return null;
    }

    @VisibleForTesting
    void updateRetryTime() {
        if (this.mCurrentRetries < RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.mCurrentRetries++;
        }
    }

    @VisibleForTesting
    void resetRetryTime() {
        this.mCurrentRetries = 0;
    }

    @VisibleForTesting
    int getRetryTime() {
        if (this.mCurrentRetries >= RETRY_TIME_ARRAY_MILLISECONDS.length) {
            this.mCurrentRetries = RETRY_TIME_ARRAY_MILLISECONDS.length - 1;
        }
        return RETRY_TIME_ARRAY_MILLISECONDS[this.mCurrentRetries];
    }

    @VisibleForTesting
    void replenishCache() {
        if (!this.mRequestInFlight && this.mMoPubNative != null && this.mNativeAdCache.size() < 1) {
            this.mRequestInFlight = true;
            this.mMoPubNative.makeRequest(this.mRequestParameters, Integer.valueOf(this.mSequenceNumber));
        }
    }

    @Deprecated
    @VisibleForTesting
    void setMoPubNative(MoPubNative moPubNative) {
        this.mMoPubNative = moPubNative;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
