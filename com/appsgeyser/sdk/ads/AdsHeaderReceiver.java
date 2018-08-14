package com.appsgeyser.sdk.ads;

import com.appsgeyser.sdk.ads.behavior.BehaviorFactory;
import com.appsgeyser.sdk.ads.behavior.BehaviorVisitor;
import java.util.List;
import java.util.Map;

class AdsHeaderReceiver implements AdsLoadingFinishedListener, HeadersReceiver {
    private final AdView adView;
    private final AdsLoader adsLoader;
    private Map<String, List<String>> lastResponseHeaders;

    AdsHeaderReceiver(AdView view, AdsLoader loader) {
        this.adsLoader = loader;
        this.adView = view;
    }

    public boolean onAdHeadersReceived(Map<String, List<String>> headers) {
        this.lastResponseHeaders = headers;
        return true;
    }

    public void onAdLoadFinished() {
        applyBehaviors(new BehaviorFactory().createPostloadBehaviors(this.lastResponseHeaders));
    }

    private void applyBehaviors(List<BehaviorVisitor> behaviors) {
        for (BehaviorVisitor visitor : behaviors) {
            this.adsLoader.acceptBehavior(visitor);
            this.adView.acceptBehavior(visitor);
        }
    }
}
