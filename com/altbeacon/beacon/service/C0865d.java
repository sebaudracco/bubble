package com.altbeacon.beacon.service;

import android.os.Bundle;
import com.altbeacon.beacon.Region;

public class C0865d {
    private final boolean f1731a;
    private final Region f1732b;

    public C0865d(boolean z, Region region) {
        this.f1731a = z;
        this.f1732b = region;
    }

    public static C0865d m1759a(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        Region region = null;
        if (bundle.get("region") != null) {
            region = (Region) bundle.getSerializable("region");
        }
        return new C0865d(Boolean.valueOf(bundle.getBoolean("inside")).booleanValue(), region);
    }

    public boolean m1760a() {
        return this.f1731a;
    }

    public Region m1761b() {
        return this.f1732b;
    }

    public Bundle m1762c() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.f1732b);
        bundle.putBoolean("inside", this.f1731a);
        return bundle;
    }
}
