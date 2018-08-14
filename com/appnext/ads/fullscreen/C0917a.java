package com.appnext.ads.fullscreen;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class C0917a extends Animation {
    private Circle dw;
    private float dx;
    private float dy;

    public C0917a(Circle circle, float f) {
        setInterpolator(new LinearInterpolator());
        this.dx = circle.getAngle();
        this.dy = f;
        this.dw = circle;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.dw.setAngle(this.dx - ((this.dx - this.dy) * f));
        this.dw.invalidate();
        this.dw.requestLayout();
    }
}
