package com.mopub.mobileads;

import android.graphics.Rect;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.util.Dips;

class BannerVisibilityTracker$BannerVisibilityChecker {
    private final Rect mClipRect = new Rect();
    private int mMinVisibleDips;
    private int mMinVisibleMillis;
    private long mStartTimeMillis = Long.MIN_VALUE;

    BannerVisibilityTracker$BannerVisibilityChecker(int minVisibleDips, int minVisibleMillis) {
        this.mMinVisibleDips = minVisibleDips;
        this.mMinVisibleMillis = minVisibleMillis;
    }

    boolean hasBeenVisibleYet() {
        return this.mStartTimeMillis != Long.MIN_VALUE;
    }

    void setStartTimeMillis() {
        this.mStartTimeMillis = SystemClock.uptimeMillis();
    }

    boolean hasRequiredTimeElapsed() {
        if (hasBeenVisibleYet() && SystemClock.uptimeMillis() - this.mStartTimeMillis >= ((long) this.mMinVisibleMillis)) {
            return true;
        }
        return false;
    }

    boolean isVisible(@Nullable View rootView, @Nullable View view) {
        if (view == null || view.getVisibility() != 0 || rootView.getParent() == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.getGlobalVisibleRect(this.mClipRect) || ((long) (Dips.pixelsToIntDips((float) this.mClipRect.width(), view.getContext()) * Dips.pixelsToIntDips((float) this.mClipRect.height(), view.getContext()))) < ((long) this.mMinVisibleDips)) {
            return false;
        }
        return true;
    }
}
