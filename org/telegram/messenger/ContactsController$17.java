package org.telegram.messenger;

class ContactsController$17 implements Runnable {
    final /* synthetic */ ContactsController this$0;
    final /* synthetic */ Integer val$uid;

    ContactsController$17(ContactsController this$0, Integer num) {
        this.this$0 = this$0;
        this.val$uid = num;
    }

    public void run() {
        ContactsController.access$1900(this.this$0, this.val$uid.intValue());
    }
}
