package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;

public class AvidJavascriptInterface {
    public static final String AVID_OBJECT = "avid";
    private final InternalAvidAdSessionContext f2654a;
    private final Handler f2655b = new Handler();
    private AvidJavascriptInterfaceCallback f2656c;

    public AvidJavascriptInterface(InternalAvidAdSessionContext avidAdSessionContext) {
        this.f2654a = avidAdSessionContext;
    }

    public AvidJavascriptInterfaceCallback getCallback() {
        return this.f2656c;
    }

    public void setCallback(AvidJavascriptInterfaceCallback callback) {
        this.f2656c = callback;
    }

    @JavascriptInterface
    public String getAvidAdSessionContext() {
        this.f2655b.post(new a(this));
        return this.f2654a.getStubContext().toString();
    }
}
