package com.facebook.ads.internal.protocol;

import java.io.Serializable;

public enum C1561e implements Serializable {
    BANNER_320_50(320, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, 250);
    
    private final int f2613f;
    private final int f2614g;

    private C1561e(int i, int i2) {
        this.f2613f = i;
        this.f2614g = i2;
    }

    public static C1561e m3418a(int i, int i2) {
        return (INTERSTITIAL.f2614g == i2 && INTERSTITIAL.f2613f == i) ? INTERSTITIAL : (BANNER_320_50.f2614g == i2 && BANNER_320_50.f2613f == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.f2614g == i2 && BANNER_HEIGHT_50.f2613f == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.f2614g == i2 && BANNER_HEIGHT_90.f2613f == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.f2614g == i2 && RECTANGLE_HEIGHT_250.f2613f == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    public int m3419a() {
        return this.f2613f;
    }

    public int m3420b() {
        return this.f2614g;
    }
}
