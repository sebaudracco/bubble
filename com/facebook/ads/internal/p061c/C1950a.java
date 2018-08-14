package com.facebook.ads.internal.p061c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.facebook.ads.internal.p056q.p057a.C2108b;
import com.facebook.ads.internal.p061c.C1953c.C1952a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class C1950a {
    public static final String f4521a = C1950a.class.getSimpleName();
    private final String f4522b;
    private final boolean f4523c;
    private final C1949c f4524d;

    private static final class C1947a implements IInterface {
        private IBinder f4512a;

        C1947a(IBinder iBinder) {
            this.f4512a = iBinder;
        }

        public String m6147a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f4512a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.f4512a;
        }

        public boolean m6148b() {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f4512a.transact(2, obtain, obtain2, 0);
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

    private static final class C1948b implements ServiceConnection {
        private AtomicBoolean f4513a;
        private final BlockingQueue<IBinder> f4514b;

        private C1948b() {
            this.f4513a = new AtomicBoolean(false);
            this.f4514b = new LinkedBlockingDeque();
        }

        public IBinder m6149a() {
            if (!this.f4513a.compareAndSet(true, true)) {
                return (IBinder) this.f4514b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f4514b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public enum C1949c {
        SHARED_PREFS,
        FB4A,
        DIRECT,
        REFLECTION,
        SERVICE
    }

    private C1950a(String str, boolean z, C1949c c1949c) {
        this.f4522b = str;
        this.f4523c = z;
        this.f4524d = c1949c;
    }

    private static C1950a m6150a(Context context) {
        try {
            AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new C1950a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), C1949c.DIRECT);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static C1950a m6151a(Context context, C1952a c1952a) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        } else if (C2108b.m6766a() && C2108b.m6767b("idfa_override")) {
            return new C1950a(C2108b.m6765a("idfa_override"), false, C1949c.DIRECT);
        } else {
            if (c1952a != null && !TextUtils.isEmpty(c1952a.f4530b)) {
                return new C1950a(c1952a.f4530b, c1952a.f4531c, C1949c.FB4A);
            }
            C1950a a = C1950a.m6150a(context);
            if (a == null || TextUtils.isEmpty(a.m6154a())) {
                a = C1950a.m6152b(context);
            }
            return (a == null || TextUtils.isEmpty(a.m6154a())) ? C1950a.m6153c(context) : a;
        }
    }

    private static C1950a m6152b(Context context) {
        Method a = C1954d.m6161a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (a == null) {
            return null;
        }
        Object a2 = C1954d.m6159a(null, a, context);
        if (a2 == null || ((Integer) a2).intValue() != 0) {
            return null;
        }
        a = C1954d.m6161a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
        if (a == null) {
            return null;
        }
        Object a3 = C1954d.m6159a(null, a, context);
        if (a3 == null) {
            return null;
        }
        a = C1954d.m6160a(a3.getClass(), "getId", new Class[0]);
        Method a4 = C1954d.m6160a(a3.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]);
        return (a == null || a4 == null) ? null : new C1950a((String) C1954d.m6159a(a3, a, new Object[0]), ((Boolean) C1954d.m6159a(a3, a4, new Object[0])).booleanValue(), C1949c.REFLECTION);
    }

    private static C1950a m6153c(Context context) {
        ServiceConnection c1948b = new C1948b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, c1948b, 1)) {
            C1950a c1950a;
            try {
                C1947a c1947a = new C1947a(c1948b.m6149a());
                c1950a = new C1950a(c1947a.m6147a(), c1947a.m6148b(), C1949c.SERVICE);
                return c1950a;
            } catch (Exception e) {
                c1950a = e;
            } finally {
                context.unbindService(c1948b);
            }
        }
        return null;
    }

    public String m6154a() {
        return this.f4522b;
    }

    public boolean m6155b() {
        return this.f4523c;
    }

    public C1949c m6156c() {
        return this.f4524d;
    }
}
