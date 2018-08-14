package com.stepleaderdigital.reveal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Log;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

public class StartupBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.v("Reveal", "*\n*\n*\n*\n*\n*\n*\n* StartupBroadcastReceiver onReceive called in startup broadcast receiver\n*\n*\n*\n*\n*\n*\n*");
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12441w("Not starting up beacon service because we do not have API version 18 (Android 4.3).  We have: " + VERSION.SDK_INT);
        } else if (intent.getAction() == null) {
        } else {
            if (intent.getBooleanExtra("wakeup", false)) {
                RevealLogger.m12430d("StartupBroadcastReceiver got wake up intent");
            } else {
                RevealLogger.m12430d("StartupBroadcastReceiver Already started.  Ignoring intent: " + intent + " of type: " + intent.getStringExtra("wakeup"));
            }
        }
    }
}
