package org.altbeacon.beacon.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.ScanJobScheduler;

public class StartupBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "StartupBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        LogManager.m16371d(TAG, "onReceive called in startup broadcast receiver", new Object[0]);
        if (VERSION.SDK_INT < 18) {
            LogManager.m16379w(TAG, "Not starting up beacon service because we do not have API version 18 (Android 4.3).  We have: %s", Integer.valueOf(VERSION.SDK_INT));
            return;
        }
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(context.getApplicationContext());
        if (beaconManager.isAnyConsumerBound() || beaconManager.getScheduledScanJobsEnabled()) {
            int bleCallbackType = intent.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", -1);
            if (bleCallbackType != -1) {
                LogManager.m16371d(TAG, "Passive background scan callback type: " + bleCallbackType, new Object[0]);
                LogManager.m16371d(TAG, "got Android O background scan via intent", new Object[0]);
                int errorCode = intent.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", -1);
                if (errorCode != -1) {
                    LogManager.m16379w(TAG, "Passive background scan failed.  Code; " + errorCode, new Object[0]);
                }
                ScanJobScheduler.getInstance().scheduleAfterBackgroundWakeup(context, intent.getParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT"));
                return;
            } else if (intent.getBooleanExtra("wakeup", false)) {
                LogManager.m16371d(TAG, "got wake up intent", new Object[0]);
                return;
            } else {
                LogManager.m16371d(TAG, "Already started.  Ignoring intent: %s of type: %s", intent, intent.getStringExtra("wakeup"));
                return;
            }
        }
        LogManager.m16371d(TAG, "No consumers are bound.  Ignoring broadcast receiver.", new Object[0]);
    }
}
