package com.facebook.ads.internal.protocol;

import cz.msebera.android.httpclient.HttpStatus;

public enum C1562f {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(101),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(103),
    NATIVE_UNKNOWN(200),
    REWARDED_VIDEO(HttpStatus.SC_BAD_REQUEST),
    NATIVE_250(201),
    INSTREAM_VIDEO(HttpStatus.SC_MULTIPLE_CHOICES);
    
    private final int f2629n;

    private C1562f(int i) {
        this.f2629n = i;
    }

    public int m3421a() {
        return this.f2629n;
    }
}
