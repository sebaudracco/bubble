package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzakj implements ThreadFactory {
    private final AtomicInteger zzcnz = new AtomicInteger(1);
    private final /* synthetic */ String zzcrl;

    zzakj(String str) {
        this.zzcrl = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzcrl;
        return new Thread(runnable, new StringBuilder(String.valueOf(str).length() + 23).append("AdWorker(").append(str).append(") #").append(this.zzcnz.getAndIncrement()).toString());
    }
}
