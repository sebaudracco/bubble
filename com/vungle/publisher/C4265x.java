package com.vungle.publisher;

/* compiled from: vungle */
public enum C4265x {
    fullscreen,
    flexview,
    flexfeed;

    public static C4265x m14121a(String str) {
        if (str == null || str.equals(flexfeed.name())) {
            return fullscreen;
        }
        for (C4265x c4265x : C4265x.values()) {
            if (c4265x.toString().equals(str)) {
                return c4265x;
            }
        }
        return fullscreen;
    }
}
