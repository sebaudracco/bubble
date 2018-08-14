package com.vungle.mediation;

abstract class VungleListener {
    private boolean mIsWaitingInit = false;
    private String mWaitingForPlacement;

    VungleListener() {
    }

    void waitForAd(String placement) {
        this.mWaitingForPlacement = placement;
    }

    String getWaitingForPlacement() {
        return this.mWaitingForPlacement;
    }

    public boolean isWaitingInit() {
        return this.mIsWaitingInit;
    }

    void setWaitingInit(boolean isWaitingInit) {
        this.mIsWaitingInit = isWaitingInit;
    }

    void onAdEnd(String placement, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
    }

    void onAdStart(String placement) {
    }

    void onAdFail(String placement) {
    }

    void onAdAvailable() {
    }

    void onInitialized(boolean isSuccess) {
    }
}
