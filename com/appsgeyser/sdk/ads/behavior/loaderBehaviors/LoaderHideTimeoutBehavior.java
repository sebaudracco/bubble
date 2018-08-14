package com.appsgeyser.sdk.ads.behavior.loaderBehaviors;

import com.appsgeyser.sdk.ads.AdsLoader;

public class LoaderHideTimeoutBehavior extends LoaderBehavior {
    private final float timeOut;

    public LoaderHideTimeoutBehavior(float timeout) {
        this.timeOut = timeout;
    }

    public void visit(AdsLoader loader) {
        loader.setHideTimeout(this.timeOut);
    }
}
