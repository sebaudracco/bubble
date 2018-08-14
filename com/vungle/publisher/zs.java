package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
public class zs {
    private final AtomicInteger f11398a = new AtomicInteger();

    public int m14229a(int i, int i2) {
        int a;
        int i3;
        do {
            i3 = this.f11398a.get();
            a = za.m14186a(i3, i, i2);
        } while (!this.f11398a.compareAndSet(i3, a));
        return a;
    }
}
