package com.inmobi.commons.core.p116c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;

/* compiled from: KeyValueStore */
public final class C3116c {
    private static HashMap<String, C3116c> f7628a = new HashMap();
    private static final Object f7629b = new Object();
    private SharedPreferences f7630c;

    private C3116c(Context context, String str) {
        this.f7630c = context.getSharedPreferences(str, 0);
    }

    public static String m10142a(String str) {
        return "com.im.keyValueStore." + str;
    }

    public static C3116c m10141a(Context context, String str) {
        String a = C3116c.m10142a(str);
        C3116c c3116c = (C3116c) f7628a.get(a);
        if (c3116c == null) {
            synchronized (f7629b) {
                c3116c = (C3116c) f7628a.get(a);
                if (c3116c != null) {
                } else {
                    c3116c = new C3116c(context, a);
                    f7628a.put(a, c3116c);
                }
            }
        }
        return c3116c;
    }

    public static C3116c m10143b(String str) {
        return C3116c.m10141a(C3105a.m10078b(), str);
    }

    public void m10146a(String str, String str2) {
        Editor edit = this.f7630c.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String m10150b(String str, String str2) {
        return this.f7630c.getString(str, str2);
    }

    public void m10144a(String str, int i) {
        Editor edit = this.f7630c.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public int m10148b(String str, int i) {
        return this.f7630c.getInt(str, i);
    }

    public void m10145a(String str, long j) {
        Editor edit = this.f7630c.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public long m10149b(String str, long j) {
        return this.f7630c.getLong(str, j);
    }

    public void m10147a(String str, boolean z) {
        Editor edit = this.f7630c.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public boolean m10151b(String str, boolean z) {
        return this.f7630c.getBoolean(str, z);
    }

    public boolean m10152c(String str) {
        return this.f7630c.contains(str);
    }
}
