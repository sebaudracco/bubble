package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1840m;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2237o;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class C2303j extends View implements C2223b {
    private final Paint f5565a;
    private final Paint f5566b;
    private final Paint f5567c;
    private C2302a f5568d = C2302a.CLOSE_BUTTON_MODE;
    private final Paint f5569e;
    private final RectF f5570f;
    @Nullable
    private C2249b f5571g;
    private int f5572h;
    private final AtomicInteger f5573i = new AtomicInteger(0);
    private final AtomicBoolean f5574j = new AtomicBoolean(false);
    private final C1840m f5575k = new C22991(this);
    private final C2237o f5576l = new C23002(this);
    private final C1844c f5577m = new C23013(this);

    class C22991 extends C1840m {
        final /* synthetic */ C2303j f5559a;

        C22991(C2303j c2303j) {
            this.f5559a = c2303j;
        }

        public void m7258a(C2235l c2235l) {
            this.f5559a.f5574j.set(true);
        }
    }

    class C23002 extends C2237o {
        final /* synthetic */ C2303j f5560a;

        C23002(C2303j c2303j) {
            this.f5560a = c2303j;
        }

        public void m7260a(C2236n c2236n) {
            if (this.f5560a.f5571g != null) {
                int c = this.f5560a.f5572h;
                int duration = this.f5560a.f5571g.getDuration();
                if (c <= 0) {
                    this.f5560a.f5573i.set(0);
                } else {
                    c = Math.min(duration, c * 1000);
                    if (c != 0) {
                        this.f5560a.f5573i.set(((c - this.f5560a.f5571g.getCurrentPosition()) * 100) / c);
                    } else {
                        return;
                    }
                }
                this.f5560a.postInvalidate();
            }
        }
    }

    class C23013 extends C1844c {
        final /* synthetic */ C2303j f5561a;

        C23013(C2303j c2303j) {
            this.f5561a = c2303j;
        }

        public void m7262a(C2229b c2229b) {
            this.f5561a.f5572h = 0;
            this.f5561a.f5573i.set(0);
            this.f5561a.postInvalidate();
        }
    }

    public enum C2302a {
        CLOSE_BUTTON_MODE,
        SKIP_BUTTON_MODE
    }

    public C2303j(Context context, int i, int i2) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        this.f5572h = i;
        this.f5566b = new Paint();
        this.f5566b.setStyle(Style.FILL);
        this.f5566b.setColor(i2);
        this.f5567c = new Paint();
        this.f5567c.setColor(-1);
        this.f5567c.setAlpha(230);
        this.f5567c.setStyle(Style.FILL);
        this.f5567c.setStrokeWidth(1.0f * f);
        this.f5567c.setAntiAlias(true);
        this.f5565a = new Paint();
        this.f5565a.setColor(-16777216);
        this.f5565a.setStyle(Style.STROKE);
        this.f5565a.setAlpha(102);
        this.f5565a.setStrokeWidth(1.5f * f);
        this.f5565a.setAntiAlias(true);
        setLayerType(1, null);
        this.f5565a.setMaskFilter(new BlurMaskFilter(6.0f, Blur.NORMAL));
        this.f5569e = new Paint();
        this.f5569e.setColor(-10066330);
        this.f5569e.setStyle(Style.STROKE);
        this.f5569e.setStrokeWidth(f * 2.0f);
        this.f5569e.setAntiAlias(true);
        this.f5570f = new RectF();
    }

    public void mo3777a(C2249b c2249b) {
        this.f5571g = c2249b;
        this.f5571g.getEventBus().m6328a(this.f5575k, this.f5576l, this.f5577m);
    }

    public boolean m7269a() {
        return this.f5571g != null && (this.f5572h <= 0 || this.f5573i.get() < 0);
    }

    public void mo3778b(C2249b c2249b) {
        this.f5571g.getEventBus().m6330b(this.f5577m, this.f5576l, this.f5575k);
        this.f5571g = null;
    }

    public int getSkipSeconds() {
        return this.f5572h;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5574j.get()) {
            int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
            int i = min / 2;
            int i2 = min / 2;
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i2), (float) i, this.f5565a);
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (i2 + getPaddingTop()), (float) i, this.f5567c);
            if (this.f5573i.get() > 0) {
                this.f5570f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
                canvas.drawArc(this.f5570f, RadialCountdown.START_ANGLE, ((float) (-(this.f5573i.get() * 360))) / 100.0f, true, this.f5566b);
            } else if (this.f5568d == C2302a.SKIP_BUTTON_MODE) {
                i2 = min / 4;
                min /= 3;
                Path path = new Path();
                path.moveTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + min));
                path.lineTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i));
                path.lineTo((float) (getPaddingLeft() + i2), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.f5569e);
                path = new Path();
                path.moveTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + min));
                path.lineTo((float) ((i2 * 3) + getPaddingLeft()), (float) (getPaddingTop() + i));
                path.lineTo((float) (i + getPaddingLeft()), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.f5569e);
            } else {
                int i3 = min / 3;
                int i4 = min / 3;
                canvas.drawLine((float) (getPaddingLeft() + i3), (float) (getPaddingTop() + i4), (float) ((i3 * 2) + getPaddingLeft()), (float) ((i4 * 2) + getPaddingTop()), this.f5569e);
                canvas.drawLine((float) ((i3 * 2) + getPaddingLeft()), (float) (getPaddingTop() + i4), (float) (getPaddingLeft() + i3), (float) ((i4 * 2) + getPaddingTop()), this.f5569e);
            }
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
    }

    public void setButtonMode(C2302a c2302a) {
        this.f5568d = c2302a;
    }
}
