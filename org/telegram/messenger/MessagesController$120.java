package org.telegram.messenger;

import java.util.ArrayList;

class MessagesController$120 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ ArrayList val$objArr;

    class C49941 implements Runnable {
        C49941() {
        }

        public void run() {
            NotificationsController.getInstance().processNewMessages(MessagesController$120.this.val$objArr, true);
        }
    }

    MessagesController$120(MessagesController this$0, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$objArr = arrayList;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C49941());
    }
}
