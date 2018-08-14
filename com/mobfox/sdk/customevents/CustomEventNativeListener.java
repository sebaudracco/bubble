package com.mobfox.sdk.customevents;

import com.mobfox.sdk.nativeads.NativeAd;

public interface CustomEventNativeListener {
    void onNativeClicked(CustomEventNative customEventNative);

    void onNativeError(Exception exception);

    void onNativeReady(CustomEventNative customEventNative, NativeAd nativeAd);
}
