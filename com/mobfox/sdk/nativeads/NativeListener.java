package com.mobfox.sdk.nativeads;

import com.mobfox.sdk.customevents.CustomEventNative;

public interface NativeListener {
    void onNativeClick(NativeAd nativeAd);

    void onNativeError(Exception exception);

    void onNativeReady(Native nativeR, CustomEventNative customEventNative, NativeAd nativeAd);
}
