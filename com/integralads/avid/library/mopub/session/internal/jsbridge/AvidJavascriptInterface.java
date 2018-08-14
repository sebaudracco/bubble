package com.integralads.avid.library.mopub.session.internal.jsbridge;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;

public class AvidJavascriptInterface {
    public static final String AVID_OBJECT = "avid";
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private AvidJavascriptInterfaceCallback callback;
    private final Handler handler = new Handler();

    public AvidJavascriptInterface(InternalAvidAdSessionContext avidAdSessionContext) {
        this.avidAdSessionContext = avidAdSessionContext;
    }

    public AvidJavascriptInterfaceCallback getCallback() {
        return this.callback;
    }

    public void setCallback(AvidJavascriptInterfaceCallback callback) {
        this.callback = callback;
    }

    @JavascriptInterface
    public String getAvidAdSessionContext() {
        this.handler.post(new CallbackRunnable(this));
        return this.avidAdSessionContext.getStubContext().toString();
    }
}
