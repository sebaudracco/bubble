package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;

class ConnectionsManager$10 implements Runnable {
    final /* synthetic */ TLRPC$TL_config val$message;

    ConnectionsManager$10(TLRPC$TL_config tLRPC$TL_config) {
        this.val$message = tLRPC$TL_config;
    }

    public void run() {
        MessagesController.getInstance().updateConfig(this.val$message);
    }
}
