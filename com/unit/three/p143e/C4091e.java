package com.unit.three.p143e;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class C4091e {
    private static ScheduledExecutorService f9477a = Executors.newScheduledThreadPool(5);

    public static void m12655a(Runnable runnable) {
        f9477a.execute(runnable);
    }
}
