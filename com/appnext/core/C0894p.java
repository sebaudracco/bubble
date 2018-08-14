package com.appnext.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public abstract class C0894p {
    private ServiceConnection mConnection = new C11381(this);
    private Messenger mMessenger;

    class C11381 implements ServiceConnection {
        final /* synthetic */ C0894p mt;

        C11381(C0894p c0894p) {
            this.mt = c0894p;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.mt.mMessenger = new Messenger(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.mt.mMessenger = null;
        }
    }

    protected abstract void mo1916a(Intent intent);

    public void m1822C(Context context) {
        Intent intent = new Intent(context, m1821B());
        mo1916a(intent);
        try {
            context.bindService(intent, this.mConnection, 1);
            Message obtain = Message.obtain(null, AdsService.ADD_PACK, 0, 0);
            obtain.setData(intent.getExtras());
            this.mMessenger.send(obtain);
        } catch (Throwable th) {
            context.startService(intent);
        }
    }

    protected Class<?> m1821B() {
        return AdsService.class;
    }

    public void mo1917d(Context context) {
        try {
            context.unbindService(this.mConnection);
            this.mMessenger = null;
            this.mConnection = null;
        } catch (Throwable th) {
        }
    }
}
