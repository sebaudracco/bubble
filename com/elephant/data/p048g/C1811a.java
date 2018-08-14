package com.elephant.data.p048g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.UUID;

public final class C1811a {
    private static String f3833a;
    private static Boolean f3834b;
    private static final String f3835c = C1814b.am;
    private static final String f3836d = C1814b.an;
    private static final String f3837e = C1814b.ao;
    private static final String f3838f = C1814b.ap;

    private static SharedPreferences m5237a(String str, Context context) {
        if (context != null) {
            return VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
        } else {
            throw new IllegalStateException(C1814b.aq);
        }
    }

    public static String m5238a(Context context) {
        if (!TextUtils.isEmpty(f3833a)) {
            return f3833a;
        }
        CharSequence string = C1811a.m5237a(f3837e, context).getString(f3835c, "");
        f3833a = string;
        return !TextUtils.isEmpty(string) ? f3833a : C1811a.m5242c(context);
    }

    public static synchronized void m5239a(Context context, boolean z) {
        synchronized (C1811a.class) {
            C1811a.m5240b(f3837e, context).putBoolean(f3836d, z).commit();
            f3834b = Boolean.valueOf(z);
        }
    }

    private static Editor m5240b(String str, Context context) {
        return C1811a.m5237a(str, context).edit();
    }

    public static boolean m5241b(Context context) {
        if (f3834b != null) {
            return f3834b.booleanValue();
        }
        Boolean valueOf = Boolean.valueOf(C1811a.m5237a(f3837e, context).getBoolean(f3836d, false));
        f3834b = valueOf;
        return valueOf.booleanValue();
    }

    private static String m5242c(Context context) {
        String uuid;
        synchronized (f3838f.intern()) {
            if (TextUtils.isEmpty(f3833a)) {
                uuid = UUID.randomUUID().toString();
                C1811a.m5240b(f3837e, context).putString(f3835c, uuid).commit();
                f3833a = uuid;
            } else {
                uuid = f3833a;
            }
        }
        return uuid;
    }
}
