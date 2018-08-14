package com.fyber.ads;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.fyber.requesters.Requester;
import java.io.Serializable;

public enum AdFormat {
    OFFER_WALL("ofw"),
    REWARDED_VIDEO("videos"),
    INTERSTITIAL("interstitial"),
    BANNER("banner"),
    UNKNOWN("unknown");
    
    private final String description;

    private AdFormat(String str) {
        this.description = str;
    }

    public static AdFormat fromIntent(@NonNull Intent intent) {
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(Requester.EXTRA_AD_FORMAT);
            if (serializableExtra != null) {
                return (AdFormat) serializableExtra;
            }
        }
        return UNKNOWN;
    }

    public final String toString() {
        return this.description;
    }
}
