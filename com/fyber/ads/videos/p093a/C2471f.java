package com.fyber.ads.videos.p093a;

/* compiled from: RewardedVideoOffersStatus */
public enum C2471f {
    MUST_QUERY_SERVER_FOR_OFFERS(false, true),
    QUERYING_SERVER_FOR_OFFERS(false, false),
    READY_TO_SHOW_OFFERS(true, true),
    SHOWING_OFFERS(false, false),
    USER_ENGAGED(false, false);
    
    private final boolean f6197f;
    private final boolean f6198g;

    private C2471f(boolean z, boolean z2) {
        this.f6197f = z;
        this.f6198g = z2;
    }

    final boolean m7853a() {
        return this.f6197f;
    }

    final boolean m7854b() {
        return this.f6198g;
    }
}
