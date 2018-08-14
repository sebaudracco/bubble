package org.telegram.messenger;

import android.content.SharedPreferences.Editor;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.telegram.tgnet.TLRPC$TL_dialog;

class MessagesController$129 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ SparseArray val$clearHistoryMessages;
    final /* synthetic */ SparseArray val$deletedMessages;
    final /* synthetic */ HashMap val$markAsReadEncrypted;
    final /* synthetic */ ArrayList val$markAsReadMessages;
    final /* synthetic */ SparseArray val$markAsReadMessagesInbox;
    final /* synthetic */ SparseArray val$markAsReadMessagesOutbox;

    class C50001 implements Runnable {
        C50001() {
        }

        public void run() {
            int b;
            int key;
            MessageObject obj;
            int a;
            int updateMask = 0;
            if (!(MessagesController$129.this.val$markAsReadMessagesInbox.size() == 0 && MessagesController$129.this.val$markAsReadMessagesOutbox.size() == 0)) {
                int messageId;
                TLRPC$TL_dialog dialog;
                NotificationCenter.getInstance().postNotificationName(NotificationCenter.messagesRead, MessagesController$129.this.val$markAsReadMessagesInbox, MessagesController$129.this.val$markAsReadMessagesOutbox);
                NotificationsController.getInstance().processReadMessages(MessagesController$129.this.val$markAsReadMessagesInbox, 0, 0, 0, false);
                if (MessagesController$129.this.val$markAsReadMessagesInbox.size() != 0) {
                    Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
                    for (b = 0; b < MessagesController$129.this.val$markAsReadMessagesInbox.size(); b++) {
                        key = MessagesController$129.this.val$markAsReadMessagesInbox.keyAt(b);
                        messageId = (int) ((Long) MessagesController$129.this.val$markAsReadMessagesInbox.get(key)).longValue();
                        dialog = (TLRPC$TL_dialog) MessagesController$129.this.this$0.dialogs_dict.get(Long.valueOf((long) key));
                        if (dialog != null && dialog.top_message > 0 && dialog.top_message <= messageId) {
                            obj = (MessageObject) MessagesController$129.this.this$0.dialogMessage.get(Long.valueOf(dialog.id));
                            if (!(obj == null || obj.isOut())) {
                                obj.setIsRead();
                                updateMask |= 256;
                            }
                        }
                        if (key != UserConfig.getClientUserId()) {
                            editor.remove("diditem" + key);
                            editor.remove("diditemo" + key);
                        }
                    }
                    editor.commit();
                }
                for (b = 0; b < MessagesController$129.this.val$markAsReadMessagesOutbox.size(); b++) {
                    key = MessagesController$129.this.val$markAsReadMessagesOutbox.keyAt(b);
                    messageId = (int) ((Long) MessagesController$129.this.val$markAsReadMessagesOutbox.get(key)).longValue();
                    dialog = (TLRPC$TL_dialog) MessagesController$129.this.this$0.dialogs_dict.get(Long.valueOf((long) key));
                    if (dialog != null && dialog.top_message > 0 && dialog.top_message <= messageId) {
                        obj = (MessageObject) MessagesController$129.this.this$0.dialogMessage.get(Long.valueOf(dialog.id));
                        if (obj != null && obj.isOut()) {
                            obj.setIsRead();
                            updateMask |= 256;
                        }
                    }
                }
            }
            if (!MessagesController$129.this.val$markAsReadEncrypted.isEmpty()) {
                for (Entry<Integer, Integer> entry : MessagesController$129.this.val$markAsReadEncrypted.entrySet()) {
                    NotificationCenter.getInstance().postNotificationName(NotificationCenter.messagesReadEncrypted, entry.getKey(), entry.getValue());
                    long dialog_id = ((long) ((Integer) entry.getKey()).intValue()) << 32;
                    if (((TLRPC$TL_dialog) MessagesController$129.this.this$0.dialogs_dict.get(Long.valueOf(dialog_id))) != null) {
                        MessageObject message = (MessageObject) MessagesController$129.this.this$0.dialogMessage.get(Long.valueOf(dialog_id));
                        if (message != null && message.messageOwner.date <= ((Integer) entry.getValue()).intValue()) {
                            message.setIsRead();
                            updateMask |= 256;
                        }
                    }
                }
            }
            if (!MessagesController$129.this.val$markAsReadMessages.isEmpty()) {
                NotificationCenter.getInstance().postNotificationName(NotificationCenter.messagesReadContent, MessagesController$129.this.val$markAsReadMessages);
            }
            if (MessagesController$129.this.val$deletedMessages.size() != 0) {
                for (a = 0; a < MessagesController$129.this.val$deletedMessages.size(); a++) {
                    key = MessagesController$129.this.val$deletedMessages.keyAt(a);
                    ArrayList<Integer> arrayList = (ArrayList) MessagesController$129.this.val$deletedMessages.get(key);
                    if (arrayList != null) {
                        NotificationCenter.getInstance().postNotificationName(NotificationCenter.messagesDeleted, arrayList, Integer.valueOf(key));
                        if (key == 0) {
                            for (b = 0; b < arrayList.size(); b++) {
                                obj = (MessageObject) MessagesController$129.this.this$0.dialogMessagesByIds.get((Integer) arrayList.get(b));
                                if (obj != null) {
                                    obj.deleted = true;
                                }
                            }
                        } else {
                            obj = (MessageObject) MessagesController$129.this.this$0.dialogMessage.get(Long.valueOf((long) (-key)));
                            if (obj != null) {
                                for (b = 0; b < arrayList.size(); b++) {
                                    if (obj.getId() == ((Integer) arrayList.get(b)).intValue()) {
                                        obj.deleted = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                NotificationsController.getInstance().removeDeletedMessagesFromNotifications(MessagesController$129.this.val$deletedMessages);
            }
            if (MessagesController$129.this.val$clearHistoryMessages.size() != 0) {
                for (a = 0; a < MessagesController$129.this.val$clearHistoryMessages.size(); a++) {
                    key = MessagesController$129.this.val$clearHistoryMessages.keyAt(a);
                    Integer id = (Integer) MessagesController$129.this.val$clearHistoryMessages.get(key);
                    if (id != null) {
                        long did = (long) (-key);
                        NotificationCenter.getInstance().postNotificationName(NotificationCenter.historyCleared, Long.valueOf(did), id);
                        obj = (MessageObject) MessagesController$129.this.this$0.dialogMessage.get(Long.valueOf(did));
                        if (obj != null && obj.getId() <= id.intValue()) {
                            obj.deleted = true;
                            break;
                        }
                    }
                }
                NotificationsController.getInstance().removeDeletedHisoryFromNotifications(MessagesController$129.this.val$clearHistoryMessages);
            }
            if (updateMask != 0) {
                NotificationCenter.getInstance().postNotificationName(NotificationCenter.updateInterfaces, Integer.valueOf(updateMask));
            }
        }
    }

    MessagesController$129(MessagesController this$0, SparseArray sparseArray, SparseArray sparseArray2, HashMap hashMap, ArrayList arrayList, SparseArray sparseArray3, SparseArray sparseArray4) {
        this.this$0 = this$0;
        this.val$markAsReadMessagesInbox = sparseArray;
        this.val$markAsReadMessagesOutbox = sparseArray2;
        this.val$markAsReadEncrypted = hashMap;
        this.val$markAsReadMessages = arrayList;
        this.val$deletedMessages = sparseArray3;
        this.val$clearHistoryMessages = sparseArray4;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C50001());
    }
}
