package com.integralads.avid.library.inmobi.session.internal;

public enum SessionType {
    DISPLAY("display"),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String f8472a;

    private SessionType(String str) {
        this.f8472a = str;
    }

    public String toString() {
        return this.f8472a;
    }
}
