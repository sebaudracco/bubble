package org.telegram.messenger;

class MessagesController$43 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ long val$did;

    class C50181 implements Runnable {
        C50181() {
        }

        public void run() {
            NotificationsController.getInstance().removeNotificationsForDialog(MessagesController$43.this.val$did);
        }
    }

    MessagesController$43(MessagesController this$0, long j) {
        this.this$0 = this$0;
        this.val$did = j;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C50181());
    }
}
