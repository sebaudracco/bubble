package com.integralads.avid.library.mopub.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener listener;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.listener;
    }

    public void setListener(AvidWebViewClientListener listener) {
        this.listener = listener;
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (this.listener != null) {
            this.listener.webViewDidLoadData();
        }
    }
}
