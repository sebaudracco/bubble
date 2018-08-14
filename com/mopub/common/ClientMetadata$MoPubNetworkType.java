package com.mopub.common;

public enum ClientMetadata$MoPubNetworkType {
    UNKNOWN(0),
    ETHERNET(1),
    WIFI(2),
    MOBILE(3);
    
    private final int mId;

    private ClientMetadata$MoPubNetworkType(int id) {
        this.mId = id;
    }

    public String toString() {
        return Integer.toString(this.mId);
    }

    private static ClientMetadata$MoPubNetworkType fromAndroidNetworkType(int type) {
        switch (type) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                return MOBILE;
            case 1:
                return WIFI;
            case 9:
                return ETHERNET;
            default:
                return UNKNOWN;
        }
    }

    public int getId() {
        return this.mId;
    }
}
