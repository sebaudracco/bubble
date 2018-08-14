package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.BaseNativeAd.NativeEventListener;
import com.mopub.network.TrackingRequest;
import java.util.HashSet;
import java.util.Set;

public class NativeAd {
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private final BaseNativeAd mBaseNativeAd;
    @NonNull
    private final Set<String> mClickTrackers;
    @NonNull
    private final Context mContext;
    @NonNull
    private final Set<String> mImpressionTrackers = new HashSet();
    private boolean mIsClicked;
    private boolean mIsDestroyed;
    @NonNull
    private final MoPubAdRenderer mMoPubAdRenderer;
    @Nullable
    private MoPubNativeEventListener mMoPubNativeEventListener;
    private boolean mRecordedImpression;

    public interface MoPubNativeEventListener {
        void onClick(View view);

        void onImpression(View view);
    }

    class C37411 implements NativeEventListener {
        C37411() {
        }

        public void onAdImpressed() {
            NativeAd.this.recordImpression(null);
        }

        public void onAdClicked() {
            NativeAd.this.handleClick(null);
        }
    }

    public NativeAd(@NonNull Context context, @NonNull String moPubImpressionTrackerUrl, @NonNull String moPubClickTrackerUrl, @NonNull String adUnitId, @NonNull BaseNativeAd baseNativeAd, @NonNull MoPubAdRenderer moPubAdRenderer) {
        this.mContext = context.getApplicationContext();
        this.mAdUnitId = adUnitId;
        this.mImpressionTrackers.add(moPubImpressionTrackerUrl);
        this.mImpressionTrackers.addAll(baseNativeAd.getImpressionTrackers());
        this.mClickTrackers = new HashSet();
        this.mClickTrackers.add(moPubClickTrackerUrl);
        this.mClickTrackers.addAll(baseNativeAd.getClickTrackers());
        this.mBaseNativeAd = baseNativeAd;
        this.mBaseNativeAd.setNativeEventListener(new C37411());
        this.mMoPubAdRenderer = moPubAdRenderer;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        stringBuilder.append("impressionTrackers").append(":").append(this.mImpressionTrackers).append("\n");
        stringBuilder.append("clickTrackers").append(":").append(this.mClickTrackers).append("\n");
        stringBuilder.append("recordedImpression").append(":").append(this.mRecordedImpression).append("\n");
        stringBuilder.append("isClicked").append(":").append(this.mIsClicked).append("\n");
        stringBuilder.append("isDestroyed").append(":").append(this.mIsDestroyed).append("\n");
        return stringBuilder.toString();
    }

    public void setMoPubNativeEventListener(@Nullable MoPubNativeEventListener moPubNativeEventListener) {
        this.mMoPubNativeEventListener = moPubNativeEventListener;
    }

    @NonNull
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    @NonNull
    public BaseNativeAd getBaseNativeAd() {
        return this.mBaseNativeAd;
    }

    @NonNull
    public View createAdView(@NonNull Context context, @Nullable ViewGroup parent) {
        return this.mMoPubAdRenderer.createAdView(context, parent);
    }

    public void renderAdView(View view) {
        this.mMoPubAdRenderer.renderAdView(view, this.mBaseNativeAd);
    }

    @NonNull
    public MoPubAdRenderer getMoPubAdRenderer() {
        return this.mMoPubAdRenderer;
    }

    public void prepare(@NonNull View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.prepare(view);
        }
    }

    public void clear(@NonNull View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.clear(view);
        }
    }

    public void destroy() {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.destroy();
            this.mIsDestroyed = true;
        }
    }

    @VisibleForTesting
    void recordImpression(@Nullable View view) {
        if (!this.mRecordedImpression && !this.mIsDestroyed) {
            TrackingRequest.makeTrackingHttpRequest(this.mImpressionTrackers, this.mContext);
            if (this.mMoPubNativeEventListener != null) {
                this.mMoPubNativeEventListener.onImpression(view);
            }
            this.mRecordedImpression = true;
        }
    }

    @VisibleForTesting
    void handleClick(@Nullable View view) {
        if (!this.mIsClicked && !this.mIsDestroyed) {
            TrackingRequest.makeTrackingHttpRequest(this.mClickTrackers, this.mContext);
            if (this.mMoPubNativeEventListener != null) {
                this.mMoPubNativeEventListener.onClick(view);
            }
            this.mIsClicked = true;
        }
    }
}
