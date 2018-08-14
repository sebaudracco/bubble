package com.unit.two.p151f;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import java.util.UUID;

public final class C4138e {
    private static String f9669a;
    private static Boolean f9670b;
    private static final String f9671c = C4096a.aS;
    private static final String f9672d = C4096a.aT;
    private static final String f9673e = C4096a.aU;
    private static final String f9674f = C4096a.aV;

    private static SharedPreferences m12769a(String str, Context context) {
        if (context != null) {
            return VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
        } else {
            throw new IllegalStateException(C4096a.aW);
        }
    }

    public static String m12770a(Context context) {
        if (!TextUtils.isEmpty(f9669a)) {
            return f9669a;
        }
        CharSequence string = C4138e.m12769a(f9673e, context).getString(f9671c, "");
        f9669a = string;
        return !TextUtils.isEmpty(string) ? f9669a : C4138e.m12772c(context);
    }

    public static boolean m12771b(Context context) {
        if (f9670b != null) {
            return f9670b.booleanValue();
        }
        Boolean valueOf = Boolean.valueOf(C4138e.m12769a(f9673e, context).getBoolean(f9672d, false));
        f9670b = valueOf;
        return valueOf.booleanValue();
    }

    private static String m12772c(Context context) {
        String uuid;
        synchronized (f9674f.intern()) {
            if (TextUtils.isEmpty(f9669a)) {
                uuid = UUID.randomUUID().toString();
                C4138e.m12769a(f9673e, context).edit().putString(f9671c, uuid).commit();
                f9669a = uuid;
            } else {
                uuid = f9669a;
            }
        }
        return uuid;
    }
}
