package org.telegram.messenger;

import org.telegram.tgnet.QuickAckDelegate;
import org.telegram.tgnet.TLRPC$Message;

class SendMessagesHelper$12 implements QuickAckDelegate {
    final /* synthetic */ SendMessagesHelper this$0;
    final /* synthetic */ TLRPC$Message val$newMsgObj;

    SendMessagesHelper$12(SendMessagesHelper this$0, TLRPC$Message tLRPC$Message) {
        this.this$0 = this$0;
        this.val$newMsgObj = tLRPC$Message;
    }

    public void run() {
        final int msg_id = this.val$newMsgObj.id;
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                SendMessagesHelper$12.this.val$newMsgObj.send_state = 0;
                NotificationCenter.getInstance().postNotificationName(NotificationCenter.messageReceivedByAck, Integer.valueOf(msg_id));
            }
        });
    }
}
