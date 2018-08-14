package com.unit.two.p151f;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.LinkedBlockingQueue;

final class C4136c implements ServiceConnection {
    private boolean f9666a;
    private final LinkedBlockingQueue f9667b;

    private C4136c() {
        this.f9666a = false;
        this.f9667b = new LinkedBlockingQueue(1);
    }

    public final IBinder m12766a() {
        if (this.f9666a) {
            throw new IllegalStateException();
        }
        this.f9666a = true;
        return (IBinder) this.f9667b.take();
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.f9667b.put(iBinder);
        } catch (InterruptedException e) {
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
