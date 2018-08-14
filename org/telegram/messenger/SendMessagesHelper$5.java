package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.AlertsCreator;

class SendMessagesHelper$5 implements RequestDelegate {
    final /* synthetic */ SendMessagesHelper this$0;
    final /* synthetic */ Runnable val$callback;
    final /* synthetic */ BaseFragment val$fragment;
    final /* synthetic */ TLRPC$TL_messages_editMessage val$req;

    class C51901 implements Runnable {
        C51901() {
        }

        public void run() {
            SendMessagesHelper$5.this.val$callback.run();
        }
    }

    SendMessagesHelper$5(SendMessagesHelper this$0, Runnable runnable, BaseFragment baseFragment, TLRPC$TL_messages_editMessage tLRPC$TL_messages_editMessage) {
        this.this$0 = this$0;
        this.val$callback = runnable;
        this.val$fragment = baseFragment;
        this.val$req = tLRPC$TL_messages_editMessage;
    }

    public void run(TLObject response, final TLRPC$TL_error error) {
        AndroidUtilities.runOnUIThread(new C51901());
        if (error == null) {
            MessagesController.getInstance().processUpdates((TLRPC$Updates) response, false);
        } else {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    AlertsCreator.processError(error, SendMessagesHelper$5.this.val$fragment, SendMessagesHelper$5.this.val$req, new Object[0]);
                }
            });
        }
    }
}
