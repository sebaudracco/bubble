package com.appnext.core;

public class ECPM {
    private String banner;
    private float ecpm;
    private float ppr;

    public ECPM(float f, float f2, String str) {
        this.ecpm = f;
        this.ppr = f2;
        this.banner = str;
    }

    public float getEcpm() {
        return this.ecpm;
    }

    protected void m2293c(float f) {
        this.ecpm = f;
    }

    public float getPpr() {
        return this.ppr;
    }

    protected void m2294d(float f) {
        this.ppr = f;
    }

    public String getBanner() {
        return this.banner;
    }

    protected void aT(String str) {
        this.banner = str;
    }
}
