package com.google.android.gms.gcm;

import android.support.annotation.NonNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zze implements ThreadFactory {
    private final AtomicInteger zzx = new AtomicInteger(1);

    zze(GcmTaskService gcmTaskService) {
    }

    public final Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread(runnable, "gcm-task#" + this.zzx.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
