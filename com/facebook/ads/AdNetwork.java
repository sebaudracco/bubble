package com.facebook.ads;

import com.facebook.ads.internal.p033n.C2020c;

public enum AdNetwork {
    AN(C2020c.AN),
    ADMOB(C2020c.ADMOB),
    FLURRY(C2020c.FLURRY),
    INMOBI(C2020c.INMOBI);
    
    private final C2020c f3956a;

    private AdNetwork(C2020c c2020c) {
        this.f3956a = c2020c;
    }

    public static AdNetwork fromInternalAdNetwork(C2020c c2020c) {
        if (c2020c == null) {
            return AN;
        }
        switch (c2020c) {
            case AN:
                return AN;
            case ADMOB:
                return ADMOB;
            case FLURRY:
                return FLURRY;
            case INMOBI:
                return INMOBI;
            default:
                return AN;
        }
    }
}
