package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;

class ConnectionsManager$9 implements Runnable {
    ConnectionsManager$9() {
    }

    public void run() {
        if (UserConfig.getClientUserId() != 0) {
            UserConfig.clearConfig();
            MessagesController.getInstance().performLogout(false);
        }
    }
}
