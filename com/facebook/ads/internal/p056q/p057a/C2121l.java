package com.facebook.ads.internal.p056q.p057a;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class C2121l implements ThreadFactory {
    protected final AtomicLong f5047a = new AtomicLong();
    private int f5048b = Thread.currentThread().getPriority();

    protected String m6803a() {
        return String.format(Locale.ENGLISH, "com.facebook.ads thread-%d %tF %<tT", new Object[]{Long.valueOf(this.f5047a.incrementAndGet()), Long.valueOf(System.currentTimeMillis())});
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(null, runnable, m6803a(), 0);
        thread.setPriority(this.f5048b);
        return thread;
    }
}
