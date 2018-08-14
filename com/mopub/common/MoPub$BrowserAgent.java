package com.mopub.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public enum MoPub$BrowserAgent {
    IN_APP,
    NATIVE;

    @NonNull
    public static MoPub$BrowserAgent fromHeader(@Nullable Integer browserAgent) {
        if (browserAgent == null) {
            return IN_APP;
        }
        return browserAgent.intValue() == 1 ? NATIVE : IN_APP;
    }
}
