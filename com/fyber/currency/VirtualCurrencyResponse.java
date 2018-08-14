package com.fyber.currency;

import com.fyber.p094b.C2516n.C2515a;

public class VirtualCurrencyResponse implements C2515a {
    private double f6427a;
    private String f6428b;
    private String f6429c;
    private String f6430d;
    private boolean f6431e;

    public VirtualCurrencyResponse(double d, String str, String str2, String str3, boolean z) {
        this.f6427a = d;
        this.f6428b = str;
        this.f6429c = str2;
        this.f6430d = str3;
        this.f6431e = z;
    }

    public double getDeltaOfCoins() {
        return this.f6427a;
    }

    public String getLatestTransactionId() {
        return this.f6428b;
    }

    public String getCurrencyId() {
        return this.f6429c;
    }

    public String getCurrencyName() {
        return this.f6430d;
    }

    public boolean isDefault() {
        return this.f6431e;
    }
}
