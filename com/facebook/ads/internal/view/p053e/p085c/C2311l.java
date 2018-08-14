package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import cz.msebera.android.httpclient.HttpStatus;

public class C2311l extends C2224c {
    private final C1842i f5585a;
    private final C1841k f5586b;
    private final C1844c f5587c;
    private final C2313m f5588d;
    private final Paint f5589e;

    class C23061 extends C1842i {
        final /* synthetic */ C2311l f5580a;

        C23061(C2311l c2311l) {
            this.f5580a = c2311l;
        }

        public void m7277a(C2233h c2233h) {
            this.f5580a.f5588d.setChecked(true);
        }
    }

    class C23072 extends C1841k {
        final /* synthetic */ C2311l f5581a;

        C23072(C2311l c2311l) {
            this.f5581a = c2311l;
        }

        public void m7279a(C2234j c2234j) {
            this.f5581a.f5588d.setChecked(false);
        }
    }

    class C23083 extends C1844c {
        final /* synthetic */ C2311l f5582a;

        C23083(C2311l c2311l) {
            this.f5582a = c2311l;
        }

        public void m7281a(C2229b c2229b) {
            this.f5582a.f5588d.setChecked(true);
        }
    }

    class C23094 implements OnClickListener {
        final /* synthetic */ C2311l f5583a;

        C23094(C2311l c2311l) {
            this.f5583a = c2311l;
        }

        public void onClick(View view) {
            if (this.f5583a.getVideoView() != null) {
                switch (this.f5583a.getVideoView().getState()) {
                    case PREPARED:
                    case IDLE:
                    case PAUSED:
                    case PLAYBACK_COMPLETED:
                        this.f5583a.getVideoView().m7104a(C2222a.USER_STARTED);
                        return;
                    case STARTED:
                        this.f5583a.getVideoView().m7107a(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public C2311l(Context context) {
        this(context, false);
    }

    public C2311l(Context context, boolean z) {
        super(context);
        this.f5585a = new C23061(this);
        this.f5586b = new C23072(this);
        this.f5587c = new C23083(this);
        this.f5588d = new C2313m(context, z);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 23.76d), (int) (((double) displayMetrics.density) * 23.76d));
        layoutParams.addRule(13);
        this.f5588d.setLayoutParams(layoutParams);
        this.f5588d.setChecked(true);
        this.f5589e = new Paint();
        this.f5589e.setStyle(Style.FILL);
        if (z) {
            this.f5589e.setColor(-1728053248);
        } else {
            this.f5589e.setColor(-1);
            this.f5589e.setAlpha(HttpStatus.SC_NO_CONTENT);
        }
        setBackgroundColor(0);
        addView(this.f5588d);
        setGravity(17);
        layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 72.0d), (int) (((double) displayMetrics.density) * 72.0d));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
    }

    protected void mo3787a() {
        super.mo3787a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6328a(this.f5585a, this.f5586b, this.f5587c);
        }
        OnClickListener c23094 = new C23094(this);
        this.f5588d.setClickable(false);
        setOnClickListener(c23094);
    }

    protected void mo3788b() {
        setOnClickListener(null);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6330b(this.f5587c, this.f5586b, this.f5585a);
        }
        super.mo3788b();
    }

    protected void onDraw(Canvas canvas) {
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        int i = min / 2;
        canvas.drawCircle((float) (getPaddingLeft() + i), (float) ((min / 2) + getPaddingTop()), (float) i, this.f5589e);
        super.onDraw(canvas);
    }
}
