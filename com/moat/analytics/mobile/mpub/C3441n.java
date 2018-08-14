package com.moat.analytics.mobile.mpub;

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

final class C3441n implements LocationListener {
    private static C3441n f8760 = null;
    private Location f8761;
    private ScheduledExecutorService f8762;
    private boolean f8763;
    private ScheduledFuture<?> f8764;
    private ScheduledFuture<?> f8765;
    private LocationManager f8766;
    private boolean f8767;

    class C34391 implements Runnable {
        private /* synthetic */ C3441n f8758;

        C34391(C3441n c3441n) {
            this.f8758 = c3441n;
        }

        public final void run() {
            try {
                C3412a.m11642(3, "LocationManager", this, "fetchTimedOut");
                this.f8758.m11747(true);
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    class C34402 implements Runnable {
        private /* synthetic */ C3441n f8759;

        C34402(C3441n c3441n) {
            this.f8759 = c3441n;
        }

        public final void run() {
            try {
                C3412a.m11642(3, "LocationManager", this, "fetchTimerCompleted");
                this.f8759.m11740();
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    static C3441n m11742() {
        if (f8760 == null) {
            f8760 = new C3441n();
        }
        return f8760;
    }

    private C3441n() {
        try {
            this.f8763 = ((C3419f) MoatAnalytics.getInstance()).f8685;
            if (this.f8763) {
                C3412a.m11642(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.f8762 = Executors.newScheduledThreadPool(1);
            this.f8766 = (LocationManager) C3416c.m11664().getSystemService("location");
            if (this.f8766.getAllProviders().size() == 0) {
                C3412a.m11642(3, "LocationManager", this, "Device has no location providers");
            } else {
                m11740();
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    @Nullable
    final Location m11751() {
        if (this.f8763 || this.f8766 == null) {
            return null;
        }
        return this.f8761;
    }

    final void m11752() {
        m11740();
    }

    final void m11753() {
        m11747(false);
    }

    public final void onLocationChanged(Location location) {
        try {
            C3412a.m11642(3, "LocationManager", this, "Received an updated location = " + location.toString());
            float currentTimeMillis = (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && currentTimeMillis < 600.0f) {
                this.f8761 = C3441n.m11745(this.f8761, location);
                C3412a.m11642(3, "LocationManager", this, "fetchCompleted");
                m11747(true);
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onProviderDisabled(String str) {
    }

    private void m11740() {
        try {
            if (!this.f8763 && this.f8766 != null) {
                if (this.f8767) {
                    C3412a.m11642(3, "LocationManager", this, "already updating location");
                }
                C3412a.m11642(3, "LocationManager", this, "starting location fetch");
                this.f8761 = C3441n.m11745(this.f8761, m11737());
                if (this.f8761 != null) {
                    C3412a.m11642(3, "LocationManager", this, "Have a valid location, won't fetch = " + this.f8761.toString());
                    m11743();
                    return;
                }
                m11735();
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    private void m11747(boolean z) {
        try {
            C3412a.m11642(3, "LocationManager", this, "stopping location fetch");
            m11736();
            m11739();
            if (z) {
                m11743();
            } else {
                m11750();
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    private Location m11737() {
        try {
            boolean ͺ = m11744();
            boolean ॱˊ = m11749();
            if (ͺ && ॱˊ) {
                return C3441n.m11745(this.f8766.getLastKnownLocation("gps"), this.f8766.getLastKnownLocation("network"));
            }
            if (ͺ) {
                return this.f8766.getLastKnownLocation("gps");
            }
            if (ॱˊ) {
                return this.f8766.getLastKnownLocation("network");
            }
            return null;
        } catch (Exception e) {
            C3442o.m11756(e);
            return null;
        }
    }

    private void m11735() {
        try {
            if (!this.f8767) {
                C3412a.m11642(3, "LocationManager", this, "Attempting to start update");
                if (m11744()) {
                    C3412a.m11642(3, "LocationManager", this, "start updating gps location");
                    this.f8766.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.f8767 = true;
                }
                if (m11749()) {
                    C3412a.m11642(3, "LocationManager", this, "start updating network location");
                    this.f8766.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.f8767 = true;
                }
                if (this.f8767) {
                    m11739();
                    this.f8765 = this.f8762.schedule(new C34391(this), 60, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m11736() {
        /*
        r5 = this;
        r1 = 1;
        r0 = 0;
        r2 = 3;
        r3 = "LocationManager";
        r4 = "Stopping to update location";
        com.moat.analytics.mobile.mpub.C3412a.m11642(r2, r3, r5, r4);	 Catch:{ SecurityException -> 0x0048 }
        r2 = "android.permission.ACCESS_FINE_LOCATION";
        r3 = com.moat.analytics.mobile.mpub.C3416c.m11664();	 Catch:{ SecurityException -> 0x0048 }
        r3 = r3.getApplicationContext();	 Catch:{ SecurityException -> 0x0048 }
        r2 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r2);	 Catch:{ SecurityException -> 0x0048 }
        if (r2 != 0) goto L_0x0044;
    L_0x001d:
        r2 = r1;
    L_0x001e:
        if (r2 != 0) goto L_0x0034;
    L_0x0020:
        r2 = "android.permission.ACCESS_COARSE_LOCATION";
        r3 = com.moat.analytics.mobile.mpub.C3416c.m11664();	 Catch:{ SecurityException -> 0x0048 }
        r3 = r3.getApplicationContext();	 Catch:{ SecurityException -> 0x0048 }
        r2 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r2);	 Catch:{ SecurityException -> 0x0048 }
        if (r2 != 0) goto L_0x0046;
    L_0x0031:
        r2 = r1;
    L_0x0032:
        if (r2 == 0) goto L_0x0035;
    L_0x0034:
        r0 = r1;
    L_0x0035:
        if (r0 == 0) goto L_0x0043;
    L_0x0037:
        r0 = r5.f8766;	 Catch:{ SecurityException -> 0x0048 }
        if (r0 == 0) goto L_0x0043;
    L_0x003b:
        r0 = r5.f8766;	 Catch:{ SecurityException -> 0x0048 }
        r0.removeUpdates(r5);	 Catch:{ SecurityException -> 0x0048 }
        r0 = 0;
        r5.f8767 = r0;	 Catch:{ SecurityException -> 0x0048 }
    L_0x0043:
        return;
    L_0x0044:
        r2 = r0;
        goto L_0x001e;
    L_0x0046:
        r2 = r0;
        goto L_0x0032;
    L_0x0048:
        r0 = move-exception;
        com.moat.analytics.mobile.mpub.C3442o.m11756(r0);
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.n.ʼ():void");
    }

    private void m11739() {
        if (this.f8765 != null && !this.f8765.isCancelled()) {
            this.f8765.cancel(true);
            this.f8765 = null;
        }
    }

    private void m11750() {
        if (this.f8764 != null && !this.f8764.isCancelled()) {
            this.f8764.cancel(true);
            this.f8764 = null;
        }
    }

    private void m11743() {
        float f = 600.0f;
        C3412a.m11642(3, "LocationManager", this, "Resetting fetch timer");
        m11750();
        if (this.f8761 != null) {
            f = Math.max(600.0f - ((float) ((System.currentTimeMillis() - this.f8761.getTime()) / 1000)), 0.0f);
        }
        this.f8764 = this.f8762.schedule(new C34402(this), (long) f, TimeUnit.SECONDS);
    }

    private boolean m11744() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C3416c.m11664().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.f8766.getProvider("gps") != null && this.f8766.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    private static Location m11745(Location location, Location location2) {
        boolean ॱ = C3441n.m11748(location);
        boolean ॱ2 = C3441n.m11748(location2);
        if (ॱ) {
            if (ॱ2) {
                return location.getAccuracy() < location.getAccuracy() ? location : location2;
            } else {
                return location;
            }
        } else if (ॱ2) {
            return location2;
        } else {
            return null;
        }
    }

    private static boolean m11748(Location location) {
        if (location == null) {
            return false;
        }
        if ((location.getLatitude() != 0.0d || location.getLongitude() != 0.0d) && location.getAccuracy() >= 0.0f && ((float) ((System.currentTimeMillis() - location.getTime()) / 1000)) < 600.0f) {
            return true;
        }
        return false;
    }

    static boolean m11741(Location location, Location location2) {
        if (location == location2) {
            return true;
        }
        if (location == null || location2 == null || location.getTime() != location2.getTime()) {
            return false;
        }
        return true;
    }

    private boolean m11749() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C3416c.m11664().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (ContextCompat.checkSelfPermission(C3416c.m11664().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                z = false;
                if (z || this.f8766.getProvider("network") == null || !this.f8766.isProviderEnabled("network")) {
                    return false;
                }
                return true;
            }
        }
        z = true;
        if (z) {
        }
        return false;
    }
}
