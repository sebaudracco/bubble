package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_error;

class MessagesController$103 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ String val$regid;

    class C49691 implements Runnable {
        C49691() {
        }

        public void run() {
            MessagesController$103.this.this$0.registeringForPush = false;
        }
    }

    MessagesController$103(MessagesController this$0, String str) {
        this.this$0 = this$0;
        this.val$regid = str;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (response instanceof TLRPC$TL_boolTrue) {
            FileLog.e("registered for push");
            UserConfig.registeredForPush = true;
            UserConfig.pushString = this.val$regid;
            UserConfig.saveConfig(false);
        }
        AndroidUtilities.runOnUIThread(new C49691());
    }
}
