package com.vungle.publisher;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: vungle */
public class ml extends WebViewClient {
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        zo.b(webView);
    }
}
