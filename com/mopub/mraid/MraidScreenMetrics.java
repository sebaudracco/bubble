package com.mopub.mraid;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.mopub.common.util.Dips;

class MraidScreenMetrics {
    @NonNull
    private final Context mContext;
    @NonNull
    private final Rect mCurrentAdRect = new Rect();
    @NonNull
    private final Rect mCurrentAdRectDips = new Rect();
    @NonNull
    private final Rect mDefaultAdRect = new Rect();
    @NonNull
    private final Rect mDefaultAdRectDips = new Rect();
    private final float mDensity;
    @NonNull
    private final Rect mRootViewRect = new Rect();
    @NonNull
    private final Rect mRootViewRectDips = new Rect();
    @NonNull
    private final Rect mScreenRect = new Rect();
    @NonNull
    private final Rect mScreenRectDips = new Rect();

    MraidScreenMetrics(Context context, float density) {
        this.mContext = context.getApplicationContext();
        this.mDensity = density;
    }

    private void convertToDips(Rect sourceRect, Rect outRect) {
        outRect.set(Dips.pixelsToIntDips((float) sourceRect.left, this.mContext), Dips.pixelsToIntDips((float) sourceRect.top, this.mContext), Dips.pixelsToIntDips((float) sourceRect.right, this.mContext), Dips.pixelsToIntDips((float) sourceRect.bottom, this.mContext));
    }

    public float getDensity() {
        return this.mDensity;
    }

    void setScreenSize(int width, int height) {
        this.mScreenRect.set(0, 0, width, height);
        convertToDips(this.mScreenRect, this.mScreenRectDips);
    }

    @NonNull
    Rect getScreenRect() {
        return this.mScreenRect;
    }

    @NonNull
    Rect getScreenRectDips() {
        return this.mScreenRectDips;
    }

    void setRootViewPosition(int x, int y, int width, int height) {
        this.mRootViewRect.set(x, y, x + width, y + height);
        convertToDips(this.mRootViewRect, this.mRootViewRectDips);
    }

    @NonNull
    Rect getRootViewRect() {
        return this.mRootViewRect;
    }

    @NonNull
    Rect getRootViewRectDips() {
        return this.mRootViewRectDips;
    }

    void setCurrentAdPosition(int x, int y, int width, int height) {
        this.mCurrentAdRect.set(x, y, x + width, y + height);
        convertToDips(this.mCurrentAdRect, this.mCurrentAdRectDips);
    }

    @NonNull
    Rect getCurrentAdRect() {
        return this.mCurrentAdRect;
    }

    @NonNull
    Rect getCurrentAdRectDips() {
        return this.mCurrentAdRectDips;
    }

    void setDefaultAdPosition(int x, int y, int width, int height) {
        this.mDefaultAdRect.set(x, y, x + width, y + height);
        convertToDips(this.mDefaultAdRect, this.mDefaultAdRectDips);
    }

    @NonNull
    Rect getDefaultAdRect() {
        return this.mDefaultAdRect;
    }

    @NonNull
    Rect getDefaultAdRectDips() {
        return this.mDefaultAdRectDips;
    }
}
