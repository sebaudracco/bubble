package com.facebook.ads.internal.p071p.p072a;

public abstract class C2055l {
    protected String f4890a = "";
    protected C2057j f4891b;
    protected String f4892c;
    protected byte[] f4893d;

    public C2055l(String str, C2062p c2062p) {
        if (str != null) {
            this.f4890a = str;
        }
        if (c2062p != null) {
            this.f4890a += "?" + c2062p.m6624a();
        }
    }

    public String m6608a() {
        return this.f4890a;
    }

    public C2057j m6609b() {
        return this.f4891b;
    }

    public String m6610c() {
        return this.f4892c;
    }

    public byte[] m6611d() {
        return this.f4893d;
    }
}
