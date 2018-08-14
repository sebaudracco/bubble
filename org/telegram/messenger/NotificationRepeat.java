package org.telegram.messenger;

import android.app.IntentService;
import android.content.Intent;

public class NotificationRepeat extends IntentService {

    class C51121 implements Runnable {
        C51121() {
        }

        public void run() {
            NotificationsController.getInstance().repeatNotificationMaybe();
        }
    }

    public NotificationRepeat() {
        super("NotificationRepeat");
    }

    protected void onHandleIntent(Intent intent) {
        AndroidUtilities.runOnUIThread(new C51121());
    }
}
