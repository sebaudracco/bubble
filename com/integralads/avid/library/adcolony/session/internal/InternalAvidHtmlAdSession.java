package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;

public abstract class InternalAvidHtmlAdSession extends InternalAvidAdSession<WebView> {
    public InternalAvidHtmlAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
    }

    public WebView getWebView() {
        return (WebView) getView();
    }

    protected void onViewRegistered() {
        super.onViewRegistered();
        updateWebViewManager();
    }

    protected void onViewUnregistered() {
        super.onViewUnregistered();
        updateWebViewManager();
    }
}
