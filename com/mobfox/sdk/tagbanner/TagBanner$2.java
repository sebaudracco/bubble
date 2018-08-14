package com.mobfox.sdk.tagbanner;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class TagBanner$2 extends WebChromeClient {
    final /* synthetic */ TagBanner this$0;

    TagBanner$2(TagBanner this$0) {
        this.this$0 = this$0;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.cancel();
        return true;
    }

    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        result.cancel();
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.cancel();
        return true;
    }
}
