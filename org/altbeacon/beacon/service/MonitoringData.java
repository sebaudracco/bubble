package org.altbeacon.beacon.service;

import android.os.Bundle;
import org.altbeacon.beacon.Region;

public class MonitoringData {
    private static final String INSIDE_KEY = "inside";
    private static final String REGION_KEY = "region";
    private static final String TAG = "MonitoringData";
    private final boolean mInside;
    private final Region mRegion;

    public MonitoringData(boolean inside, Region region) {
        this.mInside = inside;
        this.mRegion = region;
    }

    public boolean isInside() {
        return this.mInside;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.mRegion);
        bundle.putBoolean(INSIDE_KEY, this.mInside);
        return bundle;
    }

    public static MonitoringData fromBundle(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        Region region = null;
        if (bundle.get("region") != null) {
            region = (Region) bundle.getSerializable("region");
        }
        return new MonitoringData(Boolean.valueOf(bundle.getBoolean(INSIDE_KEY)).booleanValue(), region);
    }
}
