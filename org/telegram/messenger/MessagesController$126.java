package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateServiceNotification;

class MessagesController$126 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ TLRPC$TL_updateServiceNotification val$notification;

    MessagesController$126(MessagesController this$0, TLRPC$TL_updateServiceNotification tLRPC$TL_updateServiceNotification) {
        this.this$0 = this$0;
        this.val$notification = tLRPC$TL_updateServiceNotification;
    }

    public void run() {
        NotificationCenter.getInstance().postNotificationName(NotificationCenter.needShowAlert, Integer.valueOf(2), this.val$notification.message);
    }
}
