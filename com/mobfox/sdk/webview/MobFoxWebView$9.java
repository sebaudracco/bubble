package com.mobfox.sdk.webview;

import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.networking.AsyncCallback;
import java.util.List;
import java.util.Map;

class MobFoxWebView$9 implements AsyncCallback {
    final /* synthetic */ MobFoxWebView this$0;

    MobFoxWebView$9(MobFoxWebView this$0) {
        this.this$0 = this$0;
    }

    public void onComplete(int code, Object response, Map<String, List<String>> map) {
        Log.d(Constants.MOBFOX_GRAYLOG, "complete");
    }

    public void onError(Exception e) {
        Log.d(Constants.MOBFOX_GRAYLOG, "incomplete");
    }
}
