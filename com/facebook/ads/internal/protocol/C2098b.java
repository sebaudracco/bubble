package com.facebook.ads.internal.protocol;

public class C2098b extends Exception {
    private final AdErrorType f4971a;
    private final String f4972b;

    public C2098b(AdErrorType adErrorType, String str) {
        this(adErrorType, str, null);
    }

    public C2098b(AdErrorType adErrorType, String str, Throwable th) {
        super(str, th);
        this.f4971a = adErrorType;
        this.f4972b = str;
    }

    public AdErrorType m6750a() {
        return this.f4971a;
    }

    public String m6751b() {
        return this.f4972b;
    }
}
