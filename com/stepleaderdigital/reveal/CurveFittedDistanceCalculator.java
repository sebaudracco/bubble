package com.stepleaderdigital.reveal;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

public class CurveFittedDistanceCalculator implements DistanceCalculator {
    public static final String TAG = "CurveFittedDistanceCalculator";
    private double mCoefficient1;
    private double mCoefficient2;
    private double mCoefficient3;

    public CurveFittedDistanceCalculator(double coefficient1, double coefficient2, double coefficient3) {
        this.mCoefficient1 = coefficient1;
        this.mCoefficient2 = coefficient2;
        this.mCoefficient3 = coefficient3;
    }

    public double calculateDistance(int txPower, double rssi) {
        if (rssi == 0.0d) {
            return -1.0d;
        }
        double distance;
        double ratio = (rssi * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / ((double) txPower);
        if (ratio < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
            distance = Math.pow(ratio, 10.0d);
        } else {
            distance = (this.mCoefficient1 * Math.pow(ratio, this.mCoefficient2)) + this.mCoefficient3;
        }
        RevealLogger.m12440v("avg mRssi: " + rssi + " distance: " + distance);
        return distance;
    }
}
