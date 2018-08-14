package com.unit.two.p151f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.unit.two.p147c.C4096a;

public final class C4147n {
    private static final String f9717a = C4096a.f9492C;

    static {
        String str = C4096a.f9491B;
    }

    private static SharedPreferences m12864a(String str, Context context) {
        if (context != null) {
            return VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
        } else {
            throw new IllegalStateException(C4096a.f9495F);
        }
    }

    public static void m12865a(Context context, String str, Object obj) {
        try {
            Editor edit = C4147n.m12864a(f9717a, context).edit();
            if (obj instanceof String) {
                edit.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                edit.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Float) {
                edit.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                edit.putLong(str, ((Long) obj).longValue());
            } else {
                edit.putString(str, String.valueOf(obj));
            }
            C4148o.m12868a(edit);
        } catch (Exception e) {
        }
    }

    public static Object m12866b(Context context, String str, Object obj) {
        SharedPreferences a = C4147n.m12864a(f9717a, context);
        if (obj instanceof String) {
            return a.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(a.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(a.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(a.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(a.getLong(str, ((Long) obj).longValue()));
        }
        throw new IllegalStateException(C4096a.f9493D);
    }
}
