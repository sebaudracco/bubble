package com.appsgeyser.sdk.webwiewclient;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

public class PushWebViewClient extends SimpleWebViewClient {
    private final String defaultUrl;

    public /* bridge */ /* synthetic */ void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public PushWebViewClient(String defaultUrl, Activity activity) {
        super(activity);
        this.defaultUrl = defaultUrl;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (!proceedPageStarted(view, url)) {
            super.onPageStarted(view, url, favicon);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url) || proceedPageStarted(view, url);
    }

    private boolean proceedPageStarted(WebView view, String url) {
        if (url.equals(this.defaultUrl)) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            view.loadUrl(this.defaultUrl);
            if (this.activity.getPackageManager().resolveActivity(intent, 0) != null) {
                this.activity.startActivity(intent);
            }
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("ActivityNotFoundExceptn", "onPageStarted() :" + e.getMessage());
            return false;
        }
    }
}
