package com.moat.analytics.mobile.vng;

import android.util.Log;
import com.mopub.mobileads.VastExtensionXmlManager;

class C3511p {
    C3511p() {
    }

    private static String m11975a(String str) {
        return VastExtensionXmlManager.MOAT + str;
    }

    static void m11976a(int i, String str, Object obj, String str2) {
        if (!C3532w.m12009a().f8996b) {
            return;
        }
        if (obj == null) {
            Log.println(i, C3511p.m11975a(str), String.format("message = %s", new Object[]{str2}));
            return;
        }
        Log.println(i, C3511p.m11975a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
    }

    static void m11977a(String str, Object obj, String str2, Throwable th) {
        if (C3532w.m12009a().f8996b) {
            Log.e(C3511p.m11975a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    static void m11978a(String str, String str2) {
        if (!C3532w.m12009a().f8996b && ((C3500k) MoatAnalytics.getInstance()).f8940a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    static void m11979b(int i, String str, Object obj, String str2) {
        if (C3532w.m12009a().f8997c) {
            String a = C3511p.m11975a(str);
            String str3 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, a, String.format(str3, objArr));
        }
    }
}
