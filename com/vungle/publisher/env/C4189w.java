package com.vungle.publisher.env;

import android.os.Build;

/* compiled from: vungle */
public interface C4189w {
    public static final boolean f10060a = "Amazon".equals(Build.MANUFACTURER);
    public static final String f10061b = (f10060a ? "amazon" : "android");
    public static final String f10062c = (f10060a ? "VungleAmazon/" : "VungleDroid/");
}
