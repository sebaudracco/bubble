package com.bgjd.ici.p029d;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.mopub.common.GpsHelper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@TargetApi(9)
public class C1451c {
    private static C1451c f2235c = null;
    private String f2236a = "";
    private boolean f2237b = false;

    private interface C1447a extends IInterface {

        public static abstract class C1449a extends Binder implements C1447a {

            private static class C1448a implements C1447a {
                private final IBinder f2232a;

                C1448a(IBinder iBinder) {
                    this.f2232a = iBinder;
                }

                public IBinder asBinder() {
                    return this.f2232a;
                }

                public String mo2326a() throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this.f2232a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        String readString = obtain2.readString();
                        return readString;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                public boolean mo2327a(boolean z) throws RemoteException {
                    boolean z2 = true;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        obtain.writeInt(z ? 1 : 0);
                        this.f2232a.transact(2, obtain, obtain2, 0);
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

            public static C1447a m2983a(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof C1447a)) {
                    return new C1448a(iBinder);
                }
                return (C1447a) queryLocalInterface;
            }

            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                int i3 = 0;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String a = mo2326a();
                        parcel2.writeNoException();
                        parcel2.writeString(a);
                        return true;
                    case 2:
                        boolean z;
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        if (parcel.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z = mo2327a(z);
                        parcel2.writeNoException();
                        if (z) {
                            i3 = 1;
                        }
                        parcel2.writeInt(i3);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
        }

        String mo2326a() throws RemoteException;

        boolean mo2327a(boolean z) throws RemoteException;
    }

    private class C1450b implements ServiceConnection {
        final /* synthetic */ C1451c f2233a;
        private BlockingQueue<IBinder> f2234b;

        private C1450b(C1451c c1451c) {
            this.f2233a = c1451c;
            this.f2234b = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f2234b.put(iBinder);
            } catch (InterruptedException e) {
                Log.i("TEST", e.getMessage());
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f2234b.clear();
            this.f2234b = null;
        }

        public IBinder m2984a() throws InterruptedException {
            return (IBinder) this.f2234b.take();
        }
    }

    public static void m2986a(Context context) {
        f2235c = new C1451c();
        if (!f2235c.m2988b(context)) {
            f2235c.m2989c(context);
        }
    }

    public static String m2985a() {
        return f2235c.f2236a;
    }

    public static boolean m2987b() {
        return f2235c.f2237b;
    }

    private boolean m2988b(Context context) {
        try {
            if (!Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context}).equals(Integer.valueOf(0))) {
                return false;
            }
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            this.f2236a = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            this.f2237b = ((Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void m2989c(Context context) {
        Object obj = 1;
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
        } catch (Exception e) {
            obj = null;
        }
        if (obj != null) {
            ServiceConnection c1450b = new C1450b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (context.bindService(intent, c1450b, 1)) {
                    C1447a a = C1449a.m2983a(c1450b.m2984a());
                    this.f2236a = a.mo2326a();
                    this.f2237b = a.mo2327a(true);
                    try {
                        context.unbindService(c1450b);
                    } catch (Exception e2) {
                        Log.i("TEST", "SERVICE ERROR : " + e2.getMessage());
                    }
                }
            } catch (Exception e3) {
                try {
                    context.unbindService(c1450b);
                } catch (Exception e22) {
                    Log.i("TEST", "SERVICE ERROR : " + e22.getMessage());
                }
            } catch (Exception e222) {
                Log.i("TEST", "SERVICE ERROR : " + e222.getMessage());
            } catch (Throwable th) {
                try {
                    context.unbindService(c1450b);
                } catch (Exception e4) {
                    Log.i("TEST", "SERVICE ERROR : " + e4.getMessage());
                }
            }
        }
    }
}
