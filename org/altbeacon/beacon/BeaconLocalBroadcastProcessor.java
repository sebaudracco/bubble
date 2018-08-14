package org.altbeacon.beacon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import org.altbeacon.beacon.logging.LogManager;

public class BeaconLocalBroadcastProcessor {
    public static final String MONITOR_NOTIFICATION = "org.altbeacon.beacon.monitor_notification";
    public static final String RANGE_NOTIFICATION = "org.altbeacon.beacon.range_notification";
    private static final String TAG = "BeaconLocalBroadcastProcessor";
    static int registerCallCount = 0;
    @NonNull
    private Context mContext;
    private BroadcastReceiver mLocalBroadcastReceiver = new C47861();
    int registerCallCountForInstnace = 0;

    class C47861 extends BroadcastReceiver {
        C47861() {
        }

        public void onReceive(Context context, Intent intent) {
            new IntentHandler().convertIntentsToCallbacks(context, intent);
        }
    }

    private BeaconLocalBroadcastProcessor() {
    }

    public BeaconLocalBroadcastProcessor(Context context) {
        this.mContext = context;
    }

    public void register() {
        registerCallCount++;
        this.registerCallCountForInstnace++;
        LogManager.m16371d(TAG, "Register calls: global=" + registerCallCount + " instance=" + this.registerCallCountForInstnace, new Object[0]);
        unregister();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mLocalBroadcastReceiver, new IntentFilter(RANGE_NOTIFICATION));
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mLocalBroadcastReceiver, new IntentFilter(MONITOR_NOTIFICATION));
    }

    public void unregister() {
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mLocalBroadcastReceiver);
    }
}
