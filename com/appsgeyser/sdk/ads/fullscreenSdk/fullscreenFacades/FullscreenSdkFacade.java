package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import java.io.Serializable;

public interface FullscreenSdkFacade {

    public interface FullscreenAdListener extends Serializable {
        void onFullscreenClicked();

        void onFullscreenClosed();

        void onFullscreenError(String str);

        void onFullscreenOpened();

        void onFullscreenReceived();
    }

    boolean isFullscreenLoaded();

    void loadFullscreenAd();

    void onPause();

    void onResume();

    void setListener(FullscreenAdListener fullscreenAdListener);

    void showFullscreenAd();
}
