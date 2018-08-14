package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.facebook.ads.internal.p056q.p075b.C2136b;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1845w;
import com.facebook.ads.internal.view.p053e.p054b.C2243v;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;

public class C2286f extends ImageView implements C2223b {
    private static final int f5530a = ((int) (4.0f * Resources.getSystem().getDisplayMetrics().density));
    private final Paint f5531b = new Paint();
    @Nullable
    private C2249b f5532c;
    private final C1845w f5533d = new C22841(this);

    class C22841 extends C1845w {
        final /* synthetic */ C2286f f5528a;

        C22841(C2286f c2286f) {
            this.f5528a = c2286f;
        }

        public void m7215a(C2243v c2243v) {
            this.f5528a.m7221a();
        }
    }

    class C22852 implements OnClickListener {
        final /* synthetic */ C2286f f5529a;

        C22852(C2286f c2286f) {
            this.f5529a = c2286f;
        }

        public void onClick(View view) {
            if (this.f5529a.f5532c != null) {
                if (this.f5529a.m7217b()) {
                    this.f5529a.f5532c.setVolume(1.0f);
                } else {
                    this.f5529a.f5532c.setVolume(0.0f);
                }
                this.f5529a.m7221a();
            }
        }
    }

    public C2286f(Context context) {
        super(context);
        this.f5531b.setColor(-1728053248);
        setColorFilter(-1);
        setPadding(f5530a, f5530a, f5530a, f5530a);
        m7219c();
        setOnClickListener(new C22852(this));
    }

    private boolean m7217b() {
        return this.f5532c != null && this.f5532c.getVolume() == 0.0f;
    }

    private void m7219c() {
        setImageBitmap(C2137c.m6843a(C2136b.SOUND_ON));
    }

    private void m7220d() {
        setImageBitmap(C2137c.m6843a(C2136b.SOUND_OFF));
    }

    public final void m7221a() {
        if (this.f5532c != null) {
            if (m7217b()) {
                m7220d();
            } else {
                m7219c();
            }
        }
    }

    public void mo3777a(C2249b c2249b) {
        this.f5532c = c2249b;
        if (this.f5532c != null) {
            this.f5532c.getEventBus().m6329a(this.f5533d);
        }
    }

    public void mo3778b(C2249b c2249b) {
        if (this.f5532c != null) {
            this.f5532c.getEventBus().m6331b(this.f5533d);
        }
        this.f5532c = null;
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.f5531b);
        super.onDraw(canvas);
    }
}
