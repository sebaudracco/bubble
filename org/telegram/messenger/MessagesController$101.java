package org.telegram.messenger;

import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

class MessagesController$101 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;

    MessagesController$101(MessagesController this$0) {
        this.this$0 = this$0;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        ConnectionsManager.getInstance().cleanup();
    }
}
