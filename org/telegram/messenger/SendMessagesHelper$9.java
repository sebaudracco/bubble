package org.telegram.messenger;

class SendMessagesHelper$9 implements Runnable {
    final /* synthetic */ SendMessagesHelper this$0;
    final /* synthetic */ String val$path;

    class C51941 implements Runnable {
        C51941() {
        }

        public void run() {
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.stopEncodingService, SendMessagesHelper$9.this.val$path);
        }
    }

    SendMessagesHelper$9(SendMessagesHelper this$0, String str) {
        this.this$0 = this$0;
        this.val$path = str;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C51941());
    }
}
