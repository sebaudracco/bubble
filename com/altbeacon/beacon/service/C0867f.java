package com.altbeacon.beacon.service;

import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.p013c.C0835d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class C0867f {
    private static boolean f1739c = false;
    private final C0862a f1740a;
    private Map<Beacon, C0868g> f1741b = new HashMap();

    public C0867f(C0862a c0862a) {
        this.f1740a = c0862a;
    }

    public synchronized Collection<Beacon> m1779a() {
        Collection arrayList;
        Map hashMap = new HashMap();
        arrayList = new ArrayList();
        synchronized (this.f1741b) {
            for (Beacon beacon : this.f1741b.keySet()) {
                C0868g c0868g = (C0868g) this.f1741b.get(beacon);
                if (c0868g.m1784a()) {
                    c0868g.m1786c();
                    if (!c0868g.m1787d()) {
                        arrayList.add(c0868g.m1785b());
                    }
                }
                if ((!c0868g.m1787d() ? 1 : null) == 1) {
                    if (!f1739c || c0868g.m1789f()) {
                        c0868g.m1783a(false);
                    }
                    hashMap.put(beacon, c0868g);
                } else {
                    C0835d.m1657a("RangeState", "Dumping beacon from RangeState because it has no recent measurements.", new Object[0]);
                }
            }
            this.f1741b = hashMap;
        }
        return arrayList;
    }

    public void m1780a(Beacon beacon) {
        if (this.f1741b.containsKey(beacon)) {
            C0835d.m1657a("RangeState", "adding %s to existing range for: %s", beacon, (C0868g) this.f1741b.get(beacon));
            r0.m1781a(beacon);
            return;
        }
        C0835d.m1657a("RangeState", "adding %s to new rangedBeacon", beacon);
        this.f1741b.put(beacon, new C0868g(beacon));
    }
}
