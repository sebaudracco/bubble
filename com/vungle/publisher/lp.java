package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class lp extends BroadcastReceiver {
    @Inject
    Context f3092a;
    @Inject
    qg f3093b;

    @Inject
    lp() {
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            this.f3093b.m4568a(new ls());
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            this.f3093b.m4568a(new lt());
        }
    }

    public void m4351a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        this.f3092a.registerReceiver(this, intentFilter);
    }

    public void m4352b() {
        try {
            this.f3092a.unregisterReceiver(this);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.DEVICE_TAG, "error unregistering external storage state broadcast receiver - not registered");
        }
    }
}
