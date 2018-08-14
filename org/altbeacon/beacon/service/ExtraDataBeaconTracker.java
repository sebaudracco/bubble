package org.altbeacon.beacon.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.HashMap;
import org.altbeacon.beacon.Beacon;

public class ExtraDataBeaconTracker implements Serializable {
    private static final String TAG = "BeaconTracker";
    @NonNull
    private final HashMap<String, HashMap<Integer, Beacon>> mBeaconsByKey;
    private final boolean matchBeaconsByServiceUUID;

    public ExtraDataBeaconTracker() {
        this(true);
    }

    public ExtraDataBeaconTracker(boolean matchBeaconsByServiceUUID) {
        this.mBeaconsByKey = new HashMap();
        this.matchBeaconsByServiceUUID = matchBeaconsByServiceUUID;
    }

    @Nullable
    public synchronized Beacon track(@NonNull Beacon beacon) {
        Beacon trackedBeacon;
        if (beacon.isMultiFrameBeacon() || beacon.getServiceUuid() != -1) {
            trackedBeacon = trackGattBeacon(beacon);
        } else {
            trackedBeacon = beacon;
        }
        return trackedBeacon;
    }

    @Nullable
    private Beacon trackGattBeacon(@NonNull Beacon beacon) {
        if (beacon.isExtraBeaconData()) {
            updateTrackedBeacons(beacon);
            return null;
        }
        String key = getBeaconKey(beacon);
        HashMap<Integer, Beacon> matchingTrackedBeacons = (HashMap) this.mBeaconsByKey.get(key);
        if (matchingTrackedBeacons == null) {
            matchingTrackedBeacons = new HashMap();
        } else {
            beacon.setExtraDataFields(((Beacon) matchingTrackedBeacons.values().iterator().next()).getExtraDataFields());
        }
        matchingTrackedBeacons.put(Integer.valueOf(beacon.hashCode()), beacon);
        this.mBeaconsByKey.put(key, matchingTrackedBeacons);
        return beacon;
    }

    private void updateTrackedBeacons(@NonNull Beacon beacon) {
        HashMap<Integer, Beacon> matchingTrackedBeacons = (HashMap) this.mBeaconsByKey.get(getBeaconKey(beacon));
        if (matchingTrackedBeacons != null) {
            for (Beacon matchingTrackedBeacon : matchingTrackedBeacons.values()) {
                matchingTrackedBeacon.setRssi(beacon.getRssi());
                matchingTrackedBeacon.setExtraDataFields(beacon.getDataFields());
            }
        }
    }

    private String getBeaconKey(@NonNull Beacon beacon) {
        if (this.matchBeaconsByServiceUUID) {
            return beacon.getBluetoothAddress() + beacon.getServiceUuid();
        }
        return beacon.getBluetoothAddress();
    }
}
