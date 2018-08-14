package com.mobfox.sdk.webview;

import com.github.lzyzsd.jsbridge.CallBackFunction;

class MobFoxWebView$17 implements CallBackFunction {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$17(MobFoxWebView this$0, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
    }

    public void onCallBack(String data) {
        if (data == null) {
            try {
                data = "";
            } catch (Throwable th) {
                return;
            }
        }
        if (data == "null") {
            data = "";
        }
        if (this.val$self.renderAdListener != null) {
            this.this$0.renderAdListener.onRendered(this.val$self, data);
        }
    }
}
