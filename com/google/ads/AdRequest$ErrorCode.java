package com.google.ads;

public enum AdRequest$ErrorCode {
    INVALID_REQUEST("Invalid Ad request."),
    NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory."),
    NETWORK_ERROR("A network error occurred."),
    INTERNAL_ERROR("There was an internal error.");
    
    private final String description;

    private AdRequest$ErrorCode(String str) {
        this.description = str;
    }

    public final String toString() {
        return this.description;
    }
}
