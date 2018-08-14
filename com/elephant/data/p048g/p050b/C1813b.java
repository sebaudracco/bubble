package com.elephant.data.p048g.p050b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.elephant.data.p048g.C1811a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.p049a.C1810b;

public final class C1813b {
    private static String f3844a = C1814b.bG;
    private static String f3845b = C1814b.bH;
    private static String f3846c = C1814b.bI;
    private static String f3847d = C1814b.bJ;
    private static final String f3848e = C1814b.bK;
    private static final String f3849f = C1814b.bL;
    private static final String f3850g = C1814b.bQ;
    private static final String f3851h = C1814b.bR;
    private static final String f3852i = C1814b.bS;
    private static final String f3853j = C1814b.bT;
    private static long f3854k;

    static {
        String str = C1814b.bF;
        str = C1814b.bM;
        str = C1814b.bN;
        str = C1814b.bO;
        str = C1814b.bP;
    }

    public static String m5252a(Context context) {
        return C1812a.m5243a(context).getString(f3844a, "");
    }

    public static void m5253a(Context context, int i) {
        C1812a.m5245a(context, f3850g, Integer.valueOf(i));
    }

    public static void m5254a(Context context, long j) {
        C1812a.m5245a(context, f3847d, Long.valueOf(j));
    }

    public static void m5255a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            C1812a.m5245a(context, f3844a, str);
        }
    }

    public static void m5256a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String a = C1810b.m5232a(str2, C1814b.bU);
            Editor edit = C1812a.m5247b(context).edit();
            edit.putString(str, a);
            C1812a.m5246a(edit);
        }
    }

    public static void m5257a(Context context, boolean z) {
        C1811a.m5239a(context, z);
    }

    public static String m5258b(Context context) {
        return C1812a.m5243a(context).getString(f3845b, "");
    }

    public static void m5259b(Context context, long j) {
        long j2 = C1812a.m5243a(context).getLong(f3848e, 0) + 1;
        Editor edit = C1812a.m5243a(context).edit();
        edit.putLong(f3849f, j);
        edit.putLong(f3848e, j2);
        C1812a.m5246a(edit);
    }

    public static void m5260b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            C1812a.m5245a(context, f3845b, str);
        }
    }

    public static String m5261c(Context context) {
        return C1812a.m5243a(context).getString(f3846c, "");
    }

    public static void m5262c(Context context, long j) {
        if (C1813b.m5269f(context) == 0 && j != 0) {
            Editor edit = C1812a.m5243a(context).edit();
            edit.putLong(C1814b.bV, j);
            edit.commit();
        }
    }

    public static void m5263c(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            C1812a.m5245a(context, f3846c, str);
        }
    }

    public static long m5264d(Context context) {
        return C1812a.m5243a(context).getLong(f3847d, 0);
    }

    public static void m5265d(Context context, long j) {
        C1812a.m5245a(context, f3851h, Long.valueOf(j));
    }

    public static void m5266d(Context context, String str) {
        Editor edit = C1812a.m5250d(context).edit();
        edit.putString(f3853j, str);
        edit.commit();
    }

    public static void m5267e(Context context) {
        Editor edit = C1812a.m5251e(context).edit();
        edit.putLong(f3848e, 0);
        C1812a.m5246a(edit);
    }

    public static void m5268e(Context context, long j) {
        Editor edit = C1812a.m5250d(context).edit();
        edit.putLong(f3852i, j);
        edit.commit();
    }

    public static long m5269f(Context context) {
        if (f3854k > 0) {
            return f3854k;
        }
        long j = C1812a.m5243a(context).getLong(C1814b.bW, 0);
        f3854k = j;
        return j;
    }

    public static int m5270g(Context context) {
        return C1812a.m5243a(context).getInt(f3850g, 0);
    }

    public static long m5271h(Context context) {
        return C1812a.m5243a(context).getLong(f3851h, 0);
    }

    public static boolean m5272i(Context context) {
        return C1811a.m5241b(context);
    }

    public static long m5273j(Context context) {
        return C1812a.m5250d(context).getLong(f3852i, 0);
    }

    public static String m5274k(Context context) {
        return C1812a.m5250d(context).getString(f3853j, "");
    }
}
