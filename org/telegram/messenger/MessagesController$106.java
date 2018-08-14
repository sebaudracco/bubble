package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

class MessagesController$106 implements Comparator<TLRPC$Updates> {
    final /* synthetic */ MessagesController this$0;

    MessagesController$106(MessagesController this$0) {
        this.this$0 = this$0;
    }

    public int compare(TLRPC$Updates updates, TLRPC$Updates updates2) {
        return AndroidUtilities.compare(MessagesController.access$6100(this.this$0, updates), MessagesController.access$6100(this.this$0, updates2));
    }
}
