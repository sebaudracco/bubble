package com.fyber.ads.internal;

/* compiled from: ClientState */
public enum C2425d {
    READY_TO_CHECK_OFFERS(false, true),
    REQUESTING_OFFERS(false, false),
    READY_TO_SHOW_OFFERS(true, true),
    SHOWING_OFFERS(false, false);
    
    private final boolean f6069e;
    private final boolean f6070f;

    private C2425d(boolean z, boolean z2) {
        this.f6069e = z;
        this.f6070f = z2;
    }

    public final boolean m7675a() {
        return this.f6069e;
    }

    public final boolean m7676b() {
        return this.f6070f;
    }
}
