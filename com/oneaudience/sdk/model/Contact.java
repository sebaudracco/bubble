package com.oneaudience.sdk.model;

public class Contact {
    String address;
    String birthday;
    String email;
    String name;
    String phone;

    public Contact(String str, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.email = str2;
        this.address = str3;
        this.phone = str4;
        this.birthday = str5;
    }
}
