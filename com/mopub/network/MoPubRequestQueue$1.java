package com.mopub.network;

import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue.RequestFilter;

class MoPubRequestQueue$1 implements RequestFilter {
    final /* synthetic */ MoPubRequestQueue this$0;
    final /* synthetic */ Object val$tag;

    MoPubRequestQueue$1(MoPubRequestQueue this$0, Object obj) {
        this.this$0 = this$0;
        this.val$tag = obj;
    }

    public boolean apply(Request<?> request) {
        return request.getTag() == this.val$tag;
    }
}
