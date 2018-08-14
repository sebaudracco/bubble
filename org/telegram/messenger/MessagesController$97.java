package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputUserSelf;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.tgnet.TLRPC.User;

class MessagesController$97 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ int val$chat_id;
    final /* synthetic */ TLRPC$InputUser val$inputUser;
    final /* synthetic */ boolean val$isChannel;
    final /* synthetic */ User val$user;

    class C50551 implements Runnable {
        C50551() {
        }

        public void run() {
            MessagesController$97.this.this$0.deleteDialog((long) (-MessagesController$97.this.val$chat_id), 0);
        }
    }

    class C50562 implements Runnable {
        C50562() {
        }

        public void run() {
            MessagesController$97.this.this$0.loadFullChat(MessagesController$97.this.val$chat_id, 0, true);
        }
    }

    MessagesController$97(MessagesController this$0, User user, int i, boolean z, TLRPC$InputUser tLRPC$InputUser) {
        this.this$0 = this$0;
        this.val$user = user;
        this.val$chat_id = i;
        this.val$isChannel = z;
        this.val$inputUser = tLRPC$InputUser;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (this.val$user.id == UserConfig.getClientUserId()) {
            AndroidUtilities.runOnUIThread(new C50551());
        }
        if (error == null) {
            this.this$0.processUpdates((TLRPC$Updates) response, false);
            if (this.val$isChannel && !(this.val$inputUser instanceof TLRPC$TL_inputUserSelf)) {
                AndroidUtilities.runOnUIThread(new C50562(), 1000);
            }
        }
    }
}
