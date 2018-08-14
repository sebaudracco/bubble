package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Pair;
import com.mopub.common.GpsHelper;
import com.yandex.metrica.impl.ob.C4484g;
import com.yandex.metrica.impl.ob.C4487l;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GoogleAdvertisingIdGetter {
    private volatile String f11554a = null;
    private volatile Boolean f11555b = null;
    private final Object f11556c = new Object();
    private volatile FutureTask<Pair<String, Boolean>> f11557d;

    private interface C4298c<T> {
        T mo6968b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException;
    }

    class C42992 implements C4298c<String> {
        C42992() {
        }

        public /* synthetic */ Object mo6968b(Future future) throws InterruptedException, ExecutionException {
            return m14425a(future);
        }

        public String m14425a(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException {
            return (String) ((Pair) future.get()).first;
        }
    }

    private interface GoogleAdvertisingInfo extends IInterface {

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private IBinder f11550a;

                GoogleAdvertisingInfoImplementation(IBinder binder) {
                    this.f11550a = binder;
                }

                public IBinder asBinder() {
                    return this.f11550a;
                }

                public String getId() throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this.f11550a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        String readString = obtain2.readString();
                        return readString;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                public boolean getEnabled(boolean paramBoolean) throws RemoteException {
                    boolean z = true;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        obtain.writeInt(paramBoolean ? 1 : 0);
                        this.f11550a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }

            public static GoogleAdvertisingInfo Create(IBinder binder) {
                if (binder == null) {
                    return null;
                }
                IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(binder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
                int i = 0;
                switch (code) {
                    case 1:
                        data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String id = getId();
                        reply.writeNoException();
                        reply.writeString(id);
                        return true;
                    case 2:
                        boolean z;
                        data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        if (data.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z = getEnabled(z);
                        reply.writeNoException();
                        if (z) {
                            i = 1;
                        }
                        reply.writeInt(i);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
        }

        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;
    }

    private class C4300a implements ServiceConnection {
        private boolean f11551a;
        private final BlockingQueue<IBinder> f11552b;

        private C4300a() {
            this.f11551a = false;
            this.f11552b = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                this.f11552b.put(service);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder m14427a() throws InterruptedException {
            if (this.f11551a) {
                throw new IllegalStateException();
            }
            this.f11551a = true;
            return (IBinder) this.f11552b.take();
        }
    }

    private static class C4301b {
        static final GoogleAdvertisingIdGetter f11553a = new GoogleAdvertisingIdGetter();
    }

    static /* synthetic */ void m14433b(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        ServiceConnection c4300a = new C4300a();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, c4300a, 1)) {
            try {
                GoogleAdvertisingInfo Create = GoogleAdvertisingInfoBinder.Create(c4300a.m14427a());
                String id = Create.getId();
                Boolean valueOf = Boolean.valueOf(Create.getEnabled(true));
                synchronized (googleAdvertisingIdGetter) {
                    googleAdvertisingIdGetter.m14431a(id);
                    googleAdvertisingIdGetter.f11555b = valueOf;
                }
            } catch (Exception e) {
            } finally {
                context.unbindService(c4300a);
            }
        }
    }

    public void m14437a(final Context context) {
        if (this.f11557d == null) {
            synchronized (this.f11556c) {
                if (this.f11557d == null) {
                    this.f11557d = new FutureTask(new Callable<Pair<String, Boolean>>(this) {
                        final /* synthetic */ GoogleAdvertisingIdGetter f11549b;

                        public /* synthetic */ Object call() throws Exception {
                            return m14423a();
                        }

                        public Pair<String, Boolean> m14423a() {
                            Context applicationContext = context.getApplicationContext();
                            if (GoogleAdvertisingIdGetter.m14435d(applicationContext)) {
                                GoogleAdvertisingIdGetter.m14430a(C4301b.f11553a, applicationContext);
                            }
                            if (!this.f11549b.m14440c()) {
                                GoogleAdvertisingIdGetter.m14433b(C4301b.f11553a, applicationContext);
                            }
                            return new Pair(this.f11549b.f11554a, this.f11549b.f11555b);
                        }
                    });
                    new Thread(this.f11557d).start();
                }
            }
        }
    }

    private void m14431a(String str) {
        C4484g.m16084a().m16092b(new C4487l(str));
        this.f11554a = str;
    }

    private <T> T m14428a(Context context, C4298c<T> c4298c) {
        m14437a(context);
        try {
            return c4298c.mo6968b(this.f11557d);
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e2) {
            return null;
        }
    }

    public String m14439b(Context context) {
        return (String) m14428a(context, new C42992());
    }

    public String m14436a() {
        return this.f11554a;
    }

    public Boolean m14438b() {
        return this.f11555b;
    }

    public synchronized boolean m14440c() {
        boolean z;
        z = (this.f11554a == null || this.f11555b == null) ? false : true;
        return z;
    }

    private static boolean m14435d(Context context) {
        boolean z = false;
        try {
            z = Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context}).equals(Integer.valueOf(0));
        } catch (Exception e) {
        }
        return z;
    }

    static /* synthetic */ void m14430a(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            String str = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            Boolean bool = (Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
            synchronized (googleAdvertisingIdGetter) {
                googleAdvertisingIdGetter.m14431a(str);
                googleAdvertisingIdGetter.f11555b = bool;
            }
        } catch (Exception e) {
        }
    }
}
