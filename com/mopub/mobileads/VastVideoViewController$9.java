package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.IntentActions;
import com.mopub.network.TrackingRequest;

class VastVideoViewController$9 implements VastWebView$VastWebViewClickListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ VastCompanionAdConfig val$vastCompanionAdConfig;

    VastVideoViewController$9(VastVideoViewController this$0, VastCompanionAdConfig vastCompanionAdConfig, Context context) {
        this.this$0 = this$0;
        this.val$vastCompanionAdConfig = vastCompanionAdConfig;
        this.val$context = context;
    }

    public void onVastWebViewClick() {
        this.this$0.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
        TrackingRequest.makeVastTrackingHttpRequest(this.val$vastCompanionAdConfig.getClickTrackers(), null, Integer.valueOf(VastVideoViewController.access$400(this.this$0)), null, this.val$context);
        this.val$vastCompanionAdConfig.handleClick(this.val$context, 1, null, VastVideoViewController.access$500(this.this$0).getDspCreativeId());
    }
}
