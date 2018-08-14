package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import java.util.ArrayList;
import java.util.List;

public class C2203d extends LinearLayout {
    private final int f5359a;
    private final int f5360b;
    private final int f5361c;
    private int f5362d = -1;
    private List<GradientDrawable> f5363e;

    public C2203d(Context context, C1900j c1900j, int i) {
        super(context);
        setOrientation(0);
        setGravity(17);
        float f = C2133v.f5083b;
        int i2 = (int) (8.0f * f);
        int i3 = (int) (6.0f * f);
        this.f5361c = (int) (f * 1.0f);
        this.f5359a = c1900j.m5829a(false);
        this.f5360b = ColorUtils.setAlphaComponent(this.f5359a, 128);
        this.f5363e = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i2, i2);
            gradientDrawable.setStroke(this.f5361c, 0);
            View imageView = new ImageView(context);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.f5363e.add(gradientDrawable);
            addView(imageView);
        }
        m7046a(0);
    }

    public void m7046a(int i) {
        if (this.f5362d != i) {
            this.f5362d = i;
            for (int i2 = 0; i2 < this.f5363e.size(); i2++) {
                int i3;
                int i4;
                if (i2 == i) {
                    i3 = this.f5359a;
                    i4 = this.f5359a;
                } else {
                    i4 = 0;
                    i3 = this.f5360b;
                }
                ((GradientDrawable) this.f5363e.get(i2)).setStroke(this.f5361c, i4);
                ((GradientDrawable) this.f5363e.get(i2)).setColor(i3);
                ((GradientDrawable) this.f5363e.get(i2)).invalidateSelf();
            }
        }
    }
}
