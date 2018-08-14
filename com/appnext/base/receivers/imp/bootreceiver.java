package com.appnext.base.receivers.imp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;

public class bootreceiver extends BroadcastReceiver {
    public static final String TAG = bootreceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        try {
            C1058l.m2184k("Receiver", getClass().getSimpleName());
            if (context != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                C1043d.init(context);
                C1057k.m2181o(context);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
