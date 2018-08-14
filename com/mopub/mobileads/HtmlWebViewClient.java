package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.MoPubSchemeListener;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import java.util.EnumSet;

class HtmlWebViewClient extends WebViewClient {
    static final String MOPUB_FAIL_LOAD = "mopub://failLoad";
    static final String MOPUB_FINISH_LOAD = "mopub://finishLoad";
    private final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_MOPUB_SCHEME, new UrlAction[]{UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.HANDLE_PHONE_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    private final String mClickthroughUrl;
    private final Context mContext;
    private final String mDspCreativeId;
    private final BaseHtmlWebView mHtmlWebView;
    private final HtmlWebViewListener mHtmlWebViewListener;
    private final String mRedirectUrl;

    class C36531 implements MoPubSchemeListener {
        C36531() {
        }

        public void onFinishLoad() {
            HtmlWebViewClient.this.mHtmlWebViewListener.onLoaded(HtmlWebViewClient.this.mHtmlWebView);
        }

        public void onClose() {
            HtmlWebViewClient.this.mHtmlWebViewListener.onCollapsed();
        }

        public void onFailLoad() {
            HtmlWebViewClient.this.mHtmlWebView.stopLoading();
            HtmlWebViewClient.this.mHtmlWebViewListener.onFailed(MoPubErrorCode.UNSPECIFIED);
        }
    }

    class C36542 implements ResultActions {
        C36542() {
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            if (HtmlWebViewClient.this.mHtmlWebView.wasClicked()) {
                HtmlWebViewClient.this.mHtmlWebViewListener.onClicked();
                HtmlWebViewClient.this.mHtmlWebView.onResetUserClick();
            }
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    HtmlWebViewClient(HtmlWebViewListener htmlWebViewListener, BaseHtmlWebView htmlWebView, String clickthrough, String redirect, String dspCreativeId) {
        this.mHtmlWebViewListener = htmlWebViewListener;
        this.mHtmlWebView = htmlWebView;
        this.mClickthroughUrl = clickthrough;
        this.mRedirectUrl = redirect;
        this.mDspCreativeId = dspCreativeId;
        this.mContext = htmlWebView.getContext();
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        new Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(this.SUPPORTED_URL_ACTIONS).withResultActions(new C36542()).withMoPubSchemeListener(new C36531()).build().handleUrl(this.mContext, url, this.mHtmlWebView.wasClicked());
        return true;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (this.mRedirectUrl != null && url.startsWith(this.mRedirectUrl)) {
            view.stopLoading();
            if (this.mHtmlWebView.wasClicked()) {
                try {
                    Intents.showMoPubBrowserForUrl(this.mContext, Uri.parse(url), this.mDspCreativeId);
                    return;
                } catch (IntentNotResolvableException e) {
                    MoPubLog.m12061d(e.getMessage());
                    return;
                }
            }
            MoPubLog.m12061d("Attempted to redirect without user interaction");
        }
    }
}
