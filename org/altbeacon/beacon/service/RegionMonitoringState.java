package org.altbeacon.beacon.service;

import android.os.SystemClock;
import java.io.Serializable;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;

public class RegionMonitoringState implements Serializable {
    private static final String TAG = RegionMonitoringState.class.getSimpleName();
    private final Callback callback;
    private boolean inside = false;
    private long lastSeenTime = 0;

    public RegionMonitoringState(Callback c) {
        this.callback = c;
    }

    public Callback getCallback() {
        return this.callback;
    }

    public boolean markInside() {
        this.lastSeenTime = SystemClock.elapsedRealtime();
        if (this.inside) {
            return false;
        }
        this.inside = true;
        return true;
    }

    public void markOutside() {
        this.inside = false;
        this.lastSeenTime = 0;
    }

    public boolean markOutsideIfExpired() {
        if (!this.inside || this.lastSeenTime <= 0 || SystemClock.elapsedRealtime() - this.lastSeenTime <= BeaconManager.getRegionExitPeriod()) {
            return false;
        }
        LogManager.m16371d(TAG, "We are newly outside the region because the lastSeenTime of %s was %s seconds ago, and that is over the expiration duration of %s", Long.valueOf(this.lastSeenTime), Long.valueOf(SystemClock.elapsedRealtime() - this.lastSeenTime), Long.valueOf(BeaconManager.getRegionExitPeriod()));
        markOutside();
        return true;
    }

    public boolean getInside() {
        return this.inside;
    }
}
