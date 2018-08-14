package com.mopub.common;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.EnumSet;

class BrowserWebViewClient extends WebViewClient {
    private static final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_PHONE_SCHEME, new UrlAction[]{UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    @NonNull
    private MoPubBrowser mMoPubBrowser;

    class C36141 implements ResultActions {
        C36141() {
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            if (urlAction.equals(UrlAction.OPEN_IN_APP_BROWSER)) {
                BrowserWebViewClient.this.mMoPubBrowser.getWebView().loadUrl(url);
            } else {
                BrowserWebViewClient.this.mMoPubBrowser.finish();
            }
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    public BrowserWebViewClient(@NonNull MoPubBrowser moPubBrowser) {
        this.mMoPubBrowser = moPubBrowser;
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        MoPubLog.m12061d("MoPubBrowser error: " + description);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return new Builder().withSupportedUrlActions(SUPPORTED_URL_ACTIONS).withoutMoPubBrowser().withResultActions(new C36141()).build().handleResolvedUrl(this.mMoPubBrowser.getApplicationContext(), url, true, null);
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    public void onPageFinished(WebView view, String url) {
        Drawable backImageDrawable;
        Drawable forwardImageDrawable;
        super.onPageFinished(view, url);
        if (view.canGoBack()) {
            backImageDrawable = Drawables.LEFT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            backImageDrawable = Drawables.UNLEFT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getBackButton().setImageDrawable(backImageDrawable);
        if (view.canGoForward()) {
            forwardImageDrawable = Drawables.RIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            forwardImageDrawable = Drawables.UNRIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getForwardButton().setImageDrawable(forwardImageDrawable);
    }
}
