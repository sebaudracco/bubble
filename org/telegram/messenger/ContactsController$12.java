package org.telegram.messenger;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC.User;

class ContactsController$12 implements Runnable {
    final /* synthetic */ ContactsController this$0;
    final /* synthetic */ ArrayList val$contactsArr;
    final /* synthetic */ int val$from;
    final /* synthetic */ ArrayList val$usersArr;

    ContactsController$12(ContactsController this$0, ArrayList arrayList, int i, ArrayList arrayList2) {
        this.this$0 = this$0;
        this.val$usersArr = arrayList;
        this.val$from = i;
        this.val$contactsArr = arrayList2;
    }

    public void run() {
        int a;
        boolean z = true;
        MessagesController instance = MessagesController.getInstance();
        ArrayList arrayList = this.val$usersArr;
        if (this.val$from != 1) {
            z = false;
        }
        instance.putUsers(arrayList, z);
        final HashMap<Integer, User> usersDict = new HashMap();
        final boolean isEmpty = this.val$contactsArr.isEmpty();
        if (!this.this$0.contacts.isEmpty()) {
            a = 0;
            while (a < this.val$contactsArr.size()) {
                if (this.this$0.contactsDict.get(Integer.valueOf(((TLRPC$TL_contact) this.val$contactsArr.get(a)).user_id)) != null) {
                    this.val$contactsArr.remove(a);
                    a--;
                }
                a++;
            }
            this.val$contactsArr.addAll(this.this$0.contacts);
        }
        for (a = 0; a < this.val$contactsArr.size(); a++) {
            User user = MessagesController.getInstance().getUser(Integer.valueOf(((TLRPC$TL_contact) this.val$contactsArr.get(a)).user_id));
            if (user != null) {
                usersDict.put(Integer.valueOf(user.id), user);
            }
        }
        Utilities.stageQueue.postRunnable(new Runnable() {

            class C48231 implements Comparator<TLRPC$TL_contact> {
                C48231() {
                }

                public int compare(TLRPC$TL_contact tl_contact, TLRPC$TL_contact tl_contact2) {
                    return UserObject.getFirstName((User) usersDict.get(Integer.valueOf(tl_contact.user_id))).compareTo(UserObject.getFirstName((User) usersDict.get(Integer.valueOf(tl_contact2.user_id))));
                }
            }

            class C48242 implements Comparator<String> {
                C48242() {
                }

                public int compare(String s, String s2) {
                    char cv1 = s.charAt(0);
                    char cv2 = s2.charAt(0);
                    if (cv1 == '#') {
                        return 1;
                    }
                    if (cv2 == '#') {
                        return -1;
                    }
                    return s.compareTo(s2);
                }
            }

            class C48253 implements Comparator<String> {
                C48253() {
                }

                public int compare(String s, String s2) {
                    char cv1 = s.charAt(0);
                    char cv2 = s2.charAt(0);
                    if (cv1 == '#') {
                        return 1;
                    }
                    if (cv2 == '#') {
                        return -1;
                    }
                    return s.compareTo(s2);
                }
            }

            public void run() {
                FileLog.e("done loading contacts");
                if (ContactsController$12.this.val$from == 1 && (ContactsController$12.this.val$contactsArr.isEmpty() || Math.abs((System.currentTimeMillis() / 1000) - ((long) UserConfig.lastContactsSyncTime)) >= 86400)) {
                    ContactsController$12.this.this$0.loadContacts(false, ContactsController.access$1300(ContactsController$12.this.this$0, ContactsController$12.this.val$contactsArr));
                    if (ContactsController$12.this.val$contactsArr.isEmpty()) {
                        return;
                    }
                }
                if (ContactsController$12.this.val$from == 0) {
                    UserConfig.lastContactsSyncTime = (int) (System.currentTimeMillis() / 1000);
                    UserConfig.saveConfig(false);
                }
                int a = 0;
                while (a < ContactsController$12.this.val$contactsArr.size()) {
                    TLRPC$TL_contact contact = (TLRPC$TL_contact) ContactsController$12.this.val$contactsArr.get(a);
                    if (usersDict.get(Integer.valueOf(contact.user_id)) != null || contact.user_id == UserConfig.getClientUserId()) {
                        a++;
                    } else {
                        ContactsController$12.this.this$0.loadContacts(false, 0);
                        FileLog.e("contacts are broken, load from server");
                        return;
                    }
                }
                if (ContactsController$12.this.val$from != 1) {
                    MessagesStorage.getInstance().putUsersAndChats(ContactsController$12.this.val$usersArr, null, true, true);
                    MessagesStorage.getInstance().putContacts(ContactsController$12.this.val$contactsArr, ContactsController$12.this.val$from != 2);
                }
                Collections.sort(ContactsController$12.this.val$contactsArr, new C48231());
                final ConcurrentHashMap<Integer, TLRPC$TL_contact> contactsDictionary = new ConcurrentHashMap(20, 1.0f, 2);
                final HashMap<String, ArrayList<TLRPC$TL_contact>> sectionsDict = new HashMap();
                final HashMap<String, ArrayList<TLRPC$TL_contact>> sectionsDictMutual = new HashMap();
                final ArrayList<String> sortedSectionsArray = new ArrayList();
                final ArrayList<String> sortedSectionsArrayMutual = new ArrayList();
                HashMap<String, TLRPC$TL_contact> contactsByPhonesDict = null;
                HashMap<String, TLRPC$TL_contact> contactsByPhonesShortDict = null;
                if (!ContactsController.access$900(ContactsController$12.this.this$0)) {
                    contactsByPhonesDict = new HashMap();
                    contactsByPhonesShortDict = new HashMap();
                }
                final HashMap<String, TLRPC$TL_contact> contactsByPhonesDictFinal = contactsByPhonesDict;
                HashMap<String, TLRPC$TL_contact> contactsByPhonesShortDictFinal = contactsByPhonesShortDict;
                for (a = 0; a < ContactsController$12.this.val$contactsArr.size(); a++) {
                    TLRPC$TL_contact value = (TLRPC$TL_contact) ContactsController$12.this.val$contactsArr.get(a);
                    User user = (User) usersDict.get(Integer.valueOf(value.user_id));
                    if (user != null) {
                        contactsDictionary.put(Integer.valueOf(value.user_id), value);
                        if (!(contactsByPhonesDict == null || TextUtils.isEmpty(user.phone))) {
                            contactsByPhonesDict.put(user.phone, value);
                            contactsByPhonesShortDict.put(user.phone.substring(Math.max(0, user.phone.length() - 7)), value);
                        }
                        String key = UserObject.getFirstName(user);
                        if (key.length() > 1) {
                            key = key.substring(0, 1);
                        }
                        if (key.length() == 0) {
                            key = "#";
                        } else {
                            key = key.toUpperCase();
                        }
                        String replace = (String) ContactsController.access$1400(ContactsController$12.this.this$0).get(key);
                        if (replace != null) {
                            key = replace;
                        }
                        ArrayList<TLRPC$TL_contact> arr = (ArrayList) sectionsDict.get(key);
                        if (arr == null) {
                            arr = new ArrayList();
                            sectionsDict.put(key, arr);
                            sortedSectionsArray.add(key);
                        }
                        arr.add(value);
                        if (user.mutual_contact) {
                            arr = (ArrayList) sectionsDictMutual.get(key);
                            if (arr == null) {
                                arr = new ArrayList();
                                sectionsDictMutual.put(key, arr);
                                sortedSectionsArrayMutual.add(key);
                            }
                            arr.add(value);
                        }
                    }
                }
                Collections.sort(sortedSectionsArray, new C48242());
                Collections.sort(sortedSectionsArrayMutual, new C48253());
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        ContactsController$12.this.this$0.contacts = ContactsController$12.this.val$contactsArr;
                        ContactsController$12.this.this$0.contactsDict = contactsDictionary;
                        ContactsController$12.this.this$0.usersSectionsDict = sectionsDict;
                        ContactsController$12.this.this$0.usersMutualSectionsDict = sectionsDictMutual;
                        ContactsController$12.this.this$0.sortedUsersSectionsArray = sortedSectionsArray;
                        ContactsController$12.this.this$0.sortedUsersMutualSectionsArray = sortedSectionsArrayMutual;
                        if (ContactsController$12.this.val$from != 2) {
                            synchronized (ContactsController.access$500()) {
                                ContactsController.access$602(ContactsController$12.this.this$0, false);
                            }
                        }
                        ContactsController.access$1500(ContactsController$12.this.this$0);
                        ContactsController.access$1200(ContactsController$12.this.this$0, ContactsController$12.this.val$contactsArr);
                        NotificationCenter.getInstance().postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                        if (ContactsController$12.this.val$from == 1 || isEmpty) {
                            ContactsController.access$1700(ContactsController$12.this.this$0);
                        } else {
                            ContactsController.access$1600(ContactsController$12.this.this$0);
                        }
                    }
                });
                if (!ContactsController.access$1000(ContactsController$12.this.this$0).isEmpty() && ContactsController$12.this.this$0.contactsLoaded && ContactsController.access$900(ContactsController$12.this.this$0)) {
                    ContactsController.access$1100(ContactsController$12.this.this$0, ContactsController.access$1000(ContactsController$12.this.this$0), null, null, null);
                    ContactsController.access$1000(ContactsController$12.this.this$0).clear();
                }
                if (contactsByPhonesDictFinal != null) {
                    final HashMap<String, TLRPC$TL_contact> hashMap = contactsByPhonesShortDictFinal;
                    AndroidUtilities.runOnUIThread(new Runnable() {

                        class C48271 implements Runnable {
                            C48271() {
                            }

                            public void run() {
                                ContactsController$12.this.this$0.contactsByPhone = contactsByPhonesDictFinal;
                                ContactsController$12.this.this$0.contactsByShortPhone = hashMap;
                            }
                        }

                        public void run() {
                            Utilities.globalQueue.postRunnable(new C48271());
                            if (!ContactsController.access$800(ContactsController$12.this.this$0)) {
                                ContactsController.access$802(ContactsController$12.this.this$0, true);
                                MessagesStorage.getInstance().getCachedPhoneBook(false);
                            }
                        }
                    });
                    return;
                }
                ContactsController$12.this.this$0.contactsLoaded = true;
            }
        });
    }
}
