package com.appsgeyser.sdk.push;

import android.util.Log;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal.NotificationReceivedHandler;
import org.json.JSONObject;

class OneSignalNotificationReceivedHandler implements NotificationReceivedHandler {
    OneSignalNotificationReceivedHandler() {
    }

    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        if (data != null) {
            Log.i("OneSignalData", data.toString());
        }
    }
}
