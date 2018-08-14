package org.telegram.tgnet;

import org.telegram.messenger.NotificationCenter;

class ConnectionsManager$8 implements Runnable {
    final /* synthetic */ int val$state;

    ConnectionsManager$8(int i) {
        this.val$state = i;
    }

    public void run() {
        ConnectionsManager.access$202(ConnectionsManager.getInstance(), this.val$state);
        NotificationCenter.getInstance().postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
    }
}
