package com.fyber.p089c.p102b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.view.View;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

/* compiled from: DrawCloseXView */
public final class C2527c extends View {
    private Paint f6264a = new Paint();
    private int f6265b;

    public C2527c(Context context, float f) {
        super(context);
        this.f6264a.setAntiAlias(true);
        this.f6264a.setStrokeWidth(f);
        this.f6264a.setAlpha(178);
        this.f6264a.setColor(-1);
        this.f6264a.setStyle(Style.STROKE);
        this.f6264a.setStrokeJoin(Join.ROUND);
        this.f6265b = (int) (CtaButton.TEXT_SIZE_SP * context.getResources().getDisplayMetrics().density);
    }

    public final void onDraw(Canvas canvas) {
        canvas.drawLine(0.0f, 0.0f, (float) this.f6265b, (float) this.f6265b, this.f6264a);
        canvas.drawLine((float) this.f6265b, 0.0f, 0.0f, (float) this.f6265b, this.f6264a);
    }
}
