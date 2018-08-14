package com.appsgeyser.sdk.ads.behavior.loaderBehaviors;

import com.appsgeyser.sdk.ads.AdsLoader;

public class LoaderRefreshTimeoutBehavior extends LoaderBehavior {
    private final float timeOut;

    public LoaderRefreshTimeoutBehavior(float timeout) {
        this.timeOut = timeout;
    }

    public void visit(AdsLoader loader) {
        loader.setRefreshTimeout(this.timeOut);
    }
}
