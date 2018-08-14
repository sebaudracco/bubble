package org.altbeacon.beacon.distance;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import org.altbeacon.beacon.logging.LogManager;

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
        LogManager.m16371d("CurveFittedDistanceCalculator", "calculating distance based on mRssi of %s and txPower of %s", Double.valueOf(rssi), Integer.valueOf(txPower));
        double ratio = (MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE * rssi) / ((double) txPower);
        if (ratio < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
            distance = Math.pow(ratio, 10.0d);
        } else {
            distance = (this.mCoefficient1 * Math.pow(ratio, this.mCoefficient2)) + this.mCoefficient3;
        }
        LogManager.m16371d("CurveFittedDistanceCalculator", "avg mRssi: %s distance: %s", Double.valueOf(rssi), Double.valueOf(distance));
        return distance;
    }
}
