package com.appsgeyser.sdk.push;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal.NotificationOpenedHandler;
import org.telegram.tgnet.ConnectionsManager;

class OneSignalNotificationOpenedHandler implements NotificationOpenedHandler {
    private static final String MESSAGE_KEY = "msg";
    private final Context context;

    OneSignalNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    public void notificationOpened(@NonNull OSNotificationOpenResult result) {
        if (result.notification.payload.additionalData != null) {
            String title = result.notification.payload.title;
            String message = result.notification.payload.additionalData.optString("msg");
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
                MessageViewer.launchWithContent(this.context, title, message);
                return;
            }
            return;
        }
        startActivity();
    }

    private void startActivity() {
        Intent intent = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        intent.addFlags(ConnectionsManager.FileTypeFile);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.addFlags(2097152);
        this.context.startActivity(intent);
    }
}
