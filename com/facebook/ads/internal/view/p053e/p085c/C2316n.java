package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2237o;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;

public class C2316n extends View implements C2223b {
    private final Paint f5599a = new Paint();
    private final Rect f5600b;
    private float f5601c;
    private final C2237o f5602d = new C23141(this);
    private final C1844c f5603e = new C23152(this);
    @Nullable
    private C2249b f5604f;

    class C23141 extends C2237o {
        final /* synthetic */ C2316n f5597a;

        C23141(C2316n c2316n) {
            this.f5597a = c2316n;
        }

        public void m7290a(C2236n c2236n) {
            if (this.f5597a.f5604f != null) {
                int duration = this.f5597a.f5604f.getDuration();
                if (duration > 0) {
                    this.f5597a.f5601c = ((float) this.f5597a.f5604f.getCurrentPosition()) / ((float) duration);
                } else {
                    this.f5597a.f5601c = 0.0f;
                }
                this.f5597a.postInvalidate();
            }
        }
    }

    class C23152 extends C1844c {
        final /* synthetic */ C2316n f5598a;

        C23152(C2316n c2316n) {
            this.f5598a = c2316n;
        }

        public void m7292a(C2229b c2229b) {
            if (this.f5598a.f5604f != null) {
                this.f5598a.f5601c = 0.0f;
                this.f5598a.postInvalidate();
            }
        }
    }

    public C2316n(Context context) {
        super(context);
        this.f5599a.setStyle(Style.FILL);
        this.f5599a.setColor(-9528840);
        this.f5600b = new Rect();
    }

    public void mo3777a(C2249b c2249b) {
        this.f5604f = c2249b;
        c2249b.getEventBus().m6328a(this.f5602d, this.f5603e);
    }

    public void mo3778b(C2249b c2249b) {
        c2249b.getEventBus().m6330b(this.f5603e, this.f5602d);
        this.f5604f = null;
    }

    public void draw(Canvas canvas) {
        this.f5600b.set(0, 0, (int) (((float) getWidth()) * this.f5601c), getHeight());
        canvas.drawRect(this.f5600b, this.f5599a);
        super.draw(canvas);
    }
}
