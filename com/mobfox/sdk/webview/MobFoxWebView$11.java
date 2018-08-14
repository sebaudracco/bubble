package com.mobfox.sdk.webview;

import android.content.Context;
import com.mobfox.sdk.runnables.WebViewRunnable;

class MobFoxWebView$11 extends WebViewRunnable {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ MobFoxWebView val$self;
    final /* synthetic */ Throwable val$t;

    MobFoxWebView$11(MobFoxWebView this$0, Context context, MobFoxWebView wv, String memberName, MobFoxWebView mobFoxWebView, Throwable th) {
        this.this$0 = this$0;
        this.val$self = mobFoxWebView;
        this.val$t = th;
        super(context, wv, memberName);
    }

    public void mobFoxRun() {
        this.val$self.loadAdListener.onError(this.val$self, new Exception(this.val$t.getMessage()));
    }
}
