package com.facebook.ads.internal.protocol;

import android.text.TextUtils;

public class C2097a {
    private final AdErrorType f4969a;
    private final String f4970b;

    public C2097a(int i, String str) {
        this(AdErrorType.adErrorTypeFromCode(i), str);
    }

    public C2097a(AdErrorType adErrorType, String str) {
        if (TextUtils.isEmpty(str)) {
            str = adErrorType.getDefaultErrorMessage();
        }
        this.f4969a = adErrorType;
        this.f4970b = str;
    }

    public static C2097a m6746a(AdErrorType adErrorType, String str) {
        return new C2097a(adErrorType, str);
    }

    public static C2097a m6747a(C2098b c2098b) {
        return new C2097a(c2098b.m6750a(), c2098b.m6751b());
    }

    public AdErrorType m6748a() {
        return this.f4969a;
    }

    public String m6749b() {
        return this.f4970b;
    }
}
