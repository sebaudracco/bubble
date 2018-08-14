package com.facebook.ads.internal.p066i;

import android.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

class C1993b {
    private static final String f4676a = C1993b.class.getSimpleName();

    C1993b() {
    }

    static String m6304a() {
        JSONObject jSONObject = new JSONObject();
        C1993b.m6305a(jSONObject, "is_emu", String.valueOf(C1993b.m6306b()));
        return jSONObject.toString();
    }

    private static void m6305a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
        }
    }

    private static boolean m6306b() {
        return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT));
    }
}
