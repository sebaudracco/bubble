package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.altbeacon.a;
import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.Region;
import com.altbeacon.beacon.d.b;
import com.altbeacon.beacon.f;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.BeaconURL;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class C3787c extends C3784a implements f {
    private static final String[] f9088f = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private Handler f9089g = new Handler(Looper.getMainLooper());
    private ArrayList<BeaconURL> f9090h;
    private long f9091i;
    private a f9092j = new a(this.c);
    private Runnable f9093k = new C37861(this);

    class C37861 implements Runnable {
        final /* synthetic */ C3787c f9087a;

        C37861(C3787c c3787c) {
            this.f9087a = c3787c;
        }

        public void run() {
            try {
                C3833d.m12246a("BEACON_TAG", "Finished scanning for beacons, saving results");
                this.f9087a.m12096i();
                if (!this.f9087a.f9090h.isEmpty()) {
                    this.f9087a.m12084a(this.f9087a.m12083a((Object) this.f9087a.f9090h));
                }
                this.f9087a.d = true;
            } catch (Throwable th) {
                C3833d.m12248a("BEACON_TAG", "Failed to collect beacon data: ", th);
            }
        }
    }

    protected C3787c(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "beacon_data", "disableBeaconCollector", false, false);
    }

    private void m12096i() {
        try {
            this.f9092j.b();
        } catch (Throwable th) {
            C3833d.m12250b("BEACON_TAG", "Can't unbind from beacon service: ", th);
        }
    }

    public String mo6804a() {
        this.d = true;
        this.f9090h = new ArrayList();
        if (C3843e.m12285a(this.c, "android.permission.BLUETOOTH") && C3843e.m12285a(this.c, "android.permission.BLUETOOTH_ADMIN") && C3843e.m12285a(this.c, "android.permission.ACCESS_COARSE_LOCATION") && C3843e.m12285a(this.c, "android.permission.ACCESS_FINE_LOCATION")) {
            this.f9091i = System.currentTimeMillis();
            this.f9092j.a(this);
            this.d = false;
            this.f9089g.postDelayed(this.f9093k, 5000);
        } else {
            C3833d.m12246a("BEACON_TAG", "Don't have permissions to collect beacons");
        }
        return "";
    }

    public void m12098a(Collection<Beacon> collection, Region region) {
        try {
            for (Beacon beacon : collection) {
                if (beacon.b() == 65194 && beacon.h() == 16) {
                    int i;
                    String a = b.a(beacon.c().d());
                    C3833d.m12246a("BEACON_TAG", "Found beacon url: " + a);
                    Iterator it = this.f9090h.iterator();
                    while (it.hasNext()) {
                        BeaconURL beaconURL = (BeaconURL) it.next();
                        if (beaconURL.url.equals(a) && beaconURL.timestamp == this.f9091i) {
                            i = 1;
                            break;
                        }
                    }
                    i = 0;
                    if (i == 0) {
                        this.f9090h.add(new BeaconURL(a, this.f9091i));
                    }
                }
            }
        } catch (Throwable th) {
            C3833d.m12248a("BEACON_TAG", "Failed to collect beacon data: ", th);
        }
    }

    public String[] mo6805b() {
        return f9088f;
    }
}
