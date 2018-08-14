package com.mobfox.sdk.webview;

import android.util.Log;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mobfox.sdk.constants.Constants;

class MobFoxWebView$5 implements BridgeHandler {
    final /* synthetic */ MobFoxWebView this$0;

    MobFoxWebView$5(MobFoxWebView this$0) {
        this.this$0 = this$0;
    }

    public void handler(String data, CallBackFunction function) {
        Log.d(Constants.MOBFOX_WEBVIEW, "ready");
        this.this$0.ready = true;
    }
}
