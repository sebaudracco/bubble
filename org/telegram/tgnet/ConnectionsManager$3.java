package org.telegram.tgnet;

import org.telegram.messenger.FileLog;

class ConnectionsManager$3 implements Runnable {
    ConnectionsManager$3() {
    }

    public void run() {
        if (ConnectionsManager.access$100(ConnectionsManager.getInstance()).isHeld()) {
            FileLog.d("release wakelock");
            ConnectionsManager.access$100(ConnectionsManager.getInstance()).release();
        }
    }
}
