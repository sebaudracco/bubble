package com.oneaudience.sdk.model;

public class InstalledPackage {
    long firstInstallTime;
    long lastUpdateTime;
    String packageName;

    public InstalledPackage(String str, long j, long j2) {
        this.packageName = str;
        this.firstInstallTime = j;
        this.lastUpdateTime = j2;
    }
}
