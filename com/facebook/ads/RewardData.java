package com.facebook.ads;

public class RewardData {
    private String f4049a;
    private String f4050b;

    public RewardData(String str, String str2) {
        this.f4049a = str;
        this.f4050b = str2;
    }

    public String getCurrency() {
        return this.f4050b;
    }

    public String getUserID() {
        return this.f4049a;
    }
}
