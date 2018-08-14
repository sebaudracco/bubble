package com.facebook.ads;

import com.facebook.ads.internal.p033n.C2030j;

public enum VideoAutoplayBehavior {
    DEFAULT,
    ON,
    OFF;

    public static VideoAutoplayBehavior fromInternalAutoplayBehavior(C2030j c2030j) {
        if (c2030j == null) {
            return DEFAULT;
        }
        switch (c2030j) {
            case DEFAULT:
                return DEFAULT;
            case ON:
                return ON;
            case OFF:
                return OFF;
            default:
                return DEFAULT;
        }
    }
}
