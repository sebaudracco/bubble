package com.appsgeyser.sdk.push;

import android.content.Context;
import android.support.annotation.NonNull;
import com.onesignal.OneSignal;
import com.onesignal.OneSignal.OSInFocusDisplayOption;

public class OneSignalCreator {
    public static void init(@NonNull String oneSignalAppId, @NonNull Context context) {
        OneSignal.init(context, null, oneSignalAppId, new OneSignalNotificationOpenedHandler(context), new OneSignalNotificationReceivedHandler());
        OneSignal.setInFocusDisplaying(OSInFocusDisplayOption.Notification);
    }
}
