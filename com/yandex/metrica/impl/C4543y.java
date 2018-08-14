package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import com.yandex.metrica.impl.C4364d.C4370a;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public final class C4543y implements C4364d {
    static final long f12599a = TimeUnit.SECONDS.toMillis(300);
    static final long f12600b = TimeUnit.SECONDS.toMillis(120);
    static final Set<String> f12601c = new HashSet(Arrays.asList(new String[]{"gps"}));
    private static volatile C4543y f12602i;
    private static final Object f12603j = new Object();
    private final Context f12604d;
    private final HandlerThread f12605e;
    private final LocationManager f12606f;
    private final WeakHashMap<Object, Object> f12607g;
    private boolean f12608h;
    private C4370a<Location> f12609k = new C4370a();
    private boolean f12610l = false;
    private cc f12611m;
    private LocationListener f12612n = new C45421(this);

    class C45421 implements LocationListener {
        final /* synthetic */ C4543y f12598a;

        C45421(C4543y c4543y) {
            this.f12598a = c4543y;
        }

        public void onLocationChanged(Location location) {
            this.f12598a.m16310a(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    }

    private C4543y(Context context) {
        this.f12604d = context;
        this.f12607g = new WeakHashMap();
        this.f12608h = false;
        this.f12605e = new HandlerThread("LHandlerThread");
        this.f12605e.start();
        this.f12606f = (LocationManager) context.getSystemService("location");
        this.f12611m = new cc(bp.m15358a(this.f12604d).m15364b());
        this.f12610l = this.f12611m.m15501c();
    }

    public static C4543y m16305a(Context context) {
        if (f12602i == null) {
            synchronized (f12603j) {
                if (f12602i == null) {
                    f12602i = new C4543y(context.getApplicationContext());
                }
            }
        }
        return f12602i;
    }

    public synchronized void m16311a(Object obj) {
        if (this.f12610l && al.m14592a(this.f12604d, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.f12607g.put(obj, null);
            if (!this.f12608h) {
                this.f12608h = true;
                m16306a("network", 0.0f, f12599a, this.f12612n, this.f12605e.getLooper());
                if (al.m14592a(this.f12604d, "android.permission.ACCESS_FINE_LOCATION")) {
                    m16306a("passive", 0.0f, f12599a, this.f12612n, this.f12605e.getLooper());
                }
            }
        }
    }

    private void m16306a(String str, float f, long j, LocationListener locationListener, Looper looper) {
        try {
            if (this.f12606f != null) {
                this.f12606f.requestLocationUpdates(str, j, f, locationListener, looper);
            }
        } catch (Exception e) {
        }
    }

    public synchronized void m16314b(Object obj) {
        this.f12607g.remove(obj);
        m16313b();
    }

    synchronized void m16309a() {
        this.f12607g.clear();
        m16313b();
    }

    void m16313b() {
        if (this.f12608h && this.f12607g.isEmpty()) {
            this.f12608h = false;
            try {
                if (this.f12606f != null) {
                    this.f12606f.removeUpdates(this.f12612n);
                }
            } catch (Exception e) {
            }
        }
    }

    public synchronized void m16310a(Location location) {
        if (this.f12609k.m15046c() || C4543y.m16307a(location, (Location) this.f12609k.m15043a())) {
            this.f12609k.m15044a(location == null ? null : new Location(location));
        }
    }

    synchronized Location m16315c() {
        return (Location) this.f12609k.m15047d();
    }

    public Location m16316d() {
        if (this.f12606f == null) {
            return null;
        }
        List<String> allProviders = this.f12606f.getAllProviders();
        if (allProviders == null) {
            return null;
        }
        boolean a = al.m14592a(this.f12604d, "android.permission.ACCESS_COARSE_LOCATION");
        boolean a2 = al.m14592a(this.f12604d, "android.permission.ACCESS_FINE_LOCATION");
        Location location = null;
        for (String str : allProviders) {
            Location lastKnownLocation;
            if (!f12601c.contains(str)) {
                if (a) {
                    try {
                        if (!"passive".equals(str) || a2) {
                            lastKnownLocation = this.f12606f.getLastKnownLocation(str);
                            if (lastKnownLocation != null && C4543y.m16307a(lastKnownLocation, location)) {
                                location = lastKnownLocation;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                lastKnownLocation = null;
                location = lastKnownLocation;
            }
            lastKnownLocation = location;
            location = lastKnownLocation;
        }
        return location;
    }

    static boolean m16307a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        if (location == null) {
            return false;
        }
        boolean z;
        boolean z2;
        long time = location.getTime() - location2.getTime();
        boolean z3 = time > f12600b;
        if (time < (-f12600b)) {
            z = true;
        } else {
            z = false;
        }
        if (time > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z3) {
            return true;
        }
        if (z) {
            return false;
        }
        boolean z4;
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z5 = accuracy > 0;
        if (accuracy < 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (((long) accuracy) > 200) {
            z3 = true;
        } else {
            z3 = false;
        }
        String provider = location.getProvider();
        String provider2 = location2.getProvider();
        z = provider == null ? provider2 == null : provider.equals(provider2);
        if (z4) {
            return true;
        }
        if (z2 && !z5) {
            return true;
        }
        if (z2 && !r0 && r3) {
            return true;
        }
        return false;
    }

    public static byte[] m16308b(Location location) {
        Parcel obtain = Parcel.obtain();
        byte[] bArr = new byte[0];
        try {
            obtain.writeValue(location);
            bArr = obtain.marshall();
        } catch (Exception e) {
        } finally {
            obtain.recycle();
        }
        return bArr;
    }

    public static Location m16304a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        Location location;
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            location = (Location) obtain.readValue(Location.class.getClassLoader());
            return location;
        } catch (Exception e) {
            location = e;
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public void m16312a(Object obj, boolean z, boolean z2) {
        if (this.f12610l == z2) {
            return;
        }
        if (z) {
            this.f12610l = z2;
            this.f12611m.m15496a(this.f12610l);
            if (this.f12610l) {
                m16311a(obj);
            } else {
                m16309a();
            }
        } else if (!z2) {
            m16314b(obj);
        }
    }
}
