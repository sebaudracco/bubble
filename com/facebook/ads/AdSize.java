package com.facebook.ads;

import com.facebook.ads.internal.protocol.C1561e;
import java.io.Serializable;

public class AdSize implements Serializable {
    @Deprecated
    public static final AdSize BANNER_320_50 = new AdSize(C1561e.BANNER_320_50);
    public static final AdSize BANNER_HEIGHT_50 = new AdSize(C1561e.BANNER_HEIGHT_50);
    public static final AdSize BANNER_HEIGHT_90 = new AdSize(C1561e.BANNER_HEIGHT_90);
    public static final AdSize INTERSTITIAL = new AdSize(C1561e.INTERSTITIAL);
    public static final AdSize RECTANGLE_HEIGHT_250 = new AdSize(C1561e.RECTANGLE_HEIGHT_250);
    private final int f2539a;
    private final int f2540b;

    public AdSize(int i, int i2) {
        this.f2539a = i;
        this.f2540b = i2;
    }

    private AdSize(C1561e c1561e) {
        this.f2539a = c1561e.m3419a();
        this.f2540b = c1561e.m3420b();
    }

    public static AdSize fromWidthAndHeight(int i, int i2) {
        return (INTERSTITIAL.f2540b == i2 && INTERSTITIAL.f2539a == i) ? INTERSTITIAL : (BANNER_320_50.f2540b == i2 && BANNER_320_50.f2539a == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.f2540b == i2 && BANNER_HEIGHT_50.f2539a == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.f2540b == i2 && BANNER_HEIGHT_90.f2539a == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.f2540b == i2 && RECTANGLE_HEIGHT_250.f2539a == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.f2539a != adSize.f2539a ? false : this.f2540b == adSize.f2540b;
    }

    public int getHeight() {
        return this.f2540b;
    }

    public int getWidth() {
        return this.f2539a;
    }

    public int hashCode() {
        return (this.f2539a * 31) + this.f2540b;
    }

    public C1561e toInternalAdSize() {
        return C1561e.m3418a(this.f2539a, this.f2540b);
    }
}
