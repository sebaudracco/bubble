package com.oneaudience.sdk.model;

public class BeaconURL {
    public long timestamp;
    public String url;

    public BeaconURL(String str, long j) {
        this.url = str;
        this.timestamp = j;
    }
}
