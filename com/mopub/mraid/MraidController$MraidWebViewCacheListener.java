package com.mopub.mraid;

import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.mraid.MraidBridge.MraidWebView;

public interface MraidController$MraidWebViewCacheListener {
    void onReady(MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager);
}
