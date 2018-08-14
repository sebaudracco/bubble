package com.facebook.ads.internal.p056q.p057a;

import java.util.UUID;

public class C2122m {
    private static final String f5049a = C2122m.class.getSimpleName();
    private static volatile boolean f5050b = false;
    private static double f5051c;
    private static String f5052d;

    public static void m6804a() {
        if (!f5050b) {
            synchronized (f5049a) {
                if (!f5050b) {
                    f5050b = true;
                    f5051c = ((double) System.currentTimeMillis()) / 1000.0d;
                    f5052d = UUID.randomUUID().toString();
                }
            }
        }
    }

    public static double m6805b() {
        return f5051c;
    }

    public static String m6806c() {
        return f5052d;
    }
}
