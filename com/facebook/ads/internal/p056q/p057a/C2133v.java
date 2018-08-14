package com.facebook.ads.internal.p056q.p057a;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.atomic.AtomicInteger;

public class C2133v {
    public static final DisplayMetrics f5082a = Resources.getSystem().getDisplayMetrics();
    public static final float f5083b = f5082a.density;
    private static final AtomicInteger f5084c = new AtomicInteger(1);

    public static int m6830a() {
        int i;
        int i2;
        do {
            i = f5084c.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!f5084c.compareAndSet(i, i2));
        return i;
    }

    public static int m6831a(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, f5082a);
    }

    public static void m6832a(View view) {
        if (VERSION.SDK_INT >= 17) {
            view.setId(View.generateViewId());
        } else {
            view.setId(C2133v.m6830a());
        }
    }

    public static void m6833a(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(i));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    public static void m6834a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void m6835a(View... viewArr) {
        for (View b : viewArr) {
            C2133v.m6836b(b);
        }
    }

    public static void m6836b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }
}
