package com.facebook.ads.internal.view.p080c;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.view.p034b.C2186d;

public class C2191c extends LinearLayout {
    private C2192d f5322a;
    private TextView f5323b;
    private TextView f5324c;

    public C2191c(Context context) {
        super(context);
        m7019a(context);
    }

    public void m7018a(int i, int i2) {
        this.f5323b.setTextColor(i);
        this.f5324c.setTextColor(i2);
    }

    public void m7019a(Context context) {
        int i = (int) (32.0f * C2133v.f5083b);
        setGravity(16);
        this.f5322a = new C2192d(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.setMargins(0, 0, (int) (8.0f * C2133v.f5083b), 0);
        addView(this.f5322a, layoutParams);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.f5323b = new TextView(context);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.f5323b.setTypeface(Typeface.SANS_SERIF, 1);
        this.f5323b.setTextSize(2, 16.0f);
        this.f5323b.setEllipsize(TruncateAt.END);
        this.f5323b.setSingleLine(true);
        this.f5324c = new TextView(context);
        this.f5324c.setTypeface(Typeface.SANS_SERIF, 0);
        this.f5324c.setTextSize(2, 14.0f);
        linearLayout.addView(this.f5323b);
        linearLayout.addView(this.f5324c);
        addView(linearLayout, layoutParams);
    }

    public void m7020a(String str, String str2, String str3) {
        C2186d c2186d = new C2186d(this.f5322a);
        c2186d.m7001a((int) (C2133v.f5083b * 32.0f), (int) (C2133v.f5083b * 32.0f));
        c2186d.m7004a(str2);
        this.f5323b.setText(str);
        this.f5324c.setText(str3);
    }
}
