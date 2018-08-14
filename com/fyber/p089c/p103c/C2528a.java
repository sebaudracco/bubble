package com.fyber.p089c.p103c;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

/* compiled from: CountDownDrawable */
public final class C2528a extends Drawable {
    private Paint f6266a = new Paint(1);
    private float f6267b;

    public C2528a() {
        this.f6266a.setColor(-1);
        this.f6266a.setAntiAlias(true);
        this.f6266a.setStrokeWidth((float) Math.round(1.5f));
        this.f6266a.setStyle(Style.STROKE);
    }

    public final void draw(Canvas canvas) {
        canvas.drawArc(new RectF(getBounds()), RadialCountdown.START_ANGLE, -this.f6267b, false, this.f6266a);
    }

    public final void m8026a(float f) {
        if (this.f6267b >= 0.0f) {
            this.f6267b = f;
            invalidateSelf();
        }
    }

    public final void setAlpha(int i) {
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public final int getOpacity() {
        return 0;
    }
}
