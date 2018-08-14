package com.elephant.data.p035a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.LinkedBlockingQueue;

final class C1721b implements ServiceConnection {
    private boolean f3533a;
    private final LinkedBlockingQueue f3534b;

    private C1721b() {
        this.f3533a = false;
        this.f3534b = new LinkedBlockingQueue(1);
    }

    public final IBinder m4972a() {
        if (this.f3533a) {
            throw new IllegalStateException();
        }
        this.f3533a = true;
        return (IBinder) this.f3534b.take();
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.f3534b.put(iBinder);
        } catch (InterruptedException e) {
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
