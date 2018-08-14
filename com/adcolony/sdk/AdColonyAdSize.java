package com.adcolony.sdk;

import cz.msebera.android.httpclient.HttpStatus;

public class AdColonyAdSize {
    public static final AdColonyAdSize MEDIUM_RECTANGLE = new AdColonyAdSize(HttpStatus.SC_MULTIPLE_CHOICES, 250);
    static final AdColonyAdSize f373c = new AdColonyAdSize(320, 50);
    int f374a;
    int f375b;

    public AdColonyAdSize(int width, int height) {
        this.f374a = width;
        this.f375b = height;
    }
}
