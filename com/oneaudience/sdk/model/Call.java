package com.oneaudience.sdk.model;

public class Call {
    String cachedName;
    String date;
    String duration;
    String number;
    String type;

    public Call(String str, String str2, String str3, String str4, String str5) {
        this.number = str;
        this.cachedName = str2;
        this.date = str3;
        this.type = str4;
        this.duration = str5;
    }
}
