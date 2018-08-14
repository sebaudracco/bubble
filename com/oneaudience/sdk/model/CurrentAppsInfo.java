package com.oneaudience.sdk.model;

import java.util.ArrayList;

public class CurrentAppsInfo {
    ArrayList<ProcessInfo> processes;
    long timestamp;

    public CurrentAppsInfo(long j, ArrayList<ProcessInfo> arrayList) {
        this.timestamp = j;
        this.processes = arrayList;
    }
}
