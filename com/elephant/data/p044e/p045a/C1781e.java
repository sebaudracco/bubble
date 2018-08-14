package com.elephant.data.p044e.p045a;

import android.content.Context;
import com.elephant.data.p048g.C1814b;

public final class C1781e {
    static {
        String str = C1814b.eR;
    }

    public static synchronized void m5131a(Context context, String str, String str2, String str3, String str4) {
        synchronized (C1781e.class) {
            new C1782f(context, str3, str2, str, str4).start();
        }
    }

    protected static synchronized void m5132a(Context context, boolean z, boolean z2, String str, String str2) {
        synchronized (C1781e.class) {
            C1785i.m5137a(context).m5164a(true, true, str, str2);
        }
    }
}
