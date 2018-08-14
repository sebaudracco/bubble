package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.mopub.session.internal.trackingwebview.AvidTrackingWebViewManager;

public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager trackingWebViewManager = new AvidTrackingWebViewManager(this.webView);
    private final WebView webView;

    public InternalAvidManagedAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
        this.webView = new WebView(context.getApplicationContext());
    }

    public WebView getWebView() {
        return this.webView;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.trackingWebViewManager;
    }

    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.trackingWebViewManager.loadHTML();
    }

    @VisibleForTesting
    void setTrackingWebViewManager(AvidTrackingWebViewManager trackingWebViewManager) {
        this.trackingWebViewManager = trackingWebViewManager;
    }
}
