package com.oneaudience.sdk.model;

public class WifiNetworkInfo {
    public String bssid;
    public boolean isConfigured;
    public int level;
    public String ssid;
    public int status;
    public long timestamp;

    public WifiNetworkInfo(int i, String str, String str2, int i2, long j, boolean z) {
        this.status = i;
        this.bssid = str;
        this.ssid = str2;
        this.level = i2;
        this.timestamp = j;
        this.isConfigured = z;
    }
}
