package com.fyber.p089c.p105e;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

/* compiled from: SpinnerDrawable */
public final class C2544a extends Drawable {
    private Paint f6359a = new Paint();

    public C2544a() {
        this.f6359a.setColor(-1);
        this.f6359a.setAntiAlias(true);
        this.f6359a.setAlpha(178);
        this.f6359a.setStrokeWidth((float) Math.round(1.5f));
        this.f6359a.setStyle(Style.STROKE);
    }

    public final void draw(@NonNull Canvas canvas) {
        canvas.drawArc(new RectF(getBounds()), RadialCountdown.START_ANGLE, -320.0f, false, this.f6359a);
    }

    public final void setAlpha(int i) {
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public final int getOpacity() {
        return 0;
    }
}
