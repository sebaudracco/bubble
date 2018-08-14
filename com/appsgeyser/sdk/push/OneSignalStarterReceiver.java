package com.appsgeyser.sdk.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.google.gson.JsonSyntaxException;
import com.onesignal.OneSignal;
import com.onesignal.OneSignal.OSInFocusDisplayOption;

public class OneSignalStarterReceiver extends BroadcastReceiver {
    private static final String ACTION_QUICKBOOT = "android.intent.action.QUICKBOOT_POWERON";

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals(ACTION_QUICKBOOT)) {
                String configRaw = new PreferencesCoder(context).getPrefString(Constants.PREFS_SERVER_RESPONSE, null);
                if (configRaw != null) {
                    try {
                        OneSignalCreator.init(ConfigPhp.parseFromJson(configRaw).getOneSignalAppId(), context);
                    } catch (JsonSyntaxException e) {
                        OneSignal.startInit(context).inFocusDisplaying(OSInFocusDisplayOption.Notification).unsubscribeWhenNotificationsAreDisabled(true).init();
                    }
                }
            }
        }
    }
}
