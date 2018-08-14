package org.altbeacon.beacon.service;

import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.altbeacon.beacon.logging.LogManager;

public class RunningAverageRssiFilter implements RssiFilter {
    public static final long DEFAULT_SAMPLE_EXPIRATION_MILLISECONDS = 20000;
    private static final String TAG = "RunningAverageRssiFilter";
    private static long sampleExpirationMilliseconds = 20000;
    private ArrayList<Measurement> mMeasurements = new ArrayList();

    private class Measurement implements Comparable<Measurement> {
        Integer rssi;
        long timestamp;

        private Measurement() {
        }

        public int compareTo(Measurement arg0) {
            return this.rssi.compareTo(arg0.rssi);
        }
    }

    public void addMeasurement(Integer rssi) {
        Measurement measurement = new Measurement();
        measurement.rssi = rssi;
        measurement.timestamp = SystemClock.elapsedRealtime();
        this.mMeasurements.add(measurement);
    }

    public boolean noMeasurementsAvailable() {
        return this.mMeasurements.size() == 0;
    }

    public int getMeasurementCount() {
        return this.mMeasurements.size();
    }

    public double calculateRssi() {
        refreshMeasurements();
        int size = this.mMeasurements.size();
        int startIndex = 0;
        int endIndex = size - 1;
        if (size > 2) {
            startIndex = (size / 10) + 1;
            endIndex = (size - (size / 10)) - 2;
        }
        double sum = 0.0d;
        for (int i = startIndex; i <= endIndex; i++) {
            sum += (double) ((Measurement) this.mMeasurements.get(i)).rssi.intValue();
        }
        LogManager.m16371d(TAG, "Running average mRssi based on %s measurements: %s", Integer.valueOf(size), Double.valueOf(sum / ((double) ((endIndex - startIndex) + 1))));
        return sum / ((double) ((endIndex - startIndex) + 1));
    }

    private synchronized void refreshMeasurements() {
        ArrayList<Measurement> newMeasurements = new ArrayList();
        Iterator<Measurement> iterator = this.mMeasurements.iterator();
        while (iterator.hasNext()) {
            Measurement measurement = (Measurement) iterator.next();
            if (SystemClock.elapsedRealtime() - measurement.timestamp < sampleExpirationMilliseconds) {
                newMeasurements.add(measurement);
            }
        }
        this.mMeasurements = newMeasurements;
        Collections.sort(this.mMeasurements);
    }

    public static void setSampleExpirationMilliseconds(long newSampleExpirationMilliseconds) {
        sampleExpirationMilliseconds = newSampleExpirationMilliseconds;
    }

    @RestrictTo({Scope.TESTS})
    static long getSampleExpirationMilliseconds() {
        return sampleExpirationMilliseconds;
    }
}
