package com.mopub.mobileads;

import android.location.Location;
import android.support.annotation.Nullable;

public final class MoPubRewardedVideoManager$RequestParameters {
    @Nullable
    public final String mCustomerId;
    @Nullable
    public final String mKeywords;
    @Nullable
    public final Location mLocation;

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String keywords) {
        this(keywords, null);
    }

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String keywords, @Nullable Location location) {
        this(keywords, location, null);
    }

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String keywords, @Nullable Location location, @Nullable String customerId) {
        this.mKeywords = keywords;
        this.mLocation = location;
        this.mCustomerId = customerId;
    }
}
