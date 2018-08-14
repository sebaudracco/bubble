package com.appnext.ads;

import com.appnext.core.AppnextError;

public class AdsError extends AppnextError {
    public static final String AD_NOT_READY = "Ad Not Ready";

    public AdsError(String str) {
        super(str);
    }
}
