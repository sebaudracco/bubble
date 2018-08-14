package org.altbeacon.beacon.service;

import android.os.SystemClock;
import java.io.Serializable;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;

public class RangedBeacon implements Serializable {
    public static final long DEFAULT_MAX_TRACKING_AGE = 5000;
    public static final long DEFAULT_SAMPLE_EXPIRATION_MILLISECONDS = 20000;
    private static final String TAG = "RangedBeacon";
    public static long maxTrackingAge = 5000;
    private static long sampleExpirationMilliseconds = 20000;
    protected long lastTrackedTimeMillis = 0;
    Beacon mBeacon;
    protected transient RssiFilter mFilter = null;
    private boolean mTracked = true;
    private int packetCount = 0;

    public RangedBeacon(Beacon beacon) {
        updateBeacon(beacon);
    }

    public void updateBeacon(Beacon beacon) {
        this.packetCount++;
        this.mBeacon = beacon;
        addMeasurement(Integer.valueOf(this.mBeacon.getRssi()));
    }

    public boolean isTracked() {
        return this.mTracked;
    }

    public void setTracked(boolean tracked) {
        this.mTracked = tracked;
    }

    public Beacon getBeacon() {
        return this.mBeacon;
    }

    public void commitMeasurements() {
        if (getFilter().noMeasurementsAvailable()) {
            LogManager.m16371d(TAG, "No measurements available to calculate running average", new Object[0]);
        } else {
            this.mBeacon.setRunningAverageRssi(getFilter().calculateRssi());
            this.mBeacon.setRssiMeasurementCount(getFilter().getMeasurementCount());
            LogManager.m16371d(TAG, "calculated new runningAverageRssi: %s", Double.valueOf(runningAverage));
        }
        this.mBeacon.setPacketCount(this.packetCount);
        this.packetCount = 0;
    }

    public void addMeasurement(Integer rssi) {
        if (rssi.intValue() != 127) {
            this.mTracked = true;
            this.lastTrackedTimeMillis = SystemClock.elapsedRealtime();
            getFilter().addMeasurement(rssi);
        }
    }

    public static void setSampleExpirationMilliseconds(long milliseconds) {
        sampleExpirationMilliseconds = milliseconds;
        RunningAverageRssiFilter.setSampleExpirationMilliseconds(sampleExpirationMilliseconds);
    }

    public static void setMaxTrackinAge(int maxTrackinAge) {
        maxTrackingAge = (long) maxTrackinAge;
    }

    public boolean noMeasurementsAvailable() {
        return getFilter().noMeasurementsAvailable();
    }

    public long getTrackingAge() {
        return SystemClock.elapsedRealtime() - this.lastTrackedTimeMillis;
    }

    public boolean isExpired() {
        return getTrackingAge() > maxTrackingAge;
    }

    private RssiFilter getFilter() {
        if (this.mFilter == null) {
            try {
                this.mFilter = (RssiFilter) BeaconManager.getRssiFilterImplClass().getConstructors()[0].newInstance(new Object[0]);
            } catch (Exception e) {
                LogManager.m16373e(TAG, "Could not construct RssiFilterImplClass %s", BeaconManager.getRssiFilterImplClass().getName());
            }
        }
        return this.mFilter;
    }
}
