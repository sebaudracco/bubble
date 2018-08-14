package com.facebook.ads.internal.p056q.p057a;

import org.json.JSONArray;

public enum C2109c {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11),
    UNIFIED_LOGGING(16),
    HTTP_LINKS(17);
    
    public static final C2109c[] f5011o = null;
    private static final String f5012q = null;
    private final int f5014p;

    static {
        f5011o = new C2109c[]{LINK_AD_V2, APP_ENGAGEMENT_AD, AD_CHOICES, JS_TRIGGER_NO_AUTO_IMP_LOGGING, NATIVE_CLOSE_BUTTON, UNIFIED_LOGGING, HTTP_LINKS};
        JSONArray jSONArray = new JSONArray();
        C2109c[] c2109cArr = f5011o;
        int length = c2109cArr.length;
        int i;
        while (i < length) {
            jSONArray.put(c2109cArr[i].m6769a());
            i++;
        }
        f5012q = jSONArray.toString();
    }

    private C2109c(int i) {
        this.f5014p = i;
    }

    public static String m6768b() {
        return f5012q;
    }

    public int m6769a() {
        return this.f5014p;
    }

    public String toString() {
        return String.valueOf(this.f5014p);
    }
}
