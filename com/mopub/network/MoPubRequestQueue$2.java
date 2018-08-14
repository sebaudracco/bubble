package com.mopub.network;

import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue.RequestFilter;

class MoPubRequestQueue$2 implements RequestFilter {
    final /* synthetic */ MoPubRequestQueue this$0;
    final /* synthetic */ Request val$request;

    MoPubRequestQueue$2(MoPubRequestQueue this$0, Request request) {
        this.this$0 = this$0;
        this.val$request = request;
    }

    public boolean apply(Request<?> _request) {
        return this.val$request == _request;
    }
}
