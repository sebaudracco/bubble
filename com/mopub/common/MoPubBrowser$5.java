package com.mopub.common;

import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class MoPubBrowser$5 extends WebChromeClient {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$5(MoPubBrowser this$0) {
        this.this$0 = this$0;
    }

    public void onProgressChanged(WebView webView, int progress) {
        if (progress == 100) {
            this.this$0.setTitle(webView.getUrl());
        } else {
            this.this$0.setTitle("Loading...");
        }
        if (MoPubBrowser.access$100(this.this$0) && VERSION.SDK_INT < 24) {
            this.this$0.setProgress(progress * 100);
        }
    }
}
