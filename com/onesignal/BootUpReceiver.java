package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class BootUpReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intentForService = new Intent();
        intentForService.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        startWakefulService(context, intentForService);
    }
}
