package com.facebook.ads;

import com.facebook.ads.internal.view.p053e.p083a.C2222a;

public enum VideoStartReason {
    NOT_STARTED(C2222a.NOT_STARTED),
    USER_STARTED(C2222a.USER_STARTED),
    AUTO_STARTED(C2222a.AUTO_STARTED);
    
    private final C2222a f4063a;

    private VideoStartReason(C2222a c2222a) {
        this.f4063a = c2222a;
    }

    C2222a m5533a() {
        return this.f4063a;
    }
}
