package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

class MoPubNative$2 implements Listener {
    final /* synthetic */ MoPubNative this$0;

    MoPubNative$2(MoPubNative this$0) {
        this.this$0 = this$0;
    }

    public void onSuccess(@NonNull AdResponse response) {
        MoPubNative.access$000(this.this$0, response);
    }

    public void onErrorResponse(@NonNull VolleyError volleyError) {
        this.this$0.onAdError(volleyError);
    }
}
