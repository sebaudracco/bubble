package com.elephant.data.p048g;

import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class C1821i {
    private static final ThreadFactory f3932a = new C1822j();
    private static final BlockingQueue f3933b = new LinkedBlockingDeque(200);
    private static C1821i f3934c;
    private ThreadPoolExecutor f3935d = new ThreadPoolExecutor(5, 50, 1, TimeUnit.SECONDS, f3933b, f3932a);

    private C1821i() {
    }

    public static void m5345a() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException(C1814b.cm);
        }
    }

    public static void m5346a(Runnable runnable) {
        C1821i.m5347b().f3935d.execute(runnable);
    }

    private static synchronized C1821i m5347b() {
        C1821i c1821i;
        synchronized (C1821i.class) {
            if (f3934c == null) {
                f3934c = new C1821i();
            }
            c1821i = f3934c;
        }
        return c1821i;
    }
}
