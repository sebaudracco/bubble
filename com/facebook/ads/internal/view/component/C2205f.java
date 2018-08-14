package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p056q.p057a.C2133v;

public class C2205f extends RelativeLayout {
    private static final int f5368a = ((int) (4.0f * C2133v.f5083b));
    private final Path f5369b = new Path();
    private final RectF f5370c = new RectF();

    public C2205f(Context context) {
        super(context);
        C2133v.m6833a((View) this, 0);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.f5370c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f5369b.reset();
        this.f5369b.addRoundRect(this.f5370c, (float) f5368a, (float) f5368a, Direction.CW);
        canvas.clipPath(this.f5369b);
        super.onDraw(canvas);
    }
}
