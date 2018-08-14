package com.elephant.data.p037d.p038b;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public final class C1746c {
    private static List f3606b;
    private static C1746c f3607c;
    private Context f3608a;
    private C1748e f3609d;

    private C1746c(Context context) {
        C1747d c1747d = new C1747d(this);
        this.f3608a = context;
        this.f3609d = C1748e.m5034a(context);
    }

    public static C1746c m5030a(Context context) {
        if (f3607c == null) {
            f3607c = new C1746c(context);
        }
        return f3607c;
    }

    static /* synthetic */ void m5031a(C1746c c1746c, C1744a c1744a) {
        if (c1744a != null && !TextUtils.isEmpty(c1744a.f3604d)) {
            if (f3606b == null) {
                f3606b = new ArrayList();
            }
            if (f3606b.contains(c1744a)) {
                f3606b.remove(c1744a);
            }
            f3606b.add(c1744a);
        }
    }

    public final void m5032a() {
        if (this.f3609d != null) {
            this.f3609d.m5045a();
        }
    }
}
