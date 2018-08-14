package com.mopub.mobileads;

import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

class AdViewController$1 implements Listener {
    final /* synthetic */ AdViewController this$0;

    AdViewController$1(AdViewController this$0) {
        this.this$0 = this$0;
    }

    public void onSuccess(AdResponse response) {
        this.this$0.onAdLoadSuccess(response);
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.this$0.onAdLoadError(volleyError);
    }
}
