package com.mobfox.sdk.webview;

import android.webkit.WebView;
import com.mobfox.sdk.runnables.WebViewRunnable;
import com.mobfox.sdk.webview.MobFoxWebViewClient.Listener;

class MobFoxWebView$12 implements Listener {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$12(MobFoxWebView this$0, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
    }

    public void onClick(String url) {
        final String str = url;
        this.this$0.mainHandler.post(new WebViewRunnable(this.this$0.context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$12.this.this$0.renderAdListener.onAdClick(MobFoxWebView$12.this.val$self, str);
            }
        });
    }

    public void onError(Exception e) {
        final Exception exception = e;
        this.this$0.mainHandler.post(new WebViewRunnable(this.this$0.context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$12.this.this$0.renderAdListener.onError(MobFoxWebView$12.this.val$self, exception);
            }
        });
    }

    public void onAutoRedirect(WebView view, String url) {
        final String str = url;
        this.this$0.mainHandler.post(new WebViewRunnable(this.this$0.context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$12.this.this$0.renderAdListener.onAutoRedirect(MobFoxWebView$12.this.val$self, str);
            }
        });
    }
}
