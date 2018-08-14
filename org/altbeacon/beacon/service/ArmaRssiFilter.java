package org.altbeacon.beacon.service;

import org.altbeacon.beacon.logging.LogManager;

public class ArmaRssiFilter implements RssiFilter {
    private static double DEFAULT_ARMA_SPEED = 0.1d;
    private static final String TAG = "ArmaRssiFilter";
    private int armaMeasurement;
    private double armaSpeed;
    private boolean isInitialized;

    public ArmaRssiFilter() {
        this.armaSpeed = 0.1d;
        this.isInitialized = false;
        this.armaSpeed = DEFAULT_ARMA_SPEED;
    }

    public void addMeasurement(Integer rssi) {
        LogManager.m16371d(TAG, "adding rssi: %s", rssi);
        if (!this.isInitialized) {
            this.armaMeasurement = rssi.intValue();
            this.isInitialized = true;
        }
        this.armaMeasurement = Double.valueOf(((double) this.armaMeasurement) - (this.armaSpeed * ((double) (this.armaMeasurement - rssi.intValue())))).intValue();
        LogManager.m16371d(TAG, "armaMeasurement: %s", Integer.valueOf(this.armaMeasurement));
    }

    public int getMeasurementCount() {
        return 0;
    }

    public boolean noMeasurementsAvailable() {
        return false;
    }

    public double calculateRssi() {
        return (double) this.armaMeasurement;
    }

    public static void setDEFAULT_ARMA_SPEED(double default_arma_speed) {
        DEFAULT_ARMA_SPEED = default_arma_speed;
    }
}
