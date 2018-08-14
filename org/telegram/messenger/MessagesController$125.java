package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateUserBlocked;

class MessagesController$125 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ TLRPC$TL_updateUserBlocked val$finalUpdate;

    class C49951 implements Runnable {
        C49951() {
        }

        public void run() {
            if (!MessagesController$125.this.val$finalUpdate.blocked) {
                MessagesController$125.this.this$0.blockedUsers.remove(Integer.valueOf(MessagesController$125.this.val$finalUpdate.user_id));
            } else if (!MessagesController$125.this.this$0.blockedUsers.contains(Integer.valueOf(MessagesController$125.this.val$finalUpdate.user_id))) {
                MessagesController$125.this.this$0.blockedUsers.add(Integer.valueOf(MessagesController$125.this.val$finalUpdate.user_id));
            }
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
        }
    }

    MessagesController$125(MessagesController this$0, TLRPC$TL_updateUserBlocked tLRPC$TL_updateUserBlocked) {
        this.this$0 = this$0;
        this.val$finalUpdate = tLRPC$TL_updateUserBlocked;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(new C49951());
    }
}
