package com.mobfox.sdk.webview;

import android.content.Context;
import com.mobfox.sdk.runnables.WebViewRunnable;

class MobFoxWebView$10 extends WebViewRunnable {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Exception val$e;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$10(MobFoxWebView this$0, Context context, MobFoxWebView wv, String memberName, MobFoxWebView mobFoxWebView, Exception exception) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
        this.val$e = exception;
        super(context, wv, memberName);
    }

    public void mobFoxRun() {
        this.val$self.loadAdListener.onError(this.val$self, this.val$e);
    }
}
