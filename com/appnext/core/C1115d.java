package com.appnext.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public final class C1115d {
    static C1112a lf = null;

    public static final class C1112a {
        private final String lg;
        private final boolean lh;

        C1112a(String str, boolean z) {
            this.lg = str;
            this.lh = z;
        }

        public String getId() {
            return this.lg;
        }

        public boolean cR() {
            return this.lh;
        }
    }

    private static final class C1113b implements ServiceConnection {
        boolean li;
        private final LinkedBlockingQueue<IBinder> lj;

        private C1113b() {
            this.li = false;
            this.lj = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.lj.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (this.li) {
                throw new IllegalStateException();
            }
            this.li = true;
            return (IBinder) this.lj.take();
        }
    }

    private static final class C1114c implements IInterface {
        private IBinder lk;

        public C1114c(IBinder iBinder) {
            this.lk = iBinder;
        }

        public IBinder asBinder() {
            return this.lk;
        }

        public String getId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.lk.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean m2306e(boolean z) throws RemoteException {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.lk.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static C1112a m2307r(Context context) throws Exception {
        if (lf != null) {
            return lf;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        context.getPackageManager().getPackageInfo("com.android.vending", 0);
        ServiceConnection c1113b = new C1113b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, c1113b, 1)) {
            try {
                C1114c c1114c = new C1114c(c1113b.getBinder());
                lf = new C1112a(c1114c.getId(), c1114c.m2306e(true));
                C1112a c1112a = lf;
                return c1112a;
            } finally {
                context.unbindService(c1113b);
            }
        } else {
            throw new IOException("Google Play connection failed");
        }
    }
}
