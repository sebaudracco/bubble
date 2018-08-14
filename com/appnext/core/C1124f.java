package com.appnext.core;

import android.content.Context;
import com.appnext.core.C1130h.C1129a;
import com.mopub.common.GpsHelper;

public class C1124f {
    public static final int lE = 0;
    private static String lF = "com.google.android.gms.common.GooglePlayServicesUtil";
    private static String lG = "com.google.android.gms.ads.identifier.AdvertisingIdClient";

    static boolean m2329t(Context context) {
        try {
            Object da = new C1129a(null, "isGooglePlayServicesAvailable").m2365b(Class.forName(lF)).m2364a(Context.class, context).da();
            if (da == null || ((Integer) da).intValue() != 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static String m2327b(Context context, boolean z) {
        String str = "";
        if (C1124f.m2329t(context)) {
            if (z && C1124f.m2328q(context)) {
                return "";
            }
            try {
                Object da = new C1129a(null, "getAdvertisingIdInfo").m2365b(Class.forName(lG)).m2364a(Context.class, context).da();
                if (da != null) {
                    return C1124f.m2325a(da, null);
                }
            } catch (Throwable th) {
            }
        }
        return "";
    }

    public static boolean m2328q(Context context) {
        boolean z = false;
        if (C1124f.m2329t(context)) {
            try {
                Object da = new C1129a(null, "getAdvertisingIdInfo").m2365b(Class.forName(lG)).m2364a(Context.class, context).da();
                if (da != null) {
                    z = C1124f.m2326a(da, false);
                }
            } catch (Throwable th) {
            }
        }
        return z;
    }

    static String m2325a(Object obj, String str) {
        try {
            return (String) new C1129a(obj, "getId").da();
        } catch (Throwable th) {
            return str;
        }
    }

    static boolean m2326a(Object obj, boolean z) {
        try {
            Boolean bool = (Boolean) new C1129a(obj, GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY).da();
            if (bool != null) {
                z = bool.booleanValue();
            }
        } catch (Throwable th) {
        }
        return z;
    }

    @Deprecated
    public static void cT() {
        String str = "java.lang.Class";
        lF = str;
        lG = str;
    }
}
