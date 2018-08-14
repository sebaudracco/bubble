package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

class MessagesController$51 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;

    MessagesController$51(MessagesController this$0) {
        this.this$0 = this$0;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            MessagesController.access$4302(this.this$0, true);
        } else if (MessagesController.access$4200(this.this$0) != 0) {
            MessagesController.access$4202(this.this$0, MessagesController.access$4200(this.this$0) + 5000);
        }
        MessagesController.access$4502(this.this$0, 0);
    }
}
