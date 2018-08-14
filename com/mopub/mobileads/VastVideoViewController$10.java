package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class VastVideoViewController$10 extends WebViewClient {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ VastCompanionAdConfig val$vastCompanionAdConfig;

    VastVideoViewController$10(VastVideoViewController this$0, VastCompanionAdConfig vastCompanionAdConfig, Context context) {
        this.this$0 = this$0;
        this.val$vastCompanionAdConfig = vastCompanionAdConfig;
        this.val$context = context;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        this.val$vastCompanionAdConfig.handleClick(this.val$context, 1, url, VastVideoViewController.access$500(this.this$0).getDspCreativeId());
        return true;
    }
}
