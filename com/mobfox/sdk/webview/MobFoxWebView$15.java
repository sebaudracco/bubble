package com.mobfox.sdk.webview;

import com.github.lzyzsd.jsbridge.CallBackFunction;

class MobFoxWebView$15 implements CallBackFunction {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$15(MobFoxWebView this$0, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
    }

    public void onCallBack(String data) {
        this.val$self.callHandler("loadAd", this.val$self.options, null);
    }
}
