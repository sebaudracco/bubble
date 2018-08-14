package org.telegram.messenger;

import java.util.ArrayList;

class MessagesController$127 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ ArrayList val$pushMessages;

    class C49961 implements Runnable {
        C49961() {
        }

        public void run() {
            NotificationsController.getInstance().processNewMessages(MessagesController$127.this.val$pushMessages, true);
        }
    }

    MessagesController$127(MessagesController this$0, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$pushMessages = arrayList;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C49961());
    }
}
