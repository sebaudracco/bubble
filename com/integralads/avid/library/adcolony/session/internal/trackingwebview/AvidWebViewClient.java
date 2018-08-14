package com.integralads.avid.library.adcolony.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener f8396a;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.f8396a;
    }

    public void setListener(AvidWebViewClientListener listener) {
        this.f8396a = listener;
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (this.f8396a != null) {
            this.f8396a.webViewDidLoadData();
        }
    }
}
