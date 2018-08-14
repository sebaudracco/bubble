package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.util.concurrent.atomic.AtomicBoolean;

public class C2298i extends C2224c {
    private final C2297a f5553a;
    private final int f5554b;
    private final String f5555c;
    private final String f5556d;
    private final AtomicBoolean f5557e;
    private final C1839f<C2236n> f5558f = new C22951(this);

    class C22951 extends C1839f<C2236n> {
        final /* synthetic */ C2298i f5548a;

        C22951(C2298i c2298i) {
            this.f5548a = c2298i;
        }

        public Class<C2236n> mo3580a() {
            return C2236n.class;
        }

        public void m7245a(C2236n c2236n) {
            if (!this.f5548a.f5557e.get() && this.f5548a.getVideoView() != null) {
                int c = this.f5548a.f5554b - (this.f5548a.getVideoView().getCurrentPosition() / 1000);
                if (c > 0) {
                    this.f5548a.f5553a.setText(this.f5548a.f5555c + ' ' + c);
                    return;
                }
                this.f5548a.f5553a.setText(this.f5548a.f5556d);
                this.f5548a.f5557e.set(true);
            }
        }
    }

    class C22962 implements OnClickListener {
        final /* synthetic */ C2298i f5549a;

        C22962(C2298i c2298i) {
            this.f5549a = c2298i;
        }

        public void onClick(View view) {
            if (!this.f5549a.f5557e.get()) {
                Log.i("SkipPlugin", "User clicked skip before the ads is allowed to skip.");
            } else if (this.f5549a.getVideoView() != null) {
                this.f5549a.getVideoView().m7110e();
            }
        }
    }

    private static class C2297a extends TextView {
        private final Paint f5550a = new Paint();
        private final Paint f5551b;
        private final RectF f5552c;

        public C2297a(Context context) {
            super(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            setBackgroundColor(0);
            setTextColor(-3355444);
            setPadding((int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f), (int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f));
            setTextSize(RadialCountdown.TEXT_SIZE_SP);
            this.f5550a.setStyle(Style.STROKE);
            this.f5550a.setColor(-10066330);
            this.f5550a.setStrokeWidth(1.0f);
            this.f5550a.setAntiAlias(true);
            this.f5551b = new Paint();
            this.f5551b.setStyle(Style.FILL);
            this.f5551b.setColor(-1895825408);
            this.f5552c = new RectF();
        }

        protected void onDraw(Canvas canvas) {
            if (getText().length() != 0) {
                int width = getWidth();
                int height = getHeight();
                this.f5552c.set((float) null, (float) null, (float) width, (float) height);
                canvas.drawRoundRect(this.f5552c, 6.0f, 6.0f, this.f5551b);
                this.f5552c.set((float) 2, (float) 2, (float) (width - 2), (float) (height - 2));
                canvas.drawRoundRect(this.f5552c, 6.0f, 6.0f, this.f5550a);
                super.onDraw(canvas);
            }
        }
    }

    public C2298i(Context context, int i, String str, String str2) {
        super(context);
        this.f5554b = i;
        this.f5555c = str;
        this.f5556d = str2;
        this.f5557e = new AtomicBoolean(false);
        this.f5553a = new C2297a(context);
        this.f5553a.setText(this.f5555c + ' ' + i);
        addView(this.f5553a, new LayoutParams(-2, -2));
    }

    public void mo3787a() {
        super.mo3787a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6329a(this.f5558f);
        }
        this.f5553a.setOnClickListener(new C22962(this));
    }

    public void mo3788b() {
        if (getVideoView() != null) {
            this.f5553a.setOnClickListener(null);
            getVideoView().getEventBus().m6331b(this.f5558f);
        }
        super.mo3788b();
    }
}
