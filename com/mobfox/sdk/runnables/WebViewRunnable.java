package com.mobfox.sdk.runnables;

import android.content.Context;
import com.mobfox.sdk.webview.MobFoxWebView;

public abstract class WebViewRunnable extends MobFoxRunnable {
    public static String CONNECTION_EXCEPTION = "Unable to connect to: //my.mobfox.com/request.php";
    String memberName;
    MobFoxWebView wv;

    public WebViewRunnable(Context context, MobFoxWebView wv, String memberName) {
        super(context);
        this.wv = wv;
        this.memberName = memberName;
    }

    protected boolean condition() {
        try {
            if (MobFoxWebView.class.getDeclaredField(this.memberName) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
