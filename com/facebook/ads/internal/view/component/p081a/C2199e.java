package com.facebook.ads.internal.view.component.p081a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.view.component.C2205f;
import com.facebook.ads.internal.view.component.C2208i;

final class C2199e extends RelativeLayout {
    private final View f5343a;
    private final C2205f f5344b;

    public C2199e(Context context, View view) {
        super(context);
        this.f5343a = view;
        this.f5344b = new C2205f(context);
        C2133v.m6832a(this.f5344b);
    }

    public void m7039a(int i) {
        this.f5343a.setLayoutParams(new LayoutParams(-1, i));
    }

    public void m7040a(@Nullable View view, @Nullable View view2, @Nullable C2208i c2208i, boolean z) {
        this.f5344b.addView(this.f5343a, new LayoutParams(-1, -2));
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(C2195b.f5336b, C2195b.f5336b);
            layoutParams.addRule(6, this.f5343a.getId());
            layoutParams.addRule(7, this.f5343a.getId());
            layoutParams.setMargins(C2195b.f5335a, C2195b.f5335a, C2195b.f5335a, C2195b.f5335a);
            this.f5344b.addView(view2, layoutParams);
        }
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.f5343a.getId());
        if (c2208i != null) {
            ViewGroup.LayoutParams layoutParams3;
            if (z) {
                layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                c2208i.setAlignment(3);
                layoutParams3.setMargins(C2195b.f5335a / 2, C2195b.f5335a / 2, C2195b.f5335a / 2, C2195b.f5335a / 2);
                linearLayout.addView(c2208i, layoutParams3);
                Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                C2133v.m6834a(linearLayout, gradientDrawable);
            } else {
                layoutParams3 = new LayoutParams(-1, -2);
                layoutParams3.addRule(3, this.f5344b.getId());
                layoutParams3.setMargins(0, C2195b.f5335a, 0, 0);
                c2208i.setAlignment(17);
                addView(c2208i, layoutParams3);
            }
        }
        if (view != null) {
            linearLayout.addView(view, new LayoutParams(-1, -2));
        }
        this.f5344b.addView(linearLayout, layoutParams2);
        addView(this.f5344b, new LayoutParams(-1, -2));
    }
}
