package com.mopub.nativeads;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;

@VisibleForTesting
class NativeFullScreenVideoView$LoadingBackground extends Drawable {
    @NonNull
    private final RectF mButtonRect;
    @VisibleForTesting
    final int mCornerRadiusPx;
    @NonNull
    private final Paint mPaint;

    NativeFullScreenVideoView$LoadingBackground(@NonNull Context context) {
        this(context, new RectF(), new Paint());
    }

    NativeFullScreenVideoView$LoadingBackground(@NonNull Context context, @NonNull RectF rectF, @NonNull Paint paint) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(rectF);
        Preconditions.checkNotNull(paint);
        this.mButtonRect = rectF;
        this.mPaint = paint;
        this.mPaint.setColor(-16777216);
        this.mPaint.setAlpha(128);
        this.mPaint.setAntiAlias(true);
        this.mCornerRadiusPx = Dips.asIntPixels(5.0f, context);
    }

    public void draw(Canvas canvas) {
        this.mButtonRect.set(getBounds());
        canvas.drawRoundRect(this.mButtonRect, (float) this.mCornerRadiusPx, (float) this.mCornerRadiusPx, this.mPaint);
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter cf) {
    }

    public int getOpacity() {
        return 0;
    }
}
