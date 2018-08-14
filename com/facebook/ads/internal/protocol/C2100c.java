package com.facebook.ads.internal.protocol;

public enum C2100c {
    f4975a,
    BANNER,
    INTERSTITIAL,
    NATIVE,
    REWARDED_VIDEO;

    static /* synthetic */ class C20991 {
        static final /* synthetic */ int[] f4973a = null;

        static {
            f4974b = new int[C2100c.values().length];
            try {
                f4974b[C2100c.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4974b[C2100c.BANNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4974b[C2100c.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4974b[C2100c.REWARDED_VIDEO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            f4973a = new int[f.values().length];
            try {
                f4973a[f.j.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4973a[f.c.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4973a[f.d.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4973a[f.b.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4973a[f.e.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4973a[f.g.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4973a[f.h.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4973a[f.i.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f4973a[f.f.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f4973a[f.k.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    public static C2100c m6752a(f fVar) {
        switch (C20991.f4973a[fVar.ordinal()]) {
            case 1:
                return NATIVE;
            case 2:
            case 3:
            case 4:
            case 5:
                return BANNER;
            case 6:
            case 7:
            case 8:
            case 9:
                return INTERSTITIAL;
            case 10:
                return REWARDED_VIDEO;
            default:
                return f4975a;
        }
    }

    public AdPlacementType m6753a() {
        switch (this) {
            case INTERSTITIAL:
                return AdPlacementType.INTERSTITIAL;
            case BANNER:
                return AdPlacementType.BANNER;
            case NATIVE:
                return AdPlacementType.NATIVE;
            case REWARDED_VIDEO:
                return AdPlacementType.REWARDED_VIDEO;
            default:
                return AdPlacementType.UNKNOWN;
        }
    }
}
