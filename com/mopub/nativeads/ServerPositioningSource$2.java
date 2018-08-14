package com.mopub.nativeads;

import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.volley.Response.Listener;

class ServerPositioningSource$2 implements Listener<MoPubClientPositioning> {
    final /* synthetic */ ServerPositioningSource this$0;

    ServerPositioningSource$2(ServerPositioningSource this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(MoPubClientPositioning clientPositioning) {
        ServerPositioningSource.access$100(this.this$0, clientPositioning);
    }
}
