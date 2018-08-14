package com.google.android.gms.internal.ads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zzda implements ThreadFactory {
    private final ThreadFactory zzsk = Executors.defaultThreadFactory();

    zzda() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zzsk.newThread(runnable);
        newThread.setName(String.valueOf(newThread.getName()).concat(":"));
        return newThread;
    }
}
