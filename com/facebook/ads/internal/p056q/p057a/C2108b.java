package com.facebook.ads.internal.p056q.p057a;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2108b {
    private static boolean f4995a = false;
    private static boolean f4996b = false;

    @Nullable
    public static synchronized String m6765a(String str) {
        String property;
        synchronized (C2108b.class) {
            property = !C2108b.m6766a() ? null : System.getProperty("fb.e2e." + str);
        }
        return property;
    }

    public static synchronized boolean m6766a() {
        boolean z;
        synchronized (C2108b.class) {
            if (!f4996b) {
                f4995a = SchemaSymbols.ATTVAL_TRUE.equals(System.getProperty("fb.running_e2e"));
                f4996b = true;
            }
            z = f4995a;
        }
        return z;
    }

    public static synchronized boolean m6767b(String str) {
        boolean z;
        synchronized (C2108b.class) {
            z = !TextUtils.isEmpty(C2108b.m6765a(str));
        }
        return z;
    }
}
