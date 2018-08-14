package com.altbeacon.beacon;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.beacon.service.C0865d;
import com.altbeacon.beacon.service.C0866e;
import com.altbeacon.beacon.service.C0869h;
import java.util.Collection;
import java.util.Set;

public class BeaconIntentProcessor extends IntentService {
    public BeaconIntentProcessor() {
        super("BeaconIntentProcessor");
    }

    protected void onHandleIntent(Intent intent) {
        C0865d c0865d;
        C0869h c0869h = null;
        C0835d.m1657a("BeaconIntentProcessor", "got an intent to process", new Object[0]);
        if (intent == null || intent.getExtras() == null) {
            c0865d = null;
        } else {
            C0865d a = intent.getExtras().getBundle("monitoringData") != null ? C0865d.m1759a(intent.getExtras().getBundle("monitoringData")) : null;
            if (intent.getExtras().getBundle("rangingData") != null) {
                c0869h = C0869h.m1790a(intent.getExtras().getBundle("rangingData"));
                c0865d = a;
            } else {
                c0865d = a;
            }
        }
        if (c0869h != null) {
            C0835d.m1657a("BeaconIntentProcessor", "got ranging data", new Object[0]);
            if (c0869h.m1791a() == null) {
                C0835d.m1662c("BeaconIntentProcessor", "Ranging data has a null beacons collection", new Object[0]);
            }
            Set<C0844f> h = C0829b.m1619a((Context) this).m1635h();
            Collection a2 = c0869h.m1791a();
            if (h != null) {
                for (C0844f a3 : h) {
                    a3.m1698a(a2, c0869h.m1792b());
                }
            } else {
                C0835d.m1657a("BeaconIntentProcessor", "but ranging notifier is null, so we're dropping it.", new Object[0]);
            }
            C0844f a32 = C0829b.m1619a((Context) this).m1636k();
            if (a32 != null) {
                a32.m1698a(a2, c0869h.m1792b());
            }
        }
        if (c0865d != null) {
            C0835d.m1657a("BeaconIntentProcessor", "got monitoring data", new Object[0]);
            Set<C0843e> g = C0829b.m1619a((Context) this).m1634g();
            if (g != null) {
                for (C0843e c0843e : g) {
                    C0835d.m1657a("BeaconIntentProcessor", "Calling monitoring notifier: %s", c0843e);
                    Region b = c0865d.m1761b();
                    Integer valueOf = Integer.valueOf(c0865d.m1760a() ? 1 : 0);
                    c0843e.m1695a(valueOf.intValue(), b);
                    C0866e.m1763a((Context) this).m1772a(b, valueOf);
                    if (c0865d.m1760a()) {
                        c0843e.m1696a(c0865d.m1761b());
                    } else {
                        c0843e.m1697b(c0865d.m1761b());
                    }
                }
            }
        }
    }
}
