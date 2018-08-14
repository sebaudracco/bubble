package com.mopub.mobileads;

import android.content.Context;
import com.mopub.network.TrackingRequest;

class VastVideoViewController$7 implements VastWebView$VastWebViewClickListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ VastIconConfig val$vastIconConfig;

    VastVideoViewController$7(VastVideoViewController this$0, VastIconConfig vastIconConfig, Context context) {
        this.this$0 = this$0;
        this.val$vastIconConfig = vastIconConfig;
        this.val$context = context;
    }

    public void onVastWebViewClick() {
        TrackingRequest.makeVastTrackingHttpRequest(this.val$vastIconConfig.getClickTrackingUris(), null, Integer.valueOf(this.this$0.getCurrentPosition()), this.this$0.getNetworkMediaFileUrl(), this.val$context);
        this.val$vastIconConfig.handleClick(this.this$0.getContext(), null, VastVideoViewController.access$500(this.this$0).getDspCreativeId());
    }
}
