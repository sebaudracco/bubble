package org.altbeacon.beacon.service;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

public class RangingData {
    private static final String BEACONS_KEY = "beacons";
    private static final String REGION_KEY = "region";
    private static final String TAG = "RangingData";
    private final Collection<Beacon> mBeacons;
    private final Region mRegion;

    public RangingData(Collection<Beacon> beacons, Region region) {
        synchronized (beacons) {
            this.mBeacons = beacons;
        }
        this.mRegion = region;
    }

    public Collection<Beacon> getBeacons() {
        return this.mBeacons;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.mRegion);
        ArrayList<Serializable> serializableBeacons = new ArrayList();
        for (Beacon beacon : this.mBeacons) {
            serializableBeacons.add(beacon);
        }
        bundle.putSerializable("beacons", serializableBeacons);
        return bundle;
    }

    public static RangingData fromBundle(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        Region region = null;
        Collection<Beacon> beacons = null;
        if (bundle.get("beacons") != null) {
            beacons = (Collection) bundle.getSerializable("beacons");
        }
        if (bundle.get("region") != null) {
            region = (Region) bundle.getSerializable("region");
        }
        return new RangingData(beacons, region);
    }
}
