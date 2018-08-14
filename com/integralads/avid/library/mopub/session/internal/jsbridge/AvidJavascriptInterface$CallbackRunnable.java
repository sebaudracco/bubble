package com.integralads.avid.library.mopub.session.internal.jsbridge;

class AvidJavascriptInterface$CallbackRunnable implements Runnable {
    final /* synthetic */ AvidJavascriptInterface this$0;

    AvidJavascriptInterface$CallbackRunnable(AvidJavascriptInterface this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (AvidJavascriptInterface.access$000(this.this$0) != null) {
            AvidJavascriptInterface.access$000(this.this$0).onAvidAdSessionContextInvoked();
            AvidJavascriptInterface.access$002(this.this$0, null);
        }
    }
}
