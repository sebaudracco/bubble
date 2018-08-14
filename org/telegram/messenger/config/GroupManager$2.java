package org.telegram.messenger.config;

import android.util.Log;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$Updates;

class GroupManager$2 implements RequestDelegate {
    final /* synthetic */ GroupManager this$0;

    GroupManager$2(GroupManager this$0) {
        this.this$0 = this$0;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            MessagesController.getInstance().processUpdates((TLRPC$Updates) response, false);
            Log.d("GroupManager", "Group added");
        } else {
            Log.w("GroupManager", "Group adding error: " + error.text);
        }
        TLRPC$Updates updates = (TLRPC$Updates) response;
        if (updates != null && updates.chats != null && !updates.chats.isEmpty()) {
            MessagesController.getInstance().addUserToChat(((TLRPC$Chat) updates.chats.get(0)).id, UserConfig.getCurrentUser(), null, 0, null, null);
        }
    }
}
