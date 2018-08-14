package com.fyber.mediation;

public enum FyberNetworkExtras {
    DEBUG_LOGGING("loggingEnabled"),
    SHOW_TOAST_AFTER_REQUEST("showRequestNotification"),
    SHOW_TOAST_AFTER_REWARD("showPayoffNotification");
    
    private String mKey;

    private FyberNetworkExtras(String pKey) {
        this.mKey = pKey;
    }

    public String getKey() {
        return this.mKey;
    }
}
