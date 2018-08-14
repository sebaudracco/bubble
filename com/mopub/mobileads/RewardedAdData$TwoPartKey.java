package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.util.Pair;

class RewardedAdData$TwoPartKey extends Pair<Class<? extends CustomEventRewardedAd>, String> {
    @NonNull
    final String adNetworkId;
    @NonNull
    final Class<? extends CustomEventRewardedAd> customEventClass;

    public RewardedAdData$TwoPartKey(@NonNull Class<? extends CustomEventRewardedAd> customEventClass, @NonNull String adNetworkId) {
        super(customEventClass, adNetworkId);
        this.customEventClass = customEventClass;
        this.adNetworkId = adNetworkId;
    }
}
