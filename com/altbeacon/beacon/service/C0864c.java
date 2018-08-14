package com.altbeacon.beacon.service;

import com.altbeacon.beacon.Beacon;
import java.util.HashMap;

public class C0864c {
    private HashMap<String, HashMap<Integer, Beacon>> f1729a = new HashMap();
    private boolean f1730b = true;

    public C0864c(boolean z) {
        this.f1730b = z;
    }

    private void m1755a(Beacon beacon, HashMap<Integer, Beacon> hashMap) {
        HashMap hashMap2;
        if (hashMap == null) {
            hashMap2 = new HashMap();
        }
        hashMap2.put(Integer.valueOf(beacon.hashCode()), beacon);
        this.f1729a.put(m1757c(beacon), hashMap2);
    }

    private Beacon m1756b(Beacon beacon) {
        Beacon beacon2 = null;
        HashMap hashMap = (HashMap) this.f1729a.get(m1757c(beacon));
        if (hashMap != null) {
            for (Beacon beacon3 : hashMap.values()) {
                Beacon beacon32;
                if (beacon.m1555k()) {
                    beacon32.m1543a(beacon.m1551g());
                    beacon32.m1544a(beacon.m1548d());
                    beacon32 = beacon2;
                } else {
                    beacon.m1544a(beacon32.m1549e());
                    beacon32 = beacon;
                }
                beacon2 = beacon32;
            }
        }
        if (!beacon.m1555k()) {
            m1755a(beacon, hashMap);
        }
        return (beacon2 != null || beacon.m1555k()) ? beacon2 : beacon;
    }

    private String m1757c(Beacon beacon) {
        return this.f1730b ? beacon.m1553i() + beacon.m1545b() : beacon.m1553i();
    }

    public synchronized Beacon m1758a(Beacon beacon) {
        if (beacon.m1554j() || beacon.m1545b() != -1) {
            beacon = m1756b(beacon);
        }
        return beacon;
    }
}
