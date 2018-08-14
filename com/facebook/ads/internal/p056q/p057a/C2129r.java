package com.facebook.ads.internal.p056q.p057a;

import java.util.Locale;

public class C2129r {
    public static String m6818a(double d) {
        return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(d)});
    }

    public static String m6819a(long j) {
        return C2129r.m6818a(((double) j) / 1000.0d);
    }
}
