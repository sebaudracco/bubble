package com.areametrics.areametricssdk;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

class C1351c {
    private static final String f1873b = ("AMS-" + C1351c.class.getSimpleName());
    boolean f1874a = false;
    private C1350a f1875c;
    private Location f1876d;
    private float f1877e = 100000.0f;
    private Handler f1878f = new Handler();
    private Context f1879g;
    private C1378g f1880h;
    private FusedLocationProviderClient f1881i;
    private LocationCallback f1882j;

    class C13441 implements OnSuccessListener<Location> {
        final /* synthetic */ C1351c f1861a;

        C13441(C1351c c1351c) {
            this.f1861a = c1351c;
        }

        public final /* synthetic */ void onSuccess(Object obj) {
            Location location = (Location) obj;
            if (location == null) {
                return;
            }
            if (this.f1861a.f1876d == null || location.getTime() > this.f1861a.f1876d.getTime()) {
                this.f1861a.f1876d = location;
            }
        }
    }

    class C13463 extends LocationCallback {
        final /* synthetic */ C1351c f1865a;

        C13463(C1351c c1351c) {
            this.f1865a = c1351c;
        }

        public final void onLocationResult(LocationResult locationResult) {
            C1351c.m2453b(this.f1865a, locationResult.getLastLocation());
        }
    }

    class C13474 implements OnFailureListener {
        final /* synthetic */ C1351c f1866a;

        C13474(C1351c c1351c) {
            this.f1866a = c1351c;
        }

        public final void onFailure(@NonNull Exception exception) {
            this.f1866a.f1874a = false;
            if (this.f1866a.f1875c != null) {
                this.f1866a.f1875c.mo2161a(C1349a.f1871d);
            }
        }
    }

    class C13485 implements Runnable {
        final /* synthetic */ C1351c f1867a;

        C13485(C1351c c1351c) {
            this.f1867a = c1351c;
        }

        public final void run() {
            C1351c.m2459e(this.f1867a);
        }
    }

    interface C1350a {

        public enum C1349a {
            ;
            
            public static final int f1868a = 0;
            public static final int f1869b = 0;
            public static final int f1870c = 0;
            public static final int f1871d = 0;

            static {
                f1868a = 1;
                f1869b = 2;
                f1870c = 3;
                f1871d = 4;
                f1872e = new int[]{f1868a, f1869b, f1870c, f1871d};
            }
        }

        void mo2161a(int i);

        void mo2162a(Location location);

        void mo2163a(Location location, int i);
    }

    C1351c(C1350a c1350a) {
        this.f1875c = c1350a;
    }

    static /* synthetic */ void m2450a(C1351c c1351c, LocationRequest locationRequest, long j) {
        LocationRequest b = c1351c.m2452b();
        if (c1351c.m2454c() == null || b == null || locationRequest == null || b.getPriority() != locationRequest.getPriority()) {
            c1351c.f1874a = false;
            if (c1351c.f1875c != null) {
                c1351c.f1875c.mo2161a(C1349a.f1869b);
                return;
            }
            return;
        }
        c1351c.f1882j = new C13463(c1351c);
        c1351c.m2460f().requestLocationUpdates(locationRequest, c1351c.f1882j, Looper.myLooper()).addOnFailureListener(new C13474(c1351c));
        if (j > 0 && c1351c.f1878f != null) {
            c1351c.f1878f.postDelayed(new C13485(c1351c), j);
        }
    }

    private LocationRequest m2452b() {
        String c = m2454c();
        LocationRequest create;
        if (c != null && c.equals("android.permission.ACCESS_FINE_LOCATION")) {
            create = LocationRequest.create();
            create.setInterval(2500);
            create.setFastestInterval(1000);
            create.setPriority(100);
            return create;
        } else if (c == null || !c.equals("android.permission.ACCESS_COARSE_LOCATION")) {
            return null;
        } else {
            create = LocationRequest.create();
            create.setInterval(2500);
            create.setFastestInterval(1000);
            create.setPriority(102);
            return create;
        }
    }

    static /* synthetic */ void m2453b(C1351c c1351c, Location location) {
        c1351c.f1876d = location;
        if (c1351c.f1876d != null && c1351c.f1876d.getAccuracy() <= c1351c.f1877e && c1351c.f1875c != null) {
            c1351c.f1875c.mo2162a(c1351c.f1876d);
        }
    }

    private String m2454c() {
        Object obj = 1;
        try {
            if (!AreaMetricsSDK.INSTANCE.isGooglePlayServicesAvailable(m2456d())) {
                return null;
            }
            Object obj2 = ContextCompat.checkSelfPermission(m2456d(), "android.permission.ACCESS_FINE_LOCATION") == 0 ? 1 : null;
            if (ContextCompat.checkSelfPermission(m2456d(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                obj = null;
            }
            return obj2 != null ? "android.permission.ACCESS_FINE_LOCATION" : obj != null ? "android.permission.ACCESS_COARSE_LOCATION" : null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    private Context m2456d() {
        return this.f1879g != null ? this.f1879g : AreaMetricsSDK.INSTANCE.getContext();
    }

    private C1378g m2458e() {
        return this.f1880h != null ? this.f1880h : AreaMetricsSDK.INSTANCE.getUserData();
    }

    static /* synthetic */ void m2459e(C1351c c1351c) {
        c1351c.m2461a();
        if (c1351c.f1875c != null) {
            c1351c.f1875c.mo2163a(c1351c.f1876d, C1349a.f1870c);
        }
    }

    private FusedLocationProviderClient m2460f() {
        return this.f1881i != null ? this.f1881i : AreaMetricsSDK.INSTANCE.getLocProviderClient();
    }

    final Location m2461a() {
        this.f1874a = false;
        if (this.f1878f != null) {
            this.f1878f.removeCallbacksAndMessages(null);
        }
        if (this.f1882j != null) {
            m2460f().removeLocationUpdates(this.f1882j);
        }
        return this.f1876d;
    }

    final void m2462a(float f) {
        final LocationRequest b = m2452b();
        if (b == null) {
            this.f1874a = false;
            if (m2458e() != null) {
                m2458e().m2607b("location", "denied");
            }
            if (this.f1875c != null) {
                this.f1875c.mo2161a(C1349a.f1869b);
                return;
            }
            return;
        }
        if (this.f1882j != null) {
            m2460f().removeLocationUpdates(this.f1882j);
        }
        this.f1874a = true;
        this.f1878f.removeCallbacksAndMessages(null);
        if (m2454c() != null) {
            m2460f().getLastLocation().addOnSuccessListener(new C13441(this));
        }
        if (f <= Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT) {
            f = Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT;
        }
        this.f1877e = f;
        Task checkLocationSettings = LocationServices.getSettingsClient(m2456d()).checkLocationSettings(new Builder().addLocationRequest(b).build());
        if (checkLocationSettings == null) {
            this.f1874a = false;
            if (this.f1875c != null) {
                this.f1875c.mo2161a(C1349a.f1868a);
                return;
            }
            return;
        }
        checkLocationSettings.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>(this) {
            final /* synthetic */ long f1863b = 8000;
            final /* synthetic */ C1351c f1864c;

            public final void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    task.getResult(ApiException.class);
                    this.f1864c.m2458e().m2607b("location", "authorized");
                    try {
                        C1351c.m2450a(this.f1864c, b, this.f1863b);
                    } catch (NullPointerException e) {
                        this.f1864c.f1874a = false;
                        if (this.f1864c.f1875c != null) {
                            this.f1864c.f1875c.mo2161a(C1349a.f1869b);
                        }
                    }
                } catch (ApiException e2) {
                    e2.getMessage();
                    this.f1864c.f1874a = false;
                    if (this.f1864c.f1875c != null) {
                        this.f1864c.f1875c.mo2161a(C1349a.f1868a);
                    }
                    switch (e2.getStatusCode()) {
                        case 6:
                            this.f1864c.m2458e().m2607b("location", "denied");
                            return;
                        case 17:
                            this.f1864c.m2458e().m2607b("location", "disabled_globally");
                            return;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE /*8502*/:
                            this.f1864c.m2458e().m2607b("location", "none");
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }
}
