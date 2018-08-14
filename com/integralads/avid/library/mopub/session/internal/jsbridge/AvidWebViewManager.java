package com.integralads.avid.library.mopub.session.internal.jsbridge;

import android.support.annotation.VisibleForTesting;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.weakreference.AvidWebView;

public class AvidWebViewManager implements AvidJavascriptInterface$AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private final AvidBridgeManager avidBridgeManager;
    private final AvidWebView avidWebView = new AvidWebView(null);
    private AvidJavascriptInterface javascriptInterface;

    public AvidWebViewManager(InternalAvidAdSessionContext avidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.avidAdSessionContext = avidAdSessionContext;
        this.avidBridgeManager = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() != webView) {
            this.avidBridgeManager.setWebView(null);
            clearJavascriptInterface();
            this.avidWebView.set(webView);
            if (webView != null) {
                this.javascriptInterface = new AvidJavascriptInterface(this.avidAdSessionContext);
                this.javascriptInterface.setCallback(this);
                webView.addJavascriptInterface(this.javascriptInterface, "avid");
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void clearJavascriptInterface() {
        if (this.javascriptInterface != null) {
            this.javascriptInterface.setCallback(null);
            this.javascriptInterface = null;
        }
    }

    public void onAvidAdSessionContextInvoked() {
        this.avidBridgeManager.setWebView((WebView) this.avidWebView.get());
    }

    @VisibleForTesting
    AvidJavascriptInterface getJavascriptInterface() {
        return this.javascriptInterface;
    }
}
