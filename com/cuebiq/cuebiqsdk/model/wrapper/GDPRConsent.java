package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class GDPRConsent {
    private String deviceId;
    private String os = "ANDROID";
    private int whichPopupUserGrantsConsentIn;

    public GDPRConsent(String str, int i) {
        this.deviceId = str;
        this.whichPopupUserGrantsConsentIn = i;
    }

    public String getId() {
        return this.deviceId;
    }

    public String getOs() {
        return this.os;
    }

    public int getWhichPopup() {
        return this.whichPopupUserGrantsConsentIn;
    }

    public String toJSON() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }

    public String toString() {
        return "GDPRConsent{deviceId='" + this.deviceId + '\'' + ", whichPopupUserGrantsConsentIn=" + this.whichPopupUserGrantsConsentIn + ", os='" + this.os + '\'' + '}';
    }
}
