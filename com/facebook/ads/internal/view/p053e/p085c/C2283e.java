package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.p053e.p054b.C2228a;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import java.util.HashMap;

public class C2283e extends C2224c {
    private final String f5522a;
    private final TextView f5523b = new TextView(getContext());
    private final C2012c f5524c;
    private final String f5525d;
    private final Paint f5526e;
    private final RectF f5527f;

    class C22821 implements OnClickListener {
        final /* synthetic */ C2283e f5521a;

        C22821(C2283e c2283e) {
            this.f5521a = c2283e;
        }

        public void onClick(View view) {
            if (this.f5521a.getVideoView() != null) {
                Uri parse = Uri.parse(this.f5521a.f5522a);
                this.f5521a.getVideoView().getEventBus().m6327a(new C2228a(parse));
                C1873a a = C1874b.m5632a(this.f5521a.getContext(), this.f5521a.f5524c, this.f5521a.f5525d, parse, new HashMap());
                if (a != null) {
                    a.mo3622b();
                }
            }
        }
    }

    public C2283e(Context context, String str, C2012c c2012c, String str2, String str3) {
        super(context);
        this.f5522a = str;
        this.f5524c = c2012c;
        this.f5525d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f5523b.setTextColor(-3355444);
        this.f5523b.setTextSize(16.0f);
        this.f5523b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f));
        this.f5526e = new Paint();
        this.f5526e.setStyle(Style.FILL);
        this.f5526e.setColor(-16777216);
        this.f5526e.setAlpha(178);
        this.f5527f = new RectF();
        setBackgroundColor(0);
        this.f5523b.setText(str3);
        addView(this.f5523b, new LayoutParams(-2, -2));
    }

    protected void mo3787a() {
        super.mo3787a();
        this.f5523b.setOnClickListener(new C22821(this));
    }

    protected void mo3788b() {
        this.f5523b.setOnClickListener(null);
        super.mo3788b();
    }

    protected void onDraw(Canvas canvas) {
        this.f5527f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5527f, 0.0f, 0.0f, this.f5526e);
        super.onDraw(canvas);
    }
}
