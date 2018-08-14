package com.mobfox.sdk.webview;

import android.os.Handler;

class MobFoxWebView$13 implements Runnable {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Handler val$h;
    final /* synthetic */ String val$options;

    MobFoxWebView$13(MobFoxWebView this$0, Handler handler, String str) {
        this.this$0 = this$0;
        this.val$h = handler;
        this.val$options = str;
    }

    public void run() {
        if (!this.this$0.ready || this.this$0.waterfalls == null) {
            this.val$h.postDelayed(this, 50);
        } else {
            MobFoxWebView.access$000(this.this$0, this.val$options);
        }
    }
}
