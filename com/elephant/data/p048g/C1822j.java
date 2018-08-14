package com.elephant.data.p048g;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class C1822j implements ThreadFactory {
    private final AtomicInteger f3936a = new AtomicInteger();

    C1822j() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, C1814b.cl + this.f3936a.getAndIncrement());
    }
}
