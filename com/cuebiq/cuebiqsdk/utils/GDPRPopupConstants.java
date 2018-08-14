package com.cuebiq.cuebiqsdk.utils;

public enum GDPRPopupConstants {
    FIRST(1),
    SECOND(2),
    PUBLISHER(100),
    GAID_IDFA_CHANGE(999),
    INTERNET_CONNECTION(0);
    
    private int popupValue;

    private GDPRPopupConstants(int i) {
        this.popupValue = i;
    }

    public final int getPopupValue() {
        return this.popupValue;
    }
}
