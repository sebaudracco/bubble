package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.ExternalViewabilitySessionManager;
import java.lang.ref.WeakReference;

public class WebViewCacheService$Config {
    @NonNull
    private final ExternalViewabilitySessionManager mViewabilityManager;
    @NonNull
    private final WeakReference<Interstitial> mWeakInterstitial;
    @NonNull
    private final BaseWebView mWebView;

    WebViewCacheService$Config(@NonNull BaseWebView baseWebView, @NonNull Interstitial baseInterstitial, @NonNull ExternalViewabilitySessionManager viewabilityManager) {
        this.mWebView = baseWebView;
        this.mWeakInterstitial = new WeakReference(baseInterstitial);
        this.mViewabilityManager = viewabilityManager;
    }

    @NonNull
    public BaseWebView getWebView() {
        return this.mWebView;
    }

    @NonNull
    public WeakReference<Interstitial> getWeakInterstitial() {
        return this.mWeakInterstitial;
    }

    @NonNull
    public ExternalViewabilitySessionManager getViewabilityManager() {
        return this.mViewabilityManager;
    }
}
