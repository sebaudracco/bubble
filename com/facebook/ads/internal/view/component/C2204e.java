package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.internal.p056q.p057a.C2133v;

public class C2204e extends ImageView {
    private static final int f5364a = ((int) (8.0f * C2133v.f5083b));
    private final Path f5365b = new Path();
    private final RectF f5366c = new RectF();
    private int f5367d = f5364a;

    public C2204e(Context context) {
        super(context);
        C2133v.m6833a((View) this, 0);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.f5366c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f5365b.reset();
        this.f5365b.addRoundRect(this.f5366c, (float) this.f5367d, (float) this.f5367d, Direction.CW);
        canvas.clipPath(this.f5365b);
        super.onDraw(canvas);
    }

    public void setRadius(int i) {
        this.f5367d = (int) (((float) i) * C2133v.f5083b);
    }
}
