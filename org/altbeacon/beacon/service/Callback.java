package org.altbeacon.beacon.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.altbeacon.beacon.BeaconLocalBroadcastProcessor;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;

public class Callback implements Serializable {
    private static final String TAG = "Callback";

    public Callback(String intentPackageName) {
    }

    public boolean call(Context context, String dataName, Bundle data) {
        if (BeaconManager.getInstanceForApplication(context).getScheduledScanJobsEnabled()) {
            String action;
            if (dataName == "rangingData") {
                action = BeaconLocalBroadcastProcessor.RANGE_NOTIFICATION;
            } else {
                action = BeaconLocalBroadcastProcessor.MONITOR_NOTIFICATION;
            }
            Intent intent = new Intent(action);
            intent.putExtra(dataName, data);
            LogManager.m16371d(TAG, "attempting callback via local broadcast intent: %s", action);
            return LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
        intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(), "org.altbeacon.beacon.BeaconIntentProcessor"));
        intent.putExtra(dataName, data);
        LogManager.m16371d(TAG, "attempting callback via global broadcast intent: %s", intent.getComponent());
        try {
            context.startService(intent);
            return true;
        } catch (Exception e) {
            LogManager.m16373e(TAG, "Failed attempting to start service: " + intent.getComponent().flattenToString(), e);
            return false;
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
