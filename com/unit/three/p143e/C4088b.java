package com.unit.three.p143e;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public final class C4088b {
    private static C4088b f9465c;
    private ScheduledExecutorService f9466a;
    private Executor f9467b;

    private C4088b() {
    }

    public static C4088b m12615a() {
        if (f9465c == null) {
            f9465c = new C4088b();
        }
        return f9465c;
    }

    public final ScheduledThreadPoolExecutor m12616b() {
        if (this.f9466a == null || this.f9466a.isShutdown() || this.f9466a.isTerminated()) {
            this.f9466a = Executors.newScheduledThreadPool(2);
        }
        return (ScheduledThreadPoolExecutor) this.f9466a;
    }

    public final Executor m12617c() {
        if (this.f9467b == null || ((this.f9467b instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) this.f9467b).isShutdown() || ((ThreadPoolExecutor) this.f9467b).isTerminated() || ((ThreadPoolExecutor) this.f9467b).isTerminating()))) {
            if (VERSION.SDK_INT < 11) {
                return Executors.newSingleThreadExecutor();
            }
            this.f9467b = Executors.newFixedThreadPool(5);
        }
        return this.f9467b;
    }
}
