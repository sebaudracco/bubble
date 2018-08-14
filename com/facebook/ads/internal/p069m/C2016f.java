package com.facebook.ads.internal.p069m;

public enum C2016f {
    TEST("test"),
    BROWSER_SESSION("browser_session"),
    CLOSE("close"),
    IMPRESSION("impression"),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    VIDEO("video");
    
    private String f4767k;

    private C2016f(String str) {
        this.f4767k = str;
    }

    public static C2016f m6426a(String str) {
        for (C2016f c2016f : C2016f.values()) {
            if (c2016f.f4767k.equalsIgnoreCase(str)) {
                return c2016f;
            }
        }
        return null;
    }

    public String toString() {
        return this.f4767k;
    }
}
