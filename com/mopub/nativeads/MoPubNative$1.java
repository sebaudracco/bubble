package com.mopub.nativeads;

import android.support.annotation.NonNull;

class MoPubNative$1 implements MoPubNative$MoPubNativeNetworkListener {
    MoPubNative$1() {
    }

    public void onNativeLoad(@NonNull NativeAd nativeAd) {
        nativeAd.destroy();
    }

    public void onNativeFail(NativeErrorCode errorCode) {
    }
}
