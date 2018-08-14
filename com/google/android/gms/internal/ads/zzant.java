package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzant implements Runnable {
    private final Future zzcvo;

    zzant(Future future) {
        this.zzcvo = future;
    }

    public final void run() {
        Future future = this.zzcvo;
        if (!future.isDone()) {
            future.cancel(true);
        }
    }
}
