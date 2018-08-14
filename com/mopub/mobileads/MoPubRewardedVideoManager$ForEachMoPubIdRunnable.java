package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;

abstract class MoPubRewardedVideoManager$ForEachMoPubIdRunnable implements Runnable {
    @NonNull
    private final Class<? extends CustomEventRewardedAd> mCustomEventClass;
    @NonNull
    private final String mThirdPartyId;

    protected abstract void forEach(@NonNull String str);

    MoPubRewardedVideoManager$ForEachMoPubIdRunnable(@NonNull Class<? extends CustomEventRewardedAd> customEventClass, @NonNull String thirdPartyId) {
        Preconditions.checkNotNull(customEventClass);
        Preconditions.checkNotNull(thirdPartyId);
        this.mCustomEventClass = customEventClass;
        this.mThirdPartyId = thirdPartyId;
    }

    public void run() {
        for (String moPubId : MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getMoPubIdsForAdNetwork(this.mCustomEventClass, this.mThirdPartyId)) {
            forEach(moPubId);
        }
    }
}
