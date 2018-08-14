package com.vungle.publisher;

import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.vungle.publisher.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class rb {
    void m4596a(WebView webView, String str, String... strArr) {
        m4597a("window.vungle.mraidBridgeExt", webView, str, strArr);
    }

    void m4598b(WebView webView, String str, String... strArr) {
        m4597a("window.vungle.mraidBridge", webView, str, strArr);
    }

    void m4597a(String str, WebView webView, String str2, String... strArr) {
        m4595a(webView, str + "." + str2 + "(" + zk.a(",", strArr) + ")");
    }

    void m4595a(WebView webView, String str) {
        Logger.v(Logger.AD_TAG, "load javascript: " + str);
        webView.loadUrl(BridgeUtil.JAVASCRIPT_STR + str);
    }
}
