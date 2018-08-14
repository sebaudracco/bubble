package org.telegram.ui.Components;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class CubicBezierInterpolator implements Interpolator {
    public static final CubicBezierInterpolator DEFAULT = new CubicBezierInterpolator(0.25d, 0.1d, 0.25d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    public static final CubicBezierInterpolator EASE_BOTH = new CubicBezierInterpolator(0.42d, 0.0d, 0.58d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    public static final CubicBezierInterpolator EASE_IN = new CubicBezierInterpolator(0.42d, 0.0d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    public static final CubicBezierInterpolator EASE_OUT = new CubicBezierInterpolator(0.0d, 0.0d, 0.58d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    public static final CubicBezierInterpolator EASE_OUT_QUINT = new CubicBezierInterpolator(0.23d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.32d, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    protected PointF f12657a;
    protected PointF f12658b;
    protected PointF f12659c;
    protected PointF end;
    protected PointF start;

    public CubicBezierInterpolator(PointF start, PointF end) throws IllegalArgumentException {
        this.f12657a = new PointF();
        this.f12658b = new PointF();
        this.f12659c = new PointF();
        if (start.x < 0.0f || start.x > 1.0f) {
            throw new IllegalArgumentException("startX value must be in the range [0, 1]");
        } else if (end.x < 0.0f || end.x > 1.0f) {
            throw new IllegalArgumentException("endX value must be in the range [0, 1]");
        } else {
            this.start = start;
            this.end = end;
        }
    }

    public CubicBezierInterpolator(float startX, float startY, float endX, float endY) {
        this(new PointF(startX, startY), new PointF(endX, endY));
    }

    public CubicBezierInterpolator(double startX, double startY, double endX, double endY) {
        this((float) startX, (float) startY, (float) endX, (float) endY);
    }

    public float getInterpolation(float time) {
        return getBezierCoordinateY(getXForTime(time));
    }

    protected float getBezierCoordinateY(float time) {
        this.f12659c.y = this.start.y * 3.0f;
        this.f12658b.y = ((this.end.y - this.start.y) * 3.0f) - this.f12659c.y;
        this.f12657a.y = (1.0f - this.f12659c.y) - this.f12658b.y;
        return (this.f12659c.y + ((this.f12658b.y + (this.f12657a.y * time)) * time)) * time;
    }

    protected float getXForTime(float time) {
        float x = time;
        for (int i = 1; i < 14; i++) {
            float z = getBezierCoordinateX(x) - time;
            if (((double) Math.abs(z)) < 0.001d) {
                break;
            }
            x -= z / getXDerivate(x);
        }
        return x;
    }

    private float getXDerivate(float t) {
        return this.f12659c.x + (((2.0f * this.f12658b.x) + ((3.0f * this.f12657a.x) * t)) * t);
    }

    private float getBezierCoordinateX(float time) {
        this.f12659c.x = this.start.x * 3.0f;
        this.f12658b.x = ((this.end.x - this.start.x) * 3.0f) - this.f12659c.x;
        this.f12657a.x = (1.0f - this.f12659c.x) - this.f12658b.x;
        return (this.f12659c.x + ((this.f12658b.x + (this.f12657a.x * time)) * time)) * time;
    }
}
