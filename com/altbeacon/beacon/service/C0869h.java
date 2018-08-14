package com.altbeacon.beacon.service;

import android.os.Bundle;
import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.Region;
import com.bgjd.ici.p025b.C1408j.C1403a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class C0869h {
    private final Collection<Beacon> f1748a;
    private final Region f1749b;

    public C0869h(Collection<Beacon> collection, Region region) {
        synchronized (collection) {
            this.f1748a = collection;
        }
        this.f1749b = region;
    }

    public static C0869h m1790a(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        return new C0869h(bundle.get(C1403a.f2090t) != null ? (Collection) bundle.getSerializable(C1403a.f2090t) : null, bundle.get("region") != null ? (Region) bundle.getSerializable("region") : null);
    }

    public Collection<Beacon> m1791a() {
        return this.f1748a;
    }

    public Region m1792b() {
        return this.f1749b;
    }

    public Bundle m1793c() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.f1749b);
        Serializable arrayList = new ArrayList();
        for (Beacon add : this.f1748a) {
            arrayList.add(add);
        }
        bundle.putSerializable(C1403a.f2090t, arrayList);
        return bundle;
    }
}
