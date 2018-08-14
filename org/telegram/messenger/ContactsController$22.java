package org.telegram.messenger;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC.User;

class ContactsController$22 implements RequestDelegate {
    final /* synthetic */ ContactsController this$0;
    final /* synthetic */ ArrayList val$uids;
    final /* synthetic */ ArrayList val$users;

    class C48331 implements Runnable {
        C48331() {
        }

        public void run() {
            Iterator it = ContactsController$22.this.val$users.iterator();
            while (it.hasNext()) {
                ContactsController.access$1900(ContactsController$22.this.this$0, ((User) it.next()).id);
            }
        }
    }

    class C48342 implements Runnable {
        C48342() {
        }

        public void run() {
            boolean remove = false;
            Iterator it = ContactsController$22.this.val$users.iterator();
            while (it.hasNext()) {
                User user = (User) it.next();
                TLRPC$TL_contact contact = (TLRPC$TL_contact) ContactsController$22.this.this$0.contactsDict.get(Integer.valueOf(user.id));
                if (contact != null) {
                    remove = true;
                    ContactsController$22.this.this$0.contacts.remove(contact);
                    ContactsController$22.this.this$0.contactsDict.remove(Integer.valueOf(user.id));
                }
            }
            if (remove) {
                ContactsController.access$2000(ContactsController$22.this.this$0, false);
            }
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.updateInterfaces, Integer.valueOf(1));
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
        }
    }

    ContactsController$22(ContactsController this$0, ArrayList arrayList, ArrayList arrayList2) {
        this.this$0 = this$0;
        this.val$uids = arrayList;
        this.val$users = arrayList2;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            MessagesStorage.getInstance().deleteContacts(this.val$uids);
            Utilities.phoneBookQueue.postRunnable(new C48331());
            for (int a = 0; a < this.val$users.size(); a++) {
                User user = (User) this.val$users.get(a);
                if (!TextUtils.isEmpty(user.phone)) {
                    CharSequence name = UserObject.getUserName(user);
                    MessagesStorage.getInstance().applyPhoneBookUpdates(user.phone, "");
                    ContactsController$Contact contact = (ContactsController$Contact) this.this$0.contactsBookSPhones.get(user.phone);
                    if (contact != null) {
                        int index = contact.shortPhones.indexOf(user.phone);
                        if (index != -1) {
                            contact.phoneDeleted.set(index, Integer.valueOf(1));
                        }
                    }
                }
            }
            AndroidUtilities.runOnUIThread(new C48342());
        }
    }
}
