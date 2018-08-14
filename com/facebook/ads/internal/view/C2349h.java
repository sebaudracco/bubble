package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.component.C2200a;
import com.facebook.ads.internal.view.component.C2208i;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import java.util.Map;

public class C2349h extends RelativeLayout {
    private final Paint f5773a;
    private final float f5774b;
    private final float f5775c;
    private final float f5776d;
    private LinearLayout f5777e;
    private C2208i f5778f;
    private C2200a f5779g;
    private C2395u f5780h;
    private C2012c f5781i;
    private C1832a f5782j;
    private final String f5783k;

    public C2349h(Context context, C1900j c1900j, boolean z, C2012c c2012c, C1832a c1832a, String str) {
        super(context);
        this.f5781i = c2012c;
        this.f5782j = c1832a;
        this.f5783k = str;
        float f = getResources().getDisplayMetrics().density;
        this.f5774b = 1.0f * f;
        this.f5776d = 4.0f * f;
        this.f5775c = 6.0f * f;
        setGravity(17);
        setPadding((int) this.f5774b, 0, (int) this.f5774b, (int) this.f5774b);
        C2133v.m6833a((View) this, 0);
        if (z) {
            m7431b(context, f, c1900j);
        } else {
            m7430a(context, f, c1900j);
        }
        this.f5773a = new Paint();
        this.f5773a.setColor(-16777216);
        this.f5773a.setStyle(Style.FILL);
        this.f5773a.setAlpha(16);
        this.f5773a.setAntiAlias(true);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private void m7430a(Context context, float f, C1900j c1900j) {
        this.f5780h = new C2395u(context);
        this.f5780h.setLayoutParams(new LayoutParams(-1, -2));
        C2133v.m6832a(this.f5780h);
        this.f5778f = new C2208i(context, c1900j, false, false, true);
        this.f5778f.setAlignment(3);
        this.f5778f.setLayoutParams(new LayoutParams(-1, -2));
        this.f5778f.setPadding(0, 0, 0, (int) (CloseButton.TEXT_SIZE_SP * f));
        this.f5779g = new C2200a(context, true, false, "com.facebook.ads.interstitial.clicked", c1900j, this.f5781i, this.f5782j);
        this.f5779g.setLayoutParams(new LayoutParams(-1, -2));
        this.f5777e = new LinearLayout(context);
        if (VERSION.SDK_INT >= 16) {
            this.f5777e.setBackground(new ColorDrawable(-1));
        } else {
            this.f5777e.setBackgroundDrawable(new ColorDrawable(-1));
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, this.f5780h.getId());
        this.f5777e.setLayoutParams(layoutParams);
        this.f5777e.setOrientation(1);
        int i = (int) (16.0f * f);
        this.f5777e.setPadding(i, i, i, i);
        this.f5777e.addView(this.f5778f);
        this.f5777e.addView(this.f5779g);
        addView(this.f5780h);
        addView(this.f5777e);
    }

    private void m7431b(Context context, float f, C1900j c1900j) {
        this.f5780h = new C2395u(context);
        this.f5780h.setLayoutParams(new LayoutParams(-1, -2));
        if (VERSION.SDK_INT >= 17) {
            this.f5780h.setId(View.generateViewId());
        } else {
            this.f5780h.setId(C2133v.m6830a());
        }
        this.f5778f = new C2208i(context, c1900j, true, true, true);
        this.f5778f.setAlignment(3);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(8, this.f5780h.getId());
        int i = (int) (12.0f * f);
        this.f5778f.setLayoutParams(layoutParams);
        this.f5778f.setPadding(i, i, i, i);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        C2133v.m6834a(this.f5778f, gradientDrawable);
        this.f5779g = new C2200a(context, false, false, "com.facebook.ads.interstitial.clicked", c1900j, this.f5781i, this.f5782j);
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, this.f5780h.getId());
        this.f5779g.setLayoutParams(layoutParams);
        addView(this.f5780h);
        addView(this.f5778f);
        addView(this.f5779g);
    }

    public void m7432a(String str, String str2) {
        this.f5778f.m7047a(str, str2, true, false);
    }

    public void m7433a(String str, String str2, Map<String, String> map) {
        this.f5779g.m7044a(str, str2, this.f5783k, map);
    }

    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), this.f5775c, this.f5775c, Direction.CW);
        canvas.drawPath(path, this.f5773a);
        path = new Path();
        path.addRoundRect(new RectF(this.f5774b, 0.0f, ((float) getWidth()) - this.f5774b, ((float) getHeight()) - this.f5774b), this.f5776d, this.f5776d, Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        new C2186d(this.f5780h).m7004a(str);
    }
}
