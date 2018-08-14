package com.moat.analytics.mobile.vng;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class C3510o implements LocationListener {
    private static C3510o f8962a = null;
    private ScheduledExecutorService f8963b;
    private ScheduledFuture<?> f8964c;
    private ScheduledFuture<?> f8965d;
    private LocationManager f8966e;
    private boolean f8967f;
    private Location f8968g;
    private boolean f8969h;

    class C35081 implements Runnable {
        final /* synthetic */ C3510o f8960a;

        C35081(C3510o c3510o) {
            this.f8960a = c3510o;
        }

        public void run() {
            try {
                C3511p.m11976a(3, "LocationManager", (Object) this, "fetchTimedOut");
                this.f8960a.m11956a(true);
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    class C35092 implements Runnable {
        final /* synthetic */ C3510o f8961a;

        C35092(C3510o c3510o) {
            this.f8961a = c3510o;
        }

        public void run() {
            try {
                C3511p.m11976a(3, "LocationManager", (Object) this, "fetchTimerCompleted");
                this.f8961a.m11962e();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    private C3510o() {
        try {
            this.f8967f = ((C3500k) MoatAnalytics.getInstance()).f8942c;
            if (this.f8967f) {
                C3511p.m11976a(3, "LocationManager", (Object) this, "Moat location services disabled");
                return;
            }
            this.f8963b = Executors.newScheduledThreadPool(1);
            this.f8966e = (LocationManager) C3475a.m11847a().getSystemService("location");
            if (this.f8966e.getAllProviders().size() == 0) {
                C3511p.m11976a(3, "LocationManager", (Object) this, "Device has no location providers");
            } else {
                m11962e();
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    static C3510o m11953a() {
        if (f8962a == null) {
            f8962a = new C3510o();
        }
        return f8962a;
    }

    private void m11956a(boolean z) {
        try {
            C3511p.m11976a(3, "LocationManager", (Object) this, "stopping location fetch");
            m11965h();
            m11966i();
            if (z) {
                m11968k();
            } else {
                m11967j();
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private static boolean m11957a(Location location) {
        return location == null ? false : !(location.getLatitude() == 0.0d && location.getLongitude() == 0.0d) && location.getAccuracy() >= 0.0f && C3510o.m11960b(location) < 600.0f;
    }

    static boolean m11958a(Location location, Location location2) {
        return location == location2 ? true : (location == null || location2 == null || location.getTime() != location2.getTime()) ? false : true;
    }

    private static boolean m11959a(String str) {
        return ContextCompat.checkSelfPermission(C3475a.m11847a().getApplicationContext(), str) == 0;
    }

    private static float m11960b(Location location) {
        return (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
    }

    private static Location m11961b(Location location, Location location2) {
        boolean a = C3510o.m11957a(location);
        boolean a2 = C3510o.m11957a(location2);
        return !a ? !a2 ? null : location2 : (!a2 || location.getAccuracy() < location.getAccuracy()) ? location : location2;
    }

    private void m11962e() {
        try {
            if (!this.f8967f && this.f8966e != null) {
                if (this.f8969h) {
                    C3511p.m11976a(3, "LocationManager", (Object) this, "already updating location");
                }
                C3511p.m11976a(3, "LocationManager", (Object) this, "starting location fetch");
                Location b = m11972b();
                if (b != null) {
                    C3511p.m11976a(3, "LocationManager", (Object) this, "Have a valid location, won't fetch = " + b.toString());
                    m11968k();
                    return;
                }
                m11964g();
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private Location m11963f() {
        try {
            boolean l = m11969l();
            boolean m = m11970m();
            return (l && m) ? C3510o.m11961b(this.f8966e.getLastKnownLocation("gps"), this.f8966e.getLastKnownLocation("network")) : l ? this.f8966e.getLastKnownLocation("gps") : m ? this.f8966e.getLastKnownLocation("network") : null;
        } catch (Exception e) {
            C3502m.m11942a(e);
            return null;
        }
    }

    private void m11964g() {
        try {
            if (!this.f8969h) {
                C3511p.m11976a(3, "LocationManager", (Object) this, "Attempting to start update");
                if (m11969l()) {
                    C3511p.m11976a(3, "LocationManager", (Object) this, "start updating gps location");
                    this.f8966e.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.f8969h = true;
                }
                if (m11970m()) {
                    C3511p.m11976a(3, "LocationManager", (Object) this, "start updating network location");
                    this.f8966e.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.f8969h = true;
                }
                if (this.f8969h) {
                    m11966i();
                    this.f8965d = this.f8963b.schedule(new C35081(this), 60, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private void m11965h() {
        try {
            C3511p.m11976a(3, "LocationManager", (Object) this, "Stopping to update location");
            if (C3510o.m11971n() && this.f8966e != null) {
                this.f8966e.removeUpdates(this);
                this.f8969h = false;
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private void m11966i() {
        if (this.f8965d != null && !this.f8965d.isCancelled()) {
            this.f8965d.cancel(true);
            this.f8965d = null;
        }
    }

    private void m11967j() {
        if (this.f8964c != null && !this.f8964c.isCancelled()) {
            this.f8964c.cancel(true);
            this.f8964c = null;
        }
    }

    private void m11968k() {
        float f = 600.0f;
        C3511p.m11976a(3, "LocationManager", (Object) this, "Resetting fetch timer");
        m11967j();
        Location b = m11972b();
        if (b != null) {
            f = Math.max(600.0f - C3510o.m11960b(b), 0.0f);
        }
        this.f8964c = this.f8963b.schedule(new C35092(this), (long) f, TimeUnit.SECONDS);
    }

    private boolean m11969l() {
        return C3510o.m11959a("android.permission.ACCESS_FINE_LOCATION") && this.f8966e.getProvider("gps") != null && this.f8966e.isProviderEnabled("gps");
    }

    private boolean m11970m() {
        return C3510o.m11971n() && this.f8966e.getProvider("network") != null && this.f8966e.isProviderEnabled("network");
    }

    private static boolean m11971n() {
        return C3510o.m11959a("android.permission.ACCESS_FINE_LOCATION") || C3510o.m11959a("android.permission.ACCESS_COARSE_LOCATION");
    }

    @Nullable
    Location m11972b() {
        Location location = null;
        if (!(this.f8967f || this.f8966e == null)) {
            try {
                this.f8968g = C3510o.m11961b(this.f8968g, m11963f());
                location = this.f8968g;
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
        return location;
    }

    void m11973c() {
        m11962e();
    }

    void m11974d() {
        m11956a(false);
    }

    public void onLocationChanged(Location location) {
        try {
            C3511p.m11976a(3, "LocationManager", (Object) this, "Received an updated location = " + location.toString());
            float b = C3510o.m11960b(location);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && b < 600.0f) {
                this.f8968g = C3510o.m11961b(this.f8968g, location);
                C3511p.m11976a(3, "LocationManager", (Object) this, "fetchCompleted");
                m11956a(true);
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
