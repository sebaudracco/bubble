package com.yandex.metrica.impl.utils;

import android.util.Log;

public abstract class C4519c {
    private volatile boolean f12576a = false;

    abstract String mo7120c(String str, Object[] objArr);

    abstract String mo7121d();

    abstract String mo7122e();

    public void m16240a() {
        this.f12576a = true;
    }

    public boolean m16247b() {
        return this.f12576a;
    }

    public C4519c(boolean z) {
        this.f12576a = z;
    }

    public void m16243a(String str) {
        m16241a(4, str);
    }

    public void m16245b(String str) {
        m16241a(5, str);
    }

    public void m16250c(String str) {
        m16241a(6, str);
    }

    public void m16244a(String str, Object... objArr) {
        m16242a(4, str, objArr);
    }

    public void m16246b(String str, Object... objArr) {
        m16242a(5, str, objArr);
    }

    void m16241a(int i, String str) {
        if (this.f12576a) {
            Log.println(i, mo7121d(), mo7122e() + C4519c.m16238d(str));
        }
    }

    void m16242a(int i, String str, Object... objArr) {
        if (this.f12576a) {
            Log.println(i, mo7121d(), m16239d(str, objArr));
        }
    }

    private String m16239d(String str, Object[] objArr) {
        try {
            return mo7122e() + mo7120c(C4519c.m16238d(str), objArr);
        } catch (Throwable th) {
            return m16248c();
        }
    }

    String m16248c() {
        return mo7122e();
    }

    private static String m16238d(String str) {
        return str == null ? "" : str;
    }
}
