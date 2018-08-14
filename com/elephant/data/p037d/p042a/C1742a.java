package com.elephant.data.p037d.p042a;

import android.text.TextUtils;

public final class C1742a {
    private long f3587a;
    private String f3588b;

    public C1742a(long j, String str) {
        this.f3587a = j;
        this.f3588b = str;
    }

    public static C1742a m5013a(String str) {
        return (TextUtils.isEmpty(str) || str.length() < 14) ? null : new C1742a(Long.valueOf(str.substring(0, 13)).longValue(), str.substring(13));
    }

    public final long m5014a() {
        return this.f3587a;
    }

    public final String m5015b() {
        return this.f3588b;
    }

    public final String toString() {
        return this.f3587a + this.f3588b;
    }
}
