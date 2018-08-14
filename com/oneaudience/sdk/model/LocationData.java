package com.oneaudience.sdk.model;

public class LocationData {
    float accuracy;
    double latitude;
    double longitude;
    String source;
    long timestamp;

    public LocationData(double d, double d2, float f, String str, long j) {
        this.latitude = d;
        this.longitude = d2;
        this.accuracy = f;
        this.source = str;
        this.timestamp = j;
    }
}
