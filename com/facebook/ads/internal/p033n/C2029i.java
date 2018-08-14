package com.facebook.ads.internal.p033n;

import cz.msebera.android.httpclient.HttpStatus;

public enum C2029i {
    HEIGHT_100(-1, 100),
    HEIGHT_120(-1, 120),
    HEIGHT_300(-1, HttpStatus.SC_MULTIPLE_CHOICES),
    HEIGHT_400(-1, HttpStatus.SC_BAD_REQUEST);
    
    private final int f4821e;
    private final int f4822f;

    private C2029i(int i, int i2) {
        this.f4821e = i;
        this.f4822f = i2;
    }

    public int m6499a() {
        return this.f4821e;
    }

    public int m6500b() {
        return this.f4822f;
    }

    public int m6501c() {
        switch (this.f4822f) {
            case 100:
                return 1;
            case 120:
                return 2;
            case HttpStatus.SC_MULTIPLE_CHOICES /*300*/:
                return 3;
            case HttpStatus.SC_BAD_REQUEST /*400*/:
                return 4;
            default:
                return -1;
        }
    }
}
