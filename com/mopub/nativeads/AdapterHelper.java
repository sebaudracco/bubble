package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

@Deprecated
public final class AdapterHelper {
    @NonNull
    private final Context mApplicationContext;
    @NonNull
    private final WeakReference<Context> mContext;
    private final int mInterval;
    private final int mStart;

    @Deprecated
    public AdapterHelper(@NonNull Context context, int start, int interval) {
        boolean z = true;
        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkArgument(start >= 0, "start position must be non-negative");
        if (interval < 2) {
            z = false;
        }
        Preconditions.checkArgument(z, "interval must be at least 2");
        this.mContext = new WeakReference(context);
        this.mApplicationContext = context.getApplicationContext();
        this.mStart = start;
        this.mInterval = interval;
    }

    @Deprecated
    @NonNull
    public View getAdView(@Nullable View convertView, @Nullable ViewGroup parent, @Nullable NativeAd nativeAd, @Nullable ViewBinder viewBinder) {
        Context context = (Context) this.mContext.get();
        if (context != null) {
            return NativeAdViewHelper.getAdView(convertView, parent, context, nativeAd);
        }
        MoPubLog.w("Weak reference to Context in AdapterHelper became null. Returning empty view.");
        return new View(this.mApplicationContext);
    }

    @Deprecated
    @NonNull
    public View getAdView(@Nullable View convertView, @Nullable ViewGroup parent, @Nullable NativeAd nativeAd) {
        return getAdView(convertView, parent, nativeAd, null);
    }

    @Deprecated
    public int shiftedCount(int originalCount) {
        return numberOfAdsThatCouldFitWithContent(originalCount) + originalCount;
    }

    @Deprecated
    public int shiftedPosition(int position) {
        return position - numberOfAdsSeenUpToPosition(position);
    }

    @Deprecated
    public boolean isAdPosition(int position) {
        if (position >= this.mStart && (position - this.mStart) % this.mInterval == 0) {
            return true;
        }
        return false;
    }

    private int numberOfAdsSeenUpToPosition(int position) {
        if (position <= this.mStart) {
            return 0;
        }
        return ((int) Math.floor(((double) (position - this.mStart)) / ((double) this.mInterval))) + 1;
    }

    private int numberOfAdsThatCouldFitWithContent(int contentRowCount) {
        if (contentRowCount <= this.mStart) {
            return 0;
        }
        int spacesBetweenAds = this.mInterval - 1;
        if ((contentRowCount - this.mStart) % spacesBetweenAds == 0) {
            return (contentRowCount - this.mStart) / spacesBetweenAds;
        }
        return ((int) Math.floor(((double) (contentRowCount - this.mStart)) / ((double) spacesBetweenAds))) + 1;
    }

    @Deprecated
    @VisibleForTesting
    void clearContext() {
        this.mContext.clear();
    }
}
