package org.telegram.messenger;

import android.content.SharedPreferences.Editor;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

class MessagesController$20 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ long val$dialogId;

    class C50091 implements Runnable {
        C50091() {
        }

        public void run() {
            MessagesController.access$3000(MessagesController$20.this.this$0).remove(Long.valueOf(MessagesController$20.this.val$dialogId));
            Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            editor.remove("spam_" + MessagesController$20.this.val$dialogId);
            editor.putInt("spam3_" + MessagesController$20.this.val$dialogId, 1);
            editor.commit();
        }
    }

    MessagesController$20(MessagesController this$0, long j) {
        this.this$0 = this$0;
        this.val$dialogId = j;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        AndroidUtilities.runOnUIThread(new C50091());
    }
}
