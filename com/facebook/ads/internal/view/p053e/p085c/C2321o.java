package com.facebook.ads.internal.view.p053e.p085c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2237o;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import java.util.concurrent.atomic.AtomicInteger;

public class C2321o extends RelativeLayout implements C2223b {
    private static final int f5609a = ((int) (6.0f * C2133v.f5083b));
    private ObjectAnimator f5610b;
    private AtomicInteger f5611c;
    private ProgressBar f5612d;
    @Nullable
    private C2249b f5613e;
    private C1839f f5614f;
    private C1839f f5615g;
    private C1839f f5616h;
    private C1839f f5617i;

    class C23171 extends C2237o {
        final /* synthetic */ C2321o f5605a;

        C23171(C2321o c2321o) {
            this.f5605a = c2321o;
        }

        public void m7298a(C2236n c2236n) {
            if (this.f5605a.f5613e != null) {
                this.f5605a.m7306a(this.f5605a.f5613e.getDuration(), this.f5605a.f5613e.getCurrentPosition());
            }
        }
    }

    class C23182 extends C1842i {
        final /* synthetic */ C2321o f5606a;

        C23182(C2321o c2321o) {
            this.f5606a = c2321o;
        }

        public void m7300a(C2233h c2233h) {
            this.f5606a.m7308b();
        }
    }

    class C23193 extends C1841k {
        final /* synthetic */ C2321o f5607a;

        C23193(C2321o c2321o) {
            this.f5607a = c2321o;
        }

        public void m7302a(C2234j c2234j) {
            if (this.f5607a.f5613e != null) {
                this.f5607a.m7306a(this.f5607a.f5613e.getDuration(), this.f5607a.f5613e.getCurrentPosition());
            }
        }
    }

    class C23204 extends C1844c {
        final /* synthetic */ C2321o f5608a;

        C23204(C2321o c2321o) {
            this.f5608a = c2321o;
        }

        public void m7304a(C2229b c2229b) {
            if (this.f5608a.f5613e != null) {
                this.f5608a.m7310c();
            }
        }
    }

    public C2321o(Context context) {
        this(context, f5609a, -12549889);
    }

    public C2321o(Context context, int i, int i2) {
        super(context);
        this.f5614f = new C23171(this);
        this.f5615g = new C23182(this);
        this.f5616h = new C23193(this);
        this.f5617i = new C23204(this);
        this.f5611c = new AtomicInteger(-1);
        this.f5612d = new ProgressBar(context, null, 16842872);
        this.f5612d.setLayoutParams(new LayoutParams(-1, i));
        setProgressBarColor(i2);
        this.f5612d.setMax(10000);
        addView(this.f5612d);
    }

    private void m7306a(int i, int i2) {
        m7308b();
        if (this.f5611c.get() < i2 && i > i2) {
            int i3 = (i2 * 10000) / i;
            int min = (Math.min(i2 + 250, i) * 10000) / i;
            this.f5610b = ObjectAnimator.ofInt(this.f5612d, NotificationCompat.CATEGORY_PROGRESS, new int[]{i3, min});
            this.f5610b.setDuration((long) Math.min(250, i - i2));
            this.f5610b.setInterpolator(new LinearInterpolator());
            this.f5610b.start();
            this.f5611c.set(i2);
        }
    }

    private void m7308b() {
        if (this.f5610b != null) {
            this.f5610b.cancel();
            this.f5610b.setTarget(null);
            this.f5610b = null;
            this.f5612d.clearAnimation();
        }
    }

    private void m7310c() {
        m7308b();
        this.f5610b = ObjectAnimator.ofInt(this.f5612d, NotificationCompat.CATEGORY_PROGRESS, new int[]{0, 0});
        this.f5610b.setDuration(0);
        this.f5610b.setInterpolator(new LinearInterpolator());
        this.f5610b.start();
        this.f5611c.set(0);
    }

    public void m7312a() {
        m7308b();
        this.f5612d = null;
        this.f5613e = null;
    }

    public void mo3777a(C2249b c2249b) {
        this.f5613e = c2249b;
        c2249b.getEventBus().m6328a(this.f5615g, this.f5616h, this.f5614f, this.f5617i);
    }

    public void mo3778b(C2249b c2249b) {
        c2249b.getEventBus().m6330b(this.f5614f, this.f5616h, this.f5615g, this.f5617i);
        this.f5613e = null;
    }

    public void setProgressBarColor(int i) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ColorDrawable colorDrawable2 = new ColorDrawable(0);
        ScaleDrawable scaleDrawable = new ScaleDrawable(new ColorDrawable(i), GravityCompat.START, 1.0f, -1.0f);
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{colorDrawable, colorDrawable2, scaleDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.f5612d.setProgressDrawable(layerDrawable);
    }
}
