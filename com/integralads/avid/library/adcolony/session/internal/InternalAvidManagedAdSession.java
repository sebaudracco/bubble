package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidTrackingWebViewManager;

public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager f8363a = new AvidTrackingWebViewManager(this.f8364b);
    private final WebView f8364b;

    public InternalAvidManagedAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
        this.f8364b = new WebView(context.getApplicationContext());
    }

    public WebView getWebView() {
        return this.f8364b;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.f8363a;
    }

    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.f8363a.loadHTML();
    }

    @VisibleForTesting
    void m11144a(AvidTrackingWebViewManager avidTrackingWebViewManager) {
        this.f8363a = avidTrackingWebViewManager;
    }
}
