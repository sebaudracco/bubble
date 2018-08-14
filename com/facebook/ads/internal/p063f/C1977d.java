package com.facebook.ads.internal.p063f;

import java.util.Map;

public class C1977d {
    private double f4616a;
    private double f4617b = (((double) System.currentTimeMillis()) / 1000.0d);
    private String f4618c;
    private Map<String, String> f4619d;

    public C1977d(double d, String str, Map<String, String> map) {
        this.f4616a = d;
        this.f4618c = str;
        this.f4619d = map;
    }

    public String mo3705a() {
        return "debug_crash_report";
    }

    public double m6237b() {
        return this.f4617b;
    }

    public double m6238c() {
        return this.f4616a;
    }

    public String m6239d() {
        return this.f4618c;
    }

    public Map<String, String> m6240e() {
        return this.f4619d;
    }
}
