package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

@zzadh
public final class zzamg {
    private Handler mHandler = null;
    private final Object mLock = new Object();
    private HandlerThread zzcts = null;
    private int zzctt = 0;

    public final Handler getHandler() {
        return this.mHandler;
    }

    public final Looper zzsa() {
        Looper looper;
        synchronized (this.mLock) {
            if (this.zzctt != 0) {
                Preconditions.checkNotNull(this.zzcts, "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzcts == null) {
                zzakb.m3428v("Starting the looper thread.");
                this.zzcts = new HandlerThread("LooperProvider");
                this.zzcts.start();
                this.mHandler = new Handler(this.zzcts.getLooper());
                zzakb.m3428v("Looper thread started.");
            } else {
                zzakb.m3428v("Resuming the looper thread");
                this.mLock.notifyAll();
            }
            this.zzctt++;
            looper = this.zzcts.getLooper();
        }
        return looper;
    }
}
