package com.appnext.ads.fullscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.appnext.core.C1128g;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

@SuppressLint({"ViewConstructor"})
public class Circle extends View {
    private static final int ds = 180;
    private Paint dt;
    private RectF du;
    private float dv;

    public Circle(Context context) {
        super(context);
        init(context);
    }

    public Circle(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        float a = (float) m1829a(context, 5.0f);
        this.dt = new Paint();
        this.dt.setAntiAlias(true);
        this.dt.setStyle(Style.STROKE);
        this.dt.setStrokeWidth(a);
        this.dt.setColor(-1);
        this.dt.setShadowLayer(2.0f, 2.0f, 2.0f, Color.argb(128, 0, 0, 0));
        setLayerType(1, this.dt);
        this.du = new RectF(a, a, ((float) m1829a(context, CloseButton.TEXT_SIZE_SP)) + a, ((float) m1829a(context, CloseButton.TEXT_SIZE_SP)) + a);
        this.dv = 360.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.du, BitmapDescriptorFactory.HUE_CYAN, this.dv, false, this.dt);
    }

    public float getAngle() {
        return this.dv;
    }

    public void setAngle(float f) {
        this.dv = f;
    }

    private static int m1829a(Context context, float f) {
        return C1128g.m2334a(context, f);
    }
}
