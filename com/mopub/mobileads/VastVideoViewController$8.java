package com.mopub.mobileads;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class VastVideoViewController$8 extends WebViewClient {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ VastIconConfig val$vastIconConfig;

    VastVideoViewController$8(VastVideoViewController this$0, VastIconConfig vastIconConfig) {
        this.this$0 = this$0;
        this.val$vastIconConfig = vastIconConfig;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        this.val$vastIconConfig.handleClick(this.this$0.getContext(), url, VastVideoViewController.access$500(this.this$0).getDspCreativeId());
        return true;
    }
}
