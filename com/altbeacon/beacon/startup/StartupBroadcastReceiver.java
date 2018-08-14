package com.altbeacon.beacon.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.p013c.C0835d;

public class StartupBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        C0835d.m1657a("StartupBroadcastReceiver", "onReceive called in startup broadcast receiver", new Object[0]);
        if (VERSION.SDK_INT < 18) {
            C0835d.m1662c("StartupBroadcastReceiver", "Not starting up beacon service because we do not have API version 18 (Android 4.3).  We have: %s", Integer.valueOf(VERSION.SDK_INT));
        } else if (!C0829b.m1619a(context.getApplicationContext()).m1632e()) {
        } else {
            if (intent.getBooleanExtra("wakeup", false)) {
                C0835d.m1657a("StartupBroadcastReceiver", "got wake up intent", new Object[0]);
                return;
            }
            C0835d.m1657a("StartupBroadcastReceiver", "Already started.  Ignoring intent: %s of type: %s", intent, intent.getStringExtra("wakeup"));
        }
    }
}
