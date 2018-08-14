package com.bgjd.ici.p029d;

import android.content.Context;
import android.location.Location;
import com.bgjd.ici.p025b.C1408j.C1403a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class C1468o {
    private Object f2316a;
    private Object f2317b = null;
    private Location f2318c = null;
    private Object f2319d = null;
    private Class<?> f2320e = null;
    private Class<?> f2321f = null;
    private Class<?> f2322g = null;
    private boolean f2323h = false;
    private boolean f2324i = false;
    private boolean f2325j = false;
    private float f2326k = 0.0f;
    private float f2327l = 0.0f;

    private class C1465a implements InvocationHandler {
        final /* synthetic */ C1468o f2313a;

        private C1465a(C1468o c1468o) {
            this.f2313a = c1468o;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (method.getName().equals("onConnectionFailed")) {
                    this.f2313a.m3041h();
                }
                return Integer.valueOf(0);
            } catch (Exception e) {
                throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
            }
        }
    }

    private class C1466b implements InvocationHandler {
        final /* synthetic */ C1468o f2314a;

        private C1466b(C1468o c1468o) {
            this.f2314a = c1468o;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr == null) {
                return Integer.valueOf(0);
            }
            try {
                if (method.getName().equals("onConnected")) {
                    this.f2314a.m3039f();
                } else if (method.getName().equals("onConnectionSuspended")) {
                    this.f2314a.m3040g();
                }
                return null;
            } catch (Exception e) {
                throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
            }
        }
    }

    private class C1467c implements InvocationHandler {
        final /* synthetic */ C1468o f2315a;

        private C1467c(C1468o c1468o) {
            this.f2315a = c1468o;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (method.getName().equals("onLocationChanged")) {
                    this.f2315a.m3036a(objArr);
                }
                return Integer.valueOf(0);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public C1468o(float f, float f2) {
        this.f2326k = f;
        this.f2327l = f2;
    }

    public boolean m3043a() {
        return this.f2323h;
    }

    public boolean m3044b() {
        return this.f2324i;
    }

    public boolean m3045c() {
        return this.f2325j;
    }

    public Location m3046d() {
        return this.f2318c;
    }

    public void m3042a(Context context) {
        try {
            Class cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient$Builder");
            Class cls2 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks");
            Class cls3 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener");
            this.f2320e = Class.forName("com.google.android.gms.location.LocationServices");
            Object newInstance = cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
            Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new C1466b());
            cls.getMethod("addConnectionCallbacks", new Class[]{cls2}).invoke(newInstance, new Object[]{newProxyInstance});
            Object newProxyInstance2 = Proxy.newProxyInstance(cls3.getClassLoader(), new Class[]{cls3}, new C1465a());
            cls.getMethod("addOnConnectionFailedListener", new Class[]{cls3}).invoke(newInstance, new Object[]{newProxyInstance2});
            cls.getMethod("addApi", new Class[]{Class.forName("com.google.android.gms.common.api.Api")}).invoke(newInstance, new Object[]{this.f2320e.getField("API").get(null)});
            this.f2316a = cls.getMethod(C1403a.f2088r, new Class[0]).invoke(newInstance, new Object[0]);
            this.f2316a.getClass().getMethod("connect", new Class[0]).invoke(this.f2316a, new Object[0]);
        } catch (Exception e) {
            this.f2324i = true;
        }
    }

    public void m3047e() {
        try {
            if (((Boolean) this.f2316a.getClass().getMethod("isConnected", new Class[0]).invoke(this.f2316a, new Object[0])).booleanValue()) {
                if (!(this.f2319d == null || this.f2317b == null)) {
                    this.f2317b.getClass().getMethod("removeLocationUpdates", new Class[]{this.f2321f, this.f2322g}).invoke(this.f2317b, new Object[]{this.f2316a, this.f2319d});
                }
                this.f2316a.getClass().getMethod("disconnect", new Class[0]).invoke(this.f2316a, new Object[0]);
            }
        } catch (Exception e) {
        }
    }

    private void m3039f() {
        try {
            this.f2321f = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
            this.f2317b = this.f2320e.getDeclaredField("FusedLocationApi").get(null);
            this.f2318c = (Location) this.f2317b.getClass().getMethod("getLastLocation", new Class[]{this.f2321f}).invoke(this.f2317b, new Object[]{this.f2316a});
            this.f2323h = true;
            if (this.f2318c != null) {
                float longitude = (float) this.f2318c.getLongitude();
                if (this.f2326k == ((float) this.f2318c.getLatitude()) && this.f2327l == longitude) {
                    this.f2318c = null;
                    this.f2325j = false;
                }
            }
        } catch (Exception e) {
            this.f2324i = true;
        }
        if (this.f2318c != null) {
            this.f2325j = true;
            return;
        }
        try {
            Class cls = Class.forName("com.google.android.gms.location.LocationRequest");
            this.f2322g = Class.forName("com.google.android.gms.location.LocationListener");
            Object invoke = cls.getDeclaredMethod("create", new Class[0]).invoke(null, new Object[0]);
            int intValue = ((Integer) cls.getDeclaredField("PRIORITY_HIGH_ACCURACY").get(null)).intValue();
            Object invoke2 = cls.getMethod("setPriority", new Class[]{Integer.TYPE}).invoke(invoke, new Object[]{Integer.valueOf(intValue)});
            invoke2 = cls.getMethod("setInterval", new Class[]{Long.TYPE}).invoke(invoke2, new Object[]{Long.valueOf(1000)});
            this.f2319d = Proxy.newProxyInstance(this.f2322g.getClassLoader(), new Class[]{this.f2322g}, new C1467c());
            this.f2317b.getClass().getMethod("requestLocationUpdates", new Class[]{this.f2321f, cls, this.f2322g}).invoke(this.f2317b, new Object[]{this.f2316a, invoke2, this.f2319d});
        } catch (Exception e2) {
            this.f2324i = true;
        }
    }

    private void m3040g() {
        this.f2324i = true;
        this.f2323h = false;
    }

    private void m3041h() {
        this.f2324i = true;
        this.f2323h = false;
    }

    private void m3036a(Object[] objArr) {
        if (objArr != null) {
            this.f2318c = (Location) objArr[0];
            if (this.f2318c == null) {
                this.f2325j = false;
                this.f2324i = true;
                return;
            }
            this.f2324i = false;
            this.f2325j = true;
            return;
        }
        this.f2324i = true;
        this.f2325j = false;
    }
}
