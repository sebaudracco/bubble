package com.facebook.ads.internal.view.component.p081a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.component.C2200a;
import com.facebook.ads.internal.view.p053e.p085c.C2321o;

public class C2197c {
    private static final int f5339a = C2133v.f5082a.heightPixels;
    private static final int f5340b = C2133v.f5082a.widthPixels;

    private static int m7027a(int i) {
        return (f5339a - i) - ((C2133v.m6831a(16) + (C2200a.f5345a * 2)) + (C2198d.a * 2));
    }

    public static C2195b m7028a(Context context, C2012c c2012c, C1832a c1832a, View view, C1900j c1900j, C1900j c1900j2, int i, int i2, int i3, int i4) {
        return C2197c.m7029a(context, c2012c, c1832a, view, c1900j, c1900j2, i, i2, i3, i4, null, null);
    }

    public static C2195b m7029a(Context context, C2012c c2012c, C1832a c1832a, View view, C1900j c1900j, C1900j c1900j2, int i, int i2, int i3, int i4, @Nullable C2321o c2321o, @Nullable View view2) {
        C1900j c1900j3 = i2 == 1 ? c1900j : c1900j2;
        boolean a = C2197c.m7032a(i2, i3, i4, i);
        if (c2321o != null) {
            c2321o.setProgressBarColor(ColorUtils.setAlphaComponent(c1900j3.m5829a(a), 128));
        }
        if (a) {
            return new C2196a(context, c2012c, c1832a, view, c2321o, view2, i, c1900j3, i2 == 2);
        }
        return new C2198d(context, c2012c, c1832a, view, c2321o, view2, C2197c.m7030a(i3, i4), c1900j3);
    }

    private static boolean m7030a(int i, int i2) {
        return ((double) C2197c.m7034c(i, i2)) < 0.9d;
    }

    private static boolean m7031a(int i, int i2, int i3) {
        return C2197c.m7027a(i3) < C2197c.m7033b(i, i2);
    }

    private static boolean m7032a(int i, int i2, int i3, int i4) {
        return i == 2 || C2197c.m7031a(i2, i3, i4);
    }

    private static int m7033b(int i, int i2) {
        return (int) (((float) (f5340b - (C2198d.a * 2))) / C2197c.m7034c(i, i2));
    }

    private static float m7034c(int i, int i2) {
        return i2 > 0 ? ((float) i) / ((float) i2) : -1.0f;
    }
}
