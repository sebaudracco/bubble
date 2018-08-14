package com.fyber.ads.videos.mediation;

public enum TPNVideoValidationResult {
    AdapterNotIntegrated("no_sdk"),
    NoVideoAvailable("no_video"),
    Timeout("timeout"),
    NetworkError("network_error"),
    DiskError("disk_error"),
    Error("error"),
    Success("success");
    
    private final String text;

    private TPNVideoValidationResult(String str) {
        this.text = str;
    }

    public final String toString() {
        return this.text;
    }
}
