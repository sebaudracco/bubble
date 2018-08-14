package com.facebook.ads.internal.protocol;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p056q.p057a.C2116g;
import java.util.HashMap;
import java.util.Map;

public class C2102g {
    private static final Map<e, f> f4985a = new HashMap();

    static {
        f4985a.put(e.e, f.e);
        f4985a.put(e.d, f.d);
        f4985a.put(e.c, f.c);
    }

    public static f m6755a(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
        int i2 = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return C2116g.m6789a(i, i2) ? f.i : i2 > i ? f.h : f.g;
    }

    public static f m6756a(e eVar) {
        f fVar = (f) f4985a.get(eVar);
        return fVar == null ? f.b : fVar;
    }

    public static void m6757a(DisplayMetrics displayMetrics, View view, e eVar) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= eVar.a() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) eVar.a()) * displayMetrics.density)), (int) Math.ceil((double) (((float) eVar.b()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
