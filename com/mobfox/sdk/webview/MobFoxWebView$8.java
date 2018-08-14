package com.mobfox.sdk.webview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class MobFoxWebView$8 implements OnTouchListener {
    final /* synthetic */ MobFoxWebView this$0;

    MobFoxWebView$8(MobFoxWebView this$0) {
        this.this$0 = this$0;
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.this$0.userInteraction = true;
        return false;
    }
}
