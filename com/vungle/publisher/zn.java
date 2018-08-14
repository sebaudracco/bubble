package com.vungle.publisher;

/* compiled from: vungle */
public class zn {
    public static int m14224a(int i, int i2, int i3) {
        if (i < 0) {
            throw new IllegalArgumentException("retryCount must not be negative, retryCount = " + i);
        } else if (i == 0) {
            return 0;
        } else {
            return (int) ze.m14195a((double) (i - 1), (double) i2, (double) i3);
        }
    }
}
