package com.oneaudience.sdk.model;

public class BasicData {
    String advertisingId;
    String deviceId;
    String imei;
    String macAddress;
    String manufacturer;
    String networkProvider;
    String privateIp;

    public BasicData(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.advertisingId = str;
        this.deviceId = str2;
        this.macAddress = str3;
        this.imei = str4;
        this.manufacturer = str5;
        this.privateIp = str6;
        this.networkProvider = str7;
    }
}
