package com.integralads.avid.library.adcolony.session.internal;

public enum SessionType {
    DISPLAY("display"),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String f8370a;

    private SessionType(String value) {
        this.f8370a = value;
    }

    public String toString() {
        return this.f8370a;
    }
}
