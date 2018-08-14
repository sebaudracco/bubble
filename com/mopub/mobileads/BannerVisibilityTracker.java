package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import java.lang.ref.WeakReference;

class BannerVisibilityTracker {
    private static final int VISIBILITY_THROTTLE_MILLIS = 100;
    @Nullable
    private BannerVisibilityTrackerListener mBannerVisibilityTrackerListener;
    private boolean mIsImpTrackerFired;
    private boolean mIsVisibilityScheduled;
    @NonNull
    @VisibleForTesting
    final OnPreDrawListener mOnPreDrawListener = new 1(this);
    @NonNull
    private final View mRootView;
    @NonNull
    private final View mTrackedView;
    @NonNull
    private final BannerVisibilityChecker mVisibilityChecker;
    @NonNull
    private final Handler mVisibilityHandler = new Handler();
    @NonNull
    private final BannerVisibilityRunnable mVisibilityRunnable = new BannerVisibilityRunnable(this);
    @NonNull
    @VisibleForTesting
    WeakReference<ViewTreeObserver> mWeakViewTreeObserver = new WeakReference(null);

    @VisibleForTesting
    public BannerVisibilityTracker(@NonNull Context context, @NonNull View rootView, @NonNull View trackedView, int minVisibleDips, int minVisibleMillis) {
        Preconditions.checkNotNull(rootView);
        Preconditions.checkNotNull(trackedView);
        this.mRootView = rootView;
        this.mTrackedView = trackedView;
        this.mVisibilityChecker = new BannerVisibilityChecker(minVisibleDips, minVisibleMillis);
        setViewTreeObserver(context, this.mTrackedView);
    }

    private void setViewTreeObserver(@Nullable Context context, @Nullable View view) {
        ViewTreeObserver originalViewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (originalViewTreeObserver == null || !originalViewTreeObserver.isAlive()) {
            View rootView = Views.getTopmostView(context, view);
            if (rootView == null) {
                MoPubLog.d("Unable to set Visibility Tracker due to no available root view.");
                return;
            }
            ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                this.mWeakViewTreeObserver = new WeakReference(viewTreeObserver);
                viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
                return;
            }
            MoPubLog.w("Visibility Tracker was unable to track views because the root view tree observer was not alive");
        }
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    BannerVisibilityTrackerListener getBannerVisibilityTrackerListener() {
        return this.mBannerVisibilityTrackerListener;
    }

    void setBannerVisibilityTrackerListener(@Nullable BannerVisibilityTrackerListener bannerVisibilityTrackerListener) {
        this.mBannerVisibilityTrackerListener = bannerVisibilityTrackerListener;
    }

    void destroy() {
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mWeakViewTreeObserver.clear();
        this.mBannerVisibilityTrackerListener = null;
    }

    void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    BannerVisibilityChecker getBannerVisibilityChecker() {
        return this.mVisibilityChecker;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    Handler getVisibilityHandler() {
        return this.mVisibilityHandler;
    }

    @Deprecated
    @VisibleForTesting
    boolean isVisibilityScheduled() {
        return this.mIsVisibilityScheduled;
    }

    @Deprecated
    @VisibleForTesting
    boolean isImpTrackerFired() {
        return this.mIsImpTrackerFired;
    }
}
