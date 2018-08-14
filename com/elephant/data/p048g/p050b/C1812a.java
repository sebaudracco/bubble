package com.elephant.data.p048g.p050b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.elephant.data.p048g.C1814b;

public final class C1812a {
    private static final String f3839a = C1814b.bX;
    private static final String f3840b = C1814b.bY;
    private static final String f3841c = C1814b.bZ;
    private static final String f3842d = C1814b.cd;
    private static final String f3843e = C1814b.ce;

    static {
        String str = C1814b.ca;
        str = C1814b.cb;
        str = C1814b.cc;
    }

    public static SharedPreferences m5243a(Context context) {
        return C1812a.m5248b(context, f3839a);
    }

    public static void m5244a(Context context, String str) {
        Editor edit = C1812a.m5248b(context, f3840b).edit();
        edit.remove(str);
        C1812a.m5246a(edit);
    }

    public static void m5245a(Context context, String str, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        Editor edit = C1812a.m5248b(context, f3839a).edit();
        if (C1814b.cg.equals(simpleName)) {
            edit.putString(str, (String) obj);
        } else if (C1814b.ch.equals(simpleName)) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (C1814b.ci.equals(simpleName)) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (C1814b.cj.equals(simpleName)) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (C1814b.ck.equals(simpleName)) {
            edit.putLong(str, ((Long) obj).longValue());
        }
        C1812a.m5246a(edit);
    }

    public static void m5246a(Editor editor) {
        if (VERSION.SDK_INT <= 8) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static SharedPreferences m5247b(Context context) {
        return C1812a.m5248b(context, f3840b);
    }

    private static SharedPreferences m5248b(Context context, String str) {
        if (context != null) {
            return VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
        } else {
            throw new NullPointerException(C1814b.cf);
        }
    }

    public static SharedPreferences m5249c(Context context) {
        return C1812a.m5248b(context, f3841c);
    }

    public static SharedPreferences m5250d(Context context) {
        return C1812a.m5248b(context, f3843e);
    }

    public static SharedPreferences m5251e(Context context) {
        return C1812a.m5248b(context, f3842d);
    }
}
