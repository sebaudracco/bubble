package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.support.annotation.VisibleForTesting;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;

public class AvidWebViewManager implements AvidJavascriptInterface$AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext f8381a;
    private final AvidWebView f8382b = new AvidWebView(null);
    private final AvidBridgeManager f8383c;
    private AvidJavascriptInterface f8384d;

    public AvidWebViewManager(InternalAvidAdSessionContext avidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.f8381a = avidAdSessionContext;
        this.f8383c = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.f8382b.get() != webView) {
            this.f8383c.setWebView(null);
            m11151b();
            this.f8382b.set(webView);
            if (webView != null) {
                this.f8384d = new AvidJavascriptInterface(this.f8381a);
                this.f8384d.setCallback(this);
                webView.addJavascriptInterface(this.f8384d, "avid");
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void m11151b() {
        if (this.f8384d != null) {
            this.f8384d.setCallback(null);
            this.f8384d = null;
        }
    }

    public void onAvidAdSessionContextInvoked() {
        this.f8383c.setWebView((WebView) this.f8382b.get());
    }

    @VisibleForTesting
    AvidJavascriptInterface m11152a() {
        return this.f8384d;
    }
}
