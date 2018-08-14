package com.mopub.mobileads.util;

import android.support.annotation.NonNull;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mopub.common.logging.MoPubLog;

public class WebViews {

    static class C36921 extends WebChromeClient {
        C36921() {
        }

        public boolean onJsAlert(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.m12061d(message);
            result.confirm();
            return true;
        }

        public boolean onJsConfirm(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.m12061d(message);
            result.confirm();
            return true;
        }

        public boolean onJsPrompt(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull String defaultValue, @NonNull JsPromptResult result) {
            MoPubLog.m12061d(message);
            result.confirm();
            return true;
        }

        public boolean onJsBeforeUnload(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.m12061d(message);
            result.confirm();
            return true;
        }
    }

    public static void onPause(@NonNull WebView webView, boolean isFinishing) {
        if (isFinishing) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        webView.onPause();
    }

    public static void setDisableJSChromeClient(@NonNull WebView webView) {
        webView.setWebChromeClient(new C36921());
    }
}
