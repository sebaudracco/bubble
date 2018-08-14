package com.facebook.ads;

import com.facebook.ads.internal.p033n.C1847b;
import com.facebook.ads.internal.protocol.C2097a;

class NativeAd$1 implements C1847b {
    final /* synthetic */ AdListener f4013a;
    final /* synthetic */ NativeAd f4014b;

    NativeAd$1(NativeAd nativeAd, AdListener adListener) {
        this.f4014b = nativeAd;
        this.f4013a = adListener;
    }

    public void mo3582a() {
        this.f4013a.onAdLoaded(this.f4014b);
    }

    public void mo3583a(C2097a c2097a) {
        this.f4013a.onError(this.f4014b, AdError.getAdErrorFromWrapper(c2097a));
    }

    public void mo3584b() {
        this.f4013a.onAdClicked(this.f4014b);
    }

    public void mo3585c() {
        this.f4013a.onLoggingImpression(this.f4014b);
    }
}
