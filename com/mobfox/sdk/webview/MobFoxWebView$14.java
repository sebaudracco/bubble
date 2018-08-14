package com.mobfox.sdk.webview;

class MobFoxWebView$14 implements Runnable {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$14(MobFoxWebView this$0, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
    }

    public void run() {
        this.val$self.removeAllViews();
    }
}
