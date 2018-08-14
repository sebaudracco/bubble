package org.telegram.messenger.config;

import android.util.Log;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_contacts_resolvedPeer;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC.User;

class GroupManager$3 implements RequestDelegate {
    final /* synthetic */ GroupManager this$0;

    GroupManager$3(GroupManager this$0) {
        this.this$0 = this$0;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            GroupManager.access$100(this.this$0).lock();
            TLRPC$TL_contacts_resolvedPeer res = (TLRPC$TL_contacts_resolvedPeer) response;
            MessagesController.getInstance().putUsers(res.users, false);
            MessagesController.getInstance().putChats(res.chats, false);
            MessagesStorage.getInstance().putUsersAndChats(res.users, res.chats, false, true);
            if (!res.chats.isEmpty()) {
                MessagesController.getInstance().addUserToChat(((TLRPC$Chat) res.chats.get(0)).id, UserConfig.getCurrentUser(), null, 0, null, null);
            }
            if (!res.users.isEmpty()) {
                User user = (User) res.users.get(0);
                Log.d("GroupManager", "User " + user.username);
                if (user.bot) {
                    MessagesController.getInstance().sendBotStart(user, GroupManager.access$200(this.this$0) != null ? GroupManager.access$200(this.this$0) : "start");
                }
            }
            Log.d("GroupManager", "Bot added");
            GroupManager.access$100(this.this$0).unlock();
            return;
        }
        Log.w("GroupManager", "Bot adding error: " + error.text);
    }
}
