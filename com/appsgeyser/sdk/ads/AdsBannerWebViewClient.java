package com.appsgeyser.sdk.ads;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.utils.BannerUtils;

class AdsBannerWebViewClient extends WebViewClient {
    private OnPageFinishedListener onPageFinishedListener;
    private OnPageStartedListener onPageStartedListener;

    interface OnPageFinishedListener {
        void loadFinished(WebView webView, String str);
    }

    interface OnPageStartedListener {
        boolean loadStarted(WebView webView, String str, Bitmap bitmap);
    }

    AdsBannerWebViewClient() {
    }

    void setOnPageFinishedListener(OnPageFinishedListener listener) {
        this.onPageFinishedListener = listener;
    }

    void setOnPageStartedListener(OnPageStartedListener listener) {
        this.onPageStartedListener = listener;
    }

    public void onReceivedError(final WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        view.post(new Runnable() {
            public void run() {
                view.setVisibility(8);
            }
        });
    }

    public void onPageFinished(WebView view, String url) {
        if (this.onPageFinishedListener != null) {
            this.onPageFinishedListener.loadFinished(view, url);
        }
        super.onPageFinished(view, url);
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (BannerUtils.isDataTextHtmlUrl(url)) {
            this.onPageStartedListener.loadStarted(view, null, favicon);
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_DATA_TEXT_HTML_LOADED_IN_BANNER);
        } else if (this.onPageStartedListener == null || this.onPageStartedListener.loadStarted(view, url, favicon)) {
            super.onPageStarted(view, url, favicon);
        }
    }
}
