package com.facebook.ads.internal.p056q.p057a;

import java.util.Random;

public class C2123n {
    private static String m6807a() {
        return C2124p.m6811a(Thread.currentThread().getStackTrace());
    }

    public static String m6808a(int i) {
        return (i > 0 && new Random().nextFloat() < 1.0f / ((float) i)) ? C2123n.m6807a() : null;
    }
}
