package com.mobfox.sdk.webview;

import android.content.Context;
import com.mobfox.sdk.runnables.WebViewRunnable;
import org.json.JSONObject;

class MobFoxWebView$16 extends WebViewRunnable {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ JSONObject val$adResp;

    MobFoxWebView$16(MobFoxWebView this$0, Context context, MobFoxWebView wv, String memberName, JSONObject jSONObject) {
        this.this$0 = this$0;
        this.val$adResp = jSONObject;
        super(context, wv, memberName);
    }

    public void mobFoxRun() {
        this.this$0.renderAd(this.val$adResp);
    }
}
