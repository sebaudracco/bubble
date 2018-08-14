package com.mopub.common.util;

import com.github.lzyzsd.jsbridge.BridgeUtil;

public enum JavaScriptWebViewCallbacks {
    WEB_VIEW_DID_APPEAR("webviewDidAppear();"),
    WEB_VIEW_DID_CLOSE("webviewDidClose();");
    
    private String mJavascript;

    private JavaScriptWebViewCallbacks(String javascript) {
        this.mJavascript = javascript;
    }

    public String getJavascript() {
        return this.mJavascript;
    }

    public String getUrl() {
        return BridgeUtil.JAVASCRIPT_STR + this.mJavascript;
    }
}
