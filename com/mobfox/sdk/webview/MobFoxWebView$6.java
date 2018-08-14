package com.mobfox.sdk.webview;

import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mobfox.sdk.runnables.WebViewRunnable;

class MobFoxWebView$6 implements BridgeHandler {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$6(MobFoxWebView this$0, Context context, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$context = context;
        this.val$self = mobFoxWebView;
    }

    public void handler(String error, CallBackFunction function) {
        final String str = error;
        this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$6.this.this$0.renderAdListener.onError(MobFoxWebView$6.this.val$self, new Exception(str));
            }
        });
        this.this$0.loadAd(this.this$0.options);
    }
}
