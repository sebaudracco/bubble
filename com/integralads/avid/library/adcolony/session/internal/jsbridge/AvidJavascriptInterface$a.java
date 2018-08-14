package com.integralads.avid.library.adcolony.session.internal.jsbridge;

class AvidJavascriptInterface$a implements Runnable {
    final /* synthetic */ AvidJavascriptInterface f8380a;

    AvidJavascriptInterface$a(AvidJavascriptInterface avidJavascriptInterface) {
        this.f8380a = avidJavascriptInterface;
    }

    public void run() {
        if (AvidJavascriptInterface.a(this.f8380a) != null) {
            AvidJavascriptInterface.a(this.f8380a).onAvidAdSessionContextInvoked();
            AvidJavascriptInterface.a(this.f8380a, null);
        }
    }
}
