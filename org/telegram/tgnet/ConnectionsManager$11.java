package org.telegram.tgnet;

import org.telegram.messenger.FileLog;

class ConnectionsManager$11 implements Runnable {
    ConnectionsManager$11() {
    }

    public void run() {
        try {
            if (!ConnectionsManager.access$100(ConnectionsManager.getInstance()).isHeld()) {
                ConnectionsManager.access$100(ConnectionsManager.getInstance()).acquire(10000);
                FileLog.d("acquire wakelock");
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
    }
}
