package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_contacts_contactsNotModified;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$contacts_Contacts;

class ContactsController$11 implements RequestDelegate {
    final /* synthetic */ ContactsController this$0;
    final /* synthetic */ int val$hash;

    class C48221 implements Runnable {
        C48221() {
        }

        public void run() {
            synchronized (ContactsController.access$500()) {
                ContactsController.access$602(ContactsController$11.this.this$0, false);
            }
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
        }
    }

    ContactsController$11(ContactsController this$0, int i) {
        this.this$0 = this$0;
        this.val$hash = i;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            TLRPC$contacts_Contacts res = (TLRPC$contacts_Contacts) response;
            if (this.val$hash == 0 || !(res instanceof TLRPC$TL_contacts_contactsNotModified)) {
                UserConfig.contactsSavedCount = res.saved_count;
                UserConfig.saveConfig(false);
                this.this$0.processLoadedContacts(res.contacts, res.users, 0);
                return;
            }
            this.this$0.contactsLoaded = true;
            if (!ContactsController.access$1000(this.this$0).isEmpty() && ContactsController.access$900(this.this$0)) {
                ContactsController.access$1100(this.this$0, ContactsController.access$1000(this.this$0), null, null, null);
                ContactsController.access$1000(this.this$0).clear();
            }
            UserConfig.lastContactsSyncTime = (int) (System.currentTimeMillis() / 1000);
            UserConfig.saveConfig(false);
            AndroidUtilities.runOnUIThread(new C48221());
            FileLog.e("load contacts don't change");
        }
    }
}
