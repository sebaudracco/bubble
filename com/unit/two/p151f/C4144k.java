package com.unit.two.p151f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.unit.two.p147c.C4096a;

public final class C4144k {
    public static final String f9687a = C4096a.f9531o;
    public static final String f9688b = C4096a.f9532p;
    public static final String f9689c = C4096a.f9533q;
    public static final String f9690d = C4096a.f9535s;
    public static final String f9691e = C4096a.f9536t;
    private static final String f9692f = C4096a.f9516a;
    private static final String f9693g = C4096a.f9517b;
    private static final String f9694h = C4096a.f9518c;
    private static final String f9695i = C4096a.f9521e;
    private static final String f9696j = C4096a.f9522f;
    private static final String f9697k = C4096a.f9524h;
    private static final String f9698l = C4096a.f9525i;
    private static final String f9699m = C4096a.f9526j;
    private static final String f9700n = C4096a.f9527k;
    private static final String f9701o = C4096a.f9528l;
    private static final String f9702p = C4096a.f9529m;
    private static final String f9703q = C4096a.f9530n;

    static {
        String str = C4096a.f9519d;
        str = C4096a.f9523g;
        str = C4096a.f9534r;
    }

    private static SharedPreferences m12793a(String str, Context context) {
        return context != null ? VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0) : null;
    }

    public static String m12794a(Context context) {
        return C4144k.m12818l(context).getString(f9695i, null);
    }

    public static void m12795a(Context context, int i) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putInt(f9699m, i);
        edit.apply();
    }

    public static void m12796a(Context context, long j) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putLong(f9698l, j);
        edit.apply();
    }

    public static void m12797a(Context context, String str) {
        Editor edit = C4144k.m12818l(context).edit();
        edit.putString(f9695i, str);
        edit.apply();
    }

    public static void m12798a(Context context, String str, long j) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static void m12799a(Context context, String str, Object obj) {
        C4144k.m12800a(context, f9703q, str, obj);
    }

    private static void m12800a(Context context, String str, String str2, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        Editor edit = C4144k.m12793a(str, context).edit();
        if (C4096a.f9537u.equals(simpleName)) {
            edit.putString(str2, (String) obj);
        } else if (C4096a.f9538v.equals(simpleName)) {
            edit.putInt(str2, ((Integer) obj).intValue());
        } else if (C4096a.f9539w.equals(simpleName)) {
            edit.putBoolean(str2, ((Boolean) obj).booleanValue());
        } else if (C4096a.f9540x.equals(simpleName)) {
            edit.putFloat(str2, ((Float) obj).floatValue());
        } else if (C4096a.f9541y.equals(simpleName)) {
            edit.putLong(str2, ((Long) obj).longValue());
        }
        edit.apply();
    }

    public static String m12801b(Context context) {
        return C4144k.m12818l(context).getString(f9693g, null);
    }

    public static void m12802b(Context context, long j) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putLong(f9700n, j);
        edit.apply();
    }

    public static void m12803b(Context context, String str) {
        Editor edit = C4144k.m12818l(context).edit();
        edit.putString(f9693g, str);
        edit.apply();
    }

    public static void m12804b(Context context, String str, Object obj) {
        C4144k.m12800a(context, f9702p, str, obj);
    }

    public static String m12805c(Context context) {
        return C4144k.m12818l(context).getString(f9694h, null);
    }

    public static void m12806c(Context context, long j) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putLong(f9701o, j);
        edit.apply();
    }

    public static void m12807c(Context context, String str) {
        Editor edit = C4144k.m12819m(context).edit();
        edit.putString(f9697k, str);
        edit.apply();
    }

    public static long m12808d(Context context, String str) {
        return C4144k.m12819m(context).getLong(str, 0);
    }

    public static String m12809d(Context context) {
        return C4144k.m12819m(context).getString(f9697k, "");
    }

    public static void m12810d(Context context, long j) {
        if (C4144k.m12817k(context) == 0 && j != 0) {
            Editor edit = C4144k.m12819m(context).edit();
            edit.putLong(C4096a.f9490A, j);
            edit.apply();
        }
    }

    public static long m12811e(Context context) {
        return C4144k.m12819m(context).getLong(f9698l, 0);
    }

    public static SharedPreferences m12812f(Context context) {
        return C4144k.m12793a(f9703q, context);
    }

    public static SharedPreferences m12813g(Context context) {
        return C4144k.m12793a(f9702p, context);
    }

    public static int m12814h(Context context) {
        return C4144k.m12819m(context).getInt(f9699m, 0);
    }

    public static long m12815i(Context context) {
        return C4144k.m12819m(context).getLong(f9700n, 0);
    }

    public static long m12816j(Context context) {
        return C4144k.m12819m(context).getLong(f9701o, 0);
    }

    public static long m12817k(Context context) {
        return C4144k.m12819m(context).getLong(C4096a.f9542z, 0);
    }

    private static SharedPreferences m12818l(Context context) {
        return context.getSharedPreferences(f9692f, 0);
    }

    private static SharedPreferences m12819m(Context context) {
        return context.getSharedPreferences(f9696j, 0);
    }
}
