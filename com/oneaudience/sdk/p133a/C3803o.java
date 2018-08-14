package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.oneaudience.sdk.model.LocationData;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.List;

public class C3803o extends C3784a implements LocationListener {
    private static final String[] f9127f = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private Location f9128g;
    private LocationManager f9129h = ((LocationManager) this.c.getSystemService("location"));
    private Handler f9130i = new Handler(Looper.getMainLooper());
    private List<String> f9131j;
    private Runnable f9132k = new C38021(this);

    class C38021 implements Runnable {
        final /* synthetic */ C3803o f9126a;

        C38021(C3803o c3803o) {
            this.f9126a = c3803o;
        }

        public void run() {
            try {
                this.f9126a.d = true;
                this.f9126a.m12162j();
                if (this.f9126a.f9128g != null) {
                    this.f9126a.m12084a(this.f9126a.m12083a((Object) new LocationData(this.f9126a.f9128g.getLatitude(), this.f9126a.f9128g.getLongitude(), this.f9126a.f9128g.getAccuracy(), this.f9126a.f9128g.getProvider(), this.f9126a.f9128g.getTime())));
                    return;
                }
                C3833d.m12246a(C3784a.f9072a, "Can't get current location. Trying to get last known location instead");
                this.f9126a.m12155a(this.f9126a.f9129h, this.f9126a.f9131j);
                if (this.f9126a.f9128g != null) {
                    this.f9126a.m12084a(this.f9126a.m12083a((Object) new LocationData(this.f9126a.f9128g.getLatitude(), this.f9126a.f9128g.getLongitude(), this.f9126a.f9128g.getAccuracy(), this.f9126a.f9128g.getProvider(), this.f9126a.f9128g.getTime())));
                }
            } catch (Throwable th) {
                C3833d.m12248a(C3784a.f9072a, "Failed to collect location data: ", th);
            }
        }
    }

    protected C3803o(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "location_data", "disableLocationCollector", false, false);
    }

    private void m12155a(LocationManager locationManager, List<String> list) {
        if (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            for (String str : list) {
                Location lastKnownLocation;
                try {
                    lastKnownLocation = locationManager.getLastKnownLocation(str);
                } catch (Exception e) {
                    C3833d.m12246a(a, "Can't get location from provider: " + str);
                    lastKnownLocation = null;
                }
                if (lastKnownLocation != null && (this.f9128g == null || this.f9128g.getTime() < lastKnownLocation.getTime())) {
                    this.f9128g = lastKnownLocation;
                }
            }
        }
    }

    private LocationData m12161i() {
        if (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.f9128g = null;
            this.f9131j = this.f9129h.getProviders(true);
            if (!this.e || this.f9131j.isEmpty()) {
                m12155a(this.f9129h, this.f9131j);
                return this.f9128g != null ? new LocationData(this.f9128g.getLatitude(), this.f9128g.getLongitude(), this.f9128g.getAccuracy(), this.f9128g.getProvider(), this.f9128g.getTime()) : null;
            } else {
                this.d = false;
                for (String str : this.f9131j) {
                    C3833d.m12246a(a, "Scan location from provider: " + str);
                    this.f9129h.requestSingleUpdate(str, this, Looper.getMainLooper());
                }
                this.f9130i.postDelayed(this.f9132k, 60000);
                return null;
            }
        }
        C3833d.m12246a(a, "Don't have permissions to collect location");
        return null;
    }

    private void m12162j() {
        this.f9129h.removeUpdates(this);
    }

    public String mo6804a() {
        LocationData i = m12161i();
        return i != null ? m12083a((Object) i) : "";
    }

    public String[] mo6805b() {
        return f9127f;
    }

    public void onLocationChanged(Location location) {
        try {
            C3833d.m12246a(a, "Got location from provider: " + location.getProvider());
            if (this.f9128g == null || this.f9128g.getAccuracy() < location.getAccuracy()) {
                this.f9128g = location;
            }
        } catch (Throwable th) {
            C3833d.m12248a(a, "Failed to collect location data: ", th);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
