package com.mobfox.sdk.webview;

import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mobfox.sdk.runnables.WebViewRunnable;

class MobFoxWebView$4 implements BridgeHandler {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$4(MobFoxWebView this$0, Context context, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$context = context;
        this.val$self = mobFoxWebView;
    }

    public void handler(String data, CallBackFunction function) {
        this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.RENDER_AD_LISTENER) {
            public void mobFoxRun() {
                MobFoxWebView$4.this.this$0.renderAdListener.onVideoAdFinished(MobFoxWebView$4.this.val$self);
            }
        });
    }
}
