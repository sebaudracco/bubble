package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

public interface CustomEventNativeListener extends CustomEventListener {
    void onAdImpression();

    void onAdLoaded(NativeAdMapper nativeAdMapper);

    void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper);
}
