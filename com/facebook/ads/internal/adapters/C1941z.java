package com.facebook.ads.internal.adapters;

public class C1941z {
    private static final String[] f4487a = new String[]{"com.flurry.android.FlurryAgent", "com.flurry.android.ads.FlurryAdErrorType", "com.flurry.android.ads.FlurryAdNative", "com.flurry.android.ads.FlurryAdNativeAsset", "com.flurry.android.ads.FlurryAdNativeListener"};
    private static final String[] f4488b = new String[]{"com.inmobi.ads.InMobiNative", "com.inmobi.sdk.InMobiSdk"};
    private static final String[] f4489c = new String[]{"com.google.android.gms.ads.formats.NativeAdView"};

    public static boolean m6122a(C1895g c1895g) {
        switch (c1895g) {
            case AN:
                return true;
            case YAHOO:
                return C1941z.m6124a(f4487a);
            case INMOBI:
                return C1941z.m6124a(f4488b);
            case ADMOB:
                return C1941z.m6124a(f4489c);
            default:
                return false;
        }
    }

    private static boolean m6123a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean m6124a(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a : strArr) {
            if (!C1941z.m6123a(a)) {
                return false;
            }
        }
        return true;
    }
}
