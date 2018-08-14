package org.altbeacon.beacon.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.logging.LogManager;

public class RangeState implements Serializable {
    private static final String TAG = "RangeState";
    private static boolean sUseTrackingCache = false;
    private Callback mCallback;
    private Map<Beacon, RangedBeacon> mRangedBeacons = new HashMap();

    public RangeState(Callback c) {
        this.mCallback = c;
    }

    public Callback getCallback() {
        return this.mCallback;
    }

    public void addBeacon(Beacon beacon) {
        if (this.mRangedBeacons.containsKey(beacon)) {
            RangedBeacon rangedBeacon = (RangedBeacon) this.mRangedBeacons.get(beacon);
            if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m16371d(TAG, "adding %s to existing range for: %s", beacon, rangedBeacon);
            }
            rangedBeacon.updateBeacon(beacon);
            return;
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m16371d(TAG, "adding %s to new rangedBeacon", beacon);
        }
        this.mRangedBeacons.put(beacon, new RangedBeacon(beacon));
    }

    public synchronized Collection<Beacon> finalizeBeacons() {
        ArrayList<Beacon> finalizedBeacons;
        Map<Beacon, RangedBeacon> newRangedBeacons = new HashMap();
        finalizedBeacons = new ArrayList();
        synchronized (this.mRangedBeacons) {
            for (Beacon beacon : this.mRangedBeacons.keySet()) {
                Object obj;
                RangedBeacon rangedBeacon = (RangedBeacon) this.mRangedBeacons.get(beacon);
                if (rangedBeacon.isTracked()) {
                    rangedBeacon.commitMeasurements();
                    if (!rangedBeacon.noMeasurementsAvailable()) {
                        finalizedBeacons.add(rangedBeacon.getBeacon());
                    }
                }
                if (rangedBeacon.noMeasurementsAvailable()) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj == 1) {
                    if (!sUseTrackingCache || rangedBeacon.isExpired()) {
                        rangedBeacon.setTracked(false);
                    }
                    newRangedBeacons.put(beacon, rangedBeacon);
                } else {
                    LogManager.m16371d(TAG, "Dumping beacon from RangeState because it has no recent measurements.", new Object[0]);
                }
            }
            this.mRangedBeacons = newRangedBeacons;
        }
        return finalizedBeacons;
    }

    public static void setUseTrackingCache(boolean useTrackingCache) {
        sUseTrackingCache = useTrackingCache;
    }

    public static boolean getUseTrackingCache() {
        return sUseTrackingCache;
    }
}
