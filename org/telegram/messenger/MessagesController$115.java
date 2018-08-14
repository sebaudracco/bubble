package org.telegram.messenger;

import java.util.ArrayList;

class MessagesController$115 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ ArrayList val$pushMessages;

    class C49881 implements Runnable {
        C49881() {
        }

        public void run() {
            NotificationsController.getInstance().processNewMessages(MessagesController$115.this.val$pushMessages, true);
        }
    }

    MessagesController$115(MessagesController this$0, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$pushMessages = arrayList;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C49881());
    }
}
