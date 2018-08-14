package com.mopub.nativeads;

public interface MoPubNative$MoPubNativeNetworkListener {
    void onNativeFail(NativeErrorCode nativeErrorCode);

    void onNativeLoad(NativeAd nativeAd);
}
