package com.mopub.mobileads;

import android.support.annotation.Nullable;

public abstract class CustomEventRewardedVideo extends CustomEventRewardedAd {

    @Deprecated
    protected interface CustomEventRewardedVideoListener {
    }

    @Deprecated
    protected abstract boolean hasVideoAvailable();

    @Deprecated
    protected abstract void showVideo();

    @Nullable
    @Deprecated
    protected CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return null;
    }

    protected boolean isReady() {
        return hasVideoAvailable();
    }

    protected void show() {
        showVideo();
    }
}
