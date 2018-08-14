package com.altbeacon.beacon.p012b;

import com.altbeacon.beacon.p013c.C0835d;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class C0823b implements C0822c {
    private double f1610a;
    private double f1611b;
    private double f1612c;

    public C0823b(double d, double d2, double d3) {
        this.f1610a = d;
        this.f1611b = d2;
        this.f1612c = d3;
    }

    public double mo1872a(int i, double d) {
        if (d == 0.0d) {
            return -1.0d;
        }
        C0835d.m1657a("CurveFittedDistanceCalculator", "calculating distance based on mRssi of %s and txPower of %s", Double.valueOf(d), Integer.valueOf(i));
        double d2 = (d * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / ((double) i);
        if (d2 < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
            d2 = Math.pow(d2, 10.0d);
        } else {
            d2 = (Math.pow(d2, this.f1611b) * this.f1610a) + this.f1612c;
        }
        C0835d.m1657a("CurveFittedDistanceCalculator", "avg mRssi: %s distance: %s", Double.valueOf(d), Double.valueOf(d2));
        return d2;
    }
}
