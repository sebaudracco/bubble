package com.facebook.ads.internal.view.component.p081a;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;

public class C2198d extends C2195b {
    private static final int f5341c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final C2199e f5342d;

    public C2198d(Context context, C2012c c2012c, C1832a c1832a, View view, @Nullable View view2, @Nullable View view3, boolean z, C1900j c1900j) {
        super(context, c2012c, c1832a, c1900j, z);
        this.f5342d = new C2199e(context, view);
        this.f5342d.m7040a(view2, view3, getTextContainer(), z);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        getCtaButton().setLayoutParams(layoutParams);
        View frameLayout = new FrameLayout(context);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        layoutParams2.setMargins(a, 0, a, 0);
        frameLayout.addView(this.f5342d, layoutParams2);
        addView(frameLayout);
        addView(getCtaButton());
    }

    public void mo3771a(String str, String str2, String str3, String str4, String str5, double d) {
        super.mo3771a(str, str2, str3, str4, str5, d);
        if (d > 0.0d) {
            this.f5342d.m7039a((int) (((double) (f5341c - (a * 2))) * d));
        }
    }

    public boolean mo3770a() {
        return false;
    }

    protected boolean mo3772b() {
        return false;
    }

    protected boolean mo3773c() {
        return false;
    }
}
