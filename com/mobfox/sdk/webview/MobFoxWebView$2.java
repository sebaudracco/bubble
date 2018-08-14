package com.mobfox.sdk.webview;

import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mobfox.sdk.runnables.WebViewRunnable;

class MobFoxWebView$2 implements BridgeHandler {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$2(MobFoxWebView this$0, Context context, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$context = context;
        this.val$self = mobFoxWebView;
    }

    public void handler(String url, CallBackFunction function) {
        final String str = url;
        this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$2.this.this$0.renderAdListener.onAdClick(MobFoxWebView$2.this.val$self, str);
            }
        });
    }
}
