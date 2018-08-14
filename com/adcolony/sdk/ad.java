package com.adcolony.sdk;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

class ad {
    static float[] f513a = new float[16];
    static ad f514b = new ad();
    double[] f515c;
    boolean f516d;

    ad() {
        this.f515c = new double[16];
        m675b();
    }

    ad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.f515c = new double[16];
        m678b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
    }

    ad m666a() {
        ad adVar = new ad();
        adVar.m679b(this);
        return adVar;
    }

    ad m672a(ad adVar) {
        return adVar.f516d ? this : m669a(adVar.f515c[0], adVar.f515c[1], adVar.f515c[2], adVar.f515c[3], adVar.f515c[4], adVar.f515c[5], adVar.f515c[6], adVar.f515c[7], adVar.f515c[8], adVar.f515c[9], adVar.f515c[10], adVar.f515c[11], adVar.f515c[12], adVar.f515c[13], adVar.f515c[14], adVar.f515c[15]);
    }

    ad m669a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        return m670a(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, this);
    }

    ad m670a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, ad adVar) {
        if (this.f516d) {
            return adVar.m678b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
        }
        double d17 = this.f515c[0];
        double d18 = this.f515c[1];
        double d19 = this.f515c[2];
        double d20 = this.f515c[3];
        double d21 = this.f515c[4];
        double d22 = this.f515c[5];
        double d23 = this.f515c[6];
        double d24 = this.f515c[7];
        double d25 = this.f515c[8];
        double d26 = this.f515c[9];
        double d27 = this.f515c[10];
        double d28 = this.f515c[11];
        double d29 = this.f515c[12];
        double d30 = this.f515c[13];
        double d31 = this.f515c[14];
        double d32 = this.f515c[15];
        adVar.f515c[0] = (((d17 * d) + (d18 * d5)) + (d19 * d9)) + (d20 * d13);
        adVar.f515c[1] = (((d17 * d2) + (d18 * d6)) + (d19 * d10)) + (d20 * d14);
        adVar.f515c[2] = (((d17 * d3) + (d18 * d7)) + (d19 * d11)) + (d20 * d15);
        adVar.f515c[3] = (((d17 * d4) + (d18 * d8)) + (d19 * d12)) + (d20 * d16);
        adVar.f515c[4] = (((d21 * d) + (d22 * d5)) + (d23 * d9)) + (d24 * d13);
        adVar.f515c[5] = (((d21 * d2) + (d22 * d6)) + (d23 * d10)) + (d24 * d14);
        adVar.f515c[6] = (((d21 * d3) + (d22 * d7)) + (d23 * d11)) + (d24 * d15);
        adVar.f515c[7] = (((d21 * d4) + (d22 * d8)) + (d23 * d12)) + (d24 * d16);
        adVar.f515c[8] = (((d25 * d) + (d26 * d5)) + (d27 * d9)) + (d28 * d13);
        adVar.f515c[9] = (((d25 * d2) + (d26 * d6)) + (d27 * d10)) + (d28 * d14);
        adVar.f515c[10] = (((d25 * d3) + (d26 * d7)) + (d27 * d11)) + (d28 * d15);
        adVar.f515c[11] = (((d25 * d4) + (d26 * d8)) + (d27 * d12)) + (d28 * d16);
        adVar.f515c[12] = (((d29 * d) + (d30 * d5)) + (d31 * d9)) + (d32 * d13);
        adVar.f515c[13] = (((d29 * d2) + (d30 * d6)) + (d31 * d10)) + (d32 * d14);
        adVar.f515c[14] = (((d29 * d3) + (d30 * d7)) + (d31 * d11)) + (d32 * d15);
        adVar.f515c[15] = (((d29 * d4) + (d30 * d8)) + (d31 * d12)) + (d32 * d16);
        this.f516d = false;
        return adVar;
    }

    ad m673a(ad adVar, ad adVar2) {
        if (adVar.f516d) {
            return adVar2.m679b(this);
        }
        return m670a(adVar.f515c[0], adVar.f515c[1], adVar.f515c[2], adVar.f515c[3], adVar.f515c[4], adVar.f515c[5], adVar.f515c[6], adVar.f515c[7], adVar.f515c[8], adVar.f515c[9], adVar.f515c[10], adVar.f515c[11], adVar.f515c[12], adVar.f515c[13], adVar.f515c[14], adVar.f515c[15], adVar2);
    }

    ad m667a(double d) {
        f514b.m675b();
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        f514b.f515c[0] = cos;
        f514b.f515c[1] = sin;
        f514b.f515c[4] = -sin;
        f514b.f515c[5] = cos;
        f514b.f516d = false;
        return m672a(f514b);
    }

    ad m676b(double d) {
        return m667a((3.141592653589793d * d) / 180.0d);
    }

    ad m668a(double d, double d2, double d3) {
        f514b.m675b();
        f514b.f515c[0] = d;
        f514b.f515c[5] = d2;
        f514b.f515c[10] = d3;
        f514b.f516d = false;
        return m672a(f514b);
    }

    ad m679b(ad adVar) {
        for (int i = 15; i >= 0; i--) {
            this.f515c[i] = adVar.f515c[i];
        }
        this.f516d = adVar.f516d;
        return this;
    }

    ad m678b(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.f516d = false;
        this.f515c[0] = d;
        this.f515c[1] = d2;
        this.f515c[2] = d3;
        this.f515c[3] = d4;
        this.f515c[4] = d5;
        this.f515c[5] = d6;
        this.f515c[6] = d7;
        this.f515c[7] = d8;
        this.f515c[8] = d9;
        this.f515c[9] = d10;
        this.f515c[10] = d11;
        this.f515c[11] = d12;
        this.f515c[12] = d13;
        this.f515c[13] = d14;
        this.f515c[14] = d15;
        this.f515c[15] = d16;
        return this;
    }

    ad m675b() {
        for (int i = 15; i >= 0; i--) {
            this.f515c[i] = 0.0d;
        }
        this.f515c[0] = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        this.f515c[5] = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        this.f515c[10] = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        this.f515c[15] = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        this.f516d = true;
        return this;
    }

    ad m671a(int i, int i2, double d) {
        m675b();
        this.f515c[0] = 2.0d / ((double) i);
        this.f515c[5] = -2.0d / ((double) i2);
        this.f515c[10] = -2.0d / d;
        this.f515c[12] = -1.0d;
        this.f515c[13] = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        this.f515c[14] = -1.0d;
        this.f516d = false;
        return this;
    }

    ad m677b(double d, double d2, double d3) {
        f514b.m675b();
        f514b.f515c[12] = d;
        f514b.f515c[13] = d2;
        f514b.f515c[14] = d3;
        f514b.f516d = false;
        return m672a(f514b);
    }

    ad m680c(double d) {
        for (int i = 15; i >= 0; i--) {
            this.f515c[i] = d;
        }
        this.f516d = false;
        return this;
    }

    float[] m681c() {
        return m674a(f513a);
    }

    float[] m674a(float[] fArr) {
        for (int i = 15; i >= 0; i--) {
            fArr[i] = (float) this.f515c[i];
        }
        return fArr;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f515c[0]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[1]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[2]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[3]);
        stringBuilder.append('\n');
        stringBuilder.append(this.f515c[4]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[5]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[6]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[7]);
        stringBuilder.append('\n');
        stringBuilder.append(this.f515c[8]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[9]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[10]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[11]);
        stringBuilder.append('\n');
        stringBuilder.append(this.f515c[12]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[13]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[14]);
        stringBuilder.append(' ');
        stringBuilder.append(this.f515c[15]);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
