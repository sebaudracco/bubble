package com.vungle.publisher;

/* compiled from: vungle */
public class ze {
    public static double m14194a(double d, double d2) {
        if (d > 0.0d && d2 > 0.0d) {
            return Math.log(d2) / Math.log(d);
        }
        throw new IllegalArgumentException("base and number must be greater than 0");
    }

    public static double m14196a(double d, double d2, double d3, double d4) {
        if (d2 < m14194a(d, d4 / Math.abs(d3))) {
            return Math.pow(d, d2) * d3;
        }
        return d4;
    }

    public static double m14195a(double d, double d2, double d3) {
        return m14196a(2.0d, d, d2, d3);
    }

    public static long m14198a(long j, long j2, long j3) {
        if (j < 0 || j2 < 0 || j3 < 0) {
            throw new IllegalArgumentException("inputs must be positive");
        } else if (j == 0 || j2 == 0) {
            return 0;
        } else {
            return j2 < j3 / j ? j * j2 : j3;
        }
    }

    public static long m14197a(long j, long j2) {
        return m14198a(j, j2, Long.MAX_VALUE);
    }
}
