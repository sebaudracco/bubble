package com.core42matters.android.registrar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.preference.PreferenceManager;

/* compiled from: Registrar */
final class ConsentClickListener implements OnClickListener {
    private Activity activity;
    private AppId appId;

    ConsentClickListener(Activity activity, AppId appId) {
        this.activity = activity;
        this.appId = appId;
    }

    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case -2:
                PreferenceManager.getDefaultSharedPreferences(this.activity).edit().putBoolean("com.core42matters.android.registrar.user_consent", false).apply();
                return;
            case -1:
                TaskQueue.scheduleRegistration(this.activity, this.appId);
                PreferenceManager.getDefaultSharedPreferences(this.activity).edit().putBoolean("com.core42matters.android.registrar.user_consent", true).apply();
                return;
            default:
                return;
        }
    }
}
