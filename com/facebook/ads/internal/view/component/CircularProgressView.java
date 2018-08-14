package com.facebook.ads.internal.view.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

public class CircularProgressView extends View {
    private final float f5326a = (3.0f * Resources.getSystem().getDisplayMetrics().density);
    private final RectF f5327b = new RectF();
    private final Paint f5328c = new Paint(1);
    private final Paint f5329d;
    private float f5330e = 0.0f;

    public CircularProgressView(Context context) {
        super(context);
        this.f5328c.setStyle(Style.STROKE);
        this.f5328c.setStrokeWidth(this.f5326a);
        this.f5329d = new Paint(1);
        this.f5329d.setStyle(Style.STROKE);
        this.f5329d.setStrokeWidth(this.f5326a);
    }

    public void m7021a(int i, int i2) {
        this.f5328c.setColor(i);
        this.f5329d.setColor(i2);
    }

    @Keep
    public float getProgress() {
        return this.f5330e;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.f5327b, 0.0f, 360.0f, false, this.f5328c);
        canvas.drawArc(this.f5327b, RadialCountdown.START_ANGLE, (this.f5330e * 360.0f) / 100.0f, false, this.f5329d);
    }

    protected void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumHeight(), i2), getDefaultSize(getSuggestedMinimumWidth(), i));
        setMeasuredDimension(min, min);
        this.f5327b.set(((this.f5326a / 2.0f) + 0.0f) + ((float) getPaddingLeft()), ((this.f5326a / 2.0f) + 0.0f) + ((float) getPaddingTop()), (((float) min) - (this.f5326a / 2.0f)) - ((float) getPaddingRight()), (((float) min) - (this.f5326a / 2.0f)) - ((float) getPaddingBottom()));
    }

    @Keep
    public void setProgress(float f) {
        this.f5330e = Math.min(f, 100.0f);
        postInvalidate();
    }

    public void setProgressWithAnimation(float f) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, NotificationCompat.CATEGORY_PROGRESS, new float[]{f});
        ofFloat.setDuration(400);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }
}
