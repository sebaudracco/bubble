package com.facebook.ads.internal.view.component.p081a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;

public class C2196a extends C2195b {
    public C2196a(Context context, C2012c c2012c, C1832a c1832a, View view, @Nullable View view2, @Nullable View view3, int i, C1900j c1900j, boolean z) {
        super(context, c2012c, c1832a, c1900j, true);
        View relativeLayout = new RelativeLayout(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        C2133v.m6834a(relativeLayout, gradientDrawable);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(z ? 0 : 1);
        linearLayout.setGravity(80);
        C2133v.m6832a(linearLayout);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(a, 0, a, view2 == null ? a : a / 2);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(z ? -2 : -1, -2);
        layoutParams3.setMargins(z ? a : 0, z ? 0 : a, 0, 0);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(z ? 0 : -1, -2);
        layoutParams4.setMargins(0, 0, 0, 0);
        layoutParams4.weight = 1.0f;
        linearLayout.addView(getTextContainer(), layoutParams4);
        linearLayout.addView(getCtaButton(), layoutParams3);
        relativeLayout.addView(linearLayout, layoutParams2);
        if (view2 != null) {
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams5.setMargins(0, 0, 0, 0);
            layoutParams5.addRule(3, linearLayout.getId());
            relativeLayout.addView(view2, layoutParams5);
        }
        addView(view, new RelativeLayout.LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams);
        if (view3 != null) {
            layoutParams5 = new RelativeLayout.LayoutParams(b, b);
            layoutParams5.addRule(10);
            layoutParams5.addRule(11);
            layoutParams5.setMargins(a, a + i, a, a);
            addView(view3, layoutParams5);
        }
    }

    public boolean mo3770a() {
        return true;
    }
}
