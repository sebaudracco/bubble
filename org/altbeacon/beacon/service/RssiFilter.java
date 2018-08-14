package org.altbeacon.beacon.service;

public interface RssiFilter {
    void addMeasurement(Integer num);

    double calculateRssi();

    int getMeasurementCount();

    boolean noMeasurementsAvailable();
}
