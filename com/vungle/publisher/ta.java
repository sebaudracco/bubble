package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.vungle.publisher.log.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ta extends BroadcastReceiver {
    private static final IntentFilter f3341e = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    @Inject
    Context f3342a;
    @Inject
    sz f3343b;
    @Inject
    qg f3344c;
    @Inject
    ConnectivityManager f3345d;
    private final AtomicBoolean f3346f = new AtomicBoolean(false);

    @Inject
    ta() {
    }

    public void onReceive(Context context, Intent intent) {
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            return;
        }
        if (intent.getBooleanExtra("noConnectivity", false)) {
            if (this.f3346f.compareAndSet(true, false)) {
                Logger.d(Logger.NETWORK_TAG, "lost connectivity");
                this.f3344c.m4568a(new lv());
            }
        } else if (intent.getBooleanExtra("isFailover", false)) {
            Logger.d(Logger.NETWORK_TAG, "connectivity failover");
        } else {
            Logger.d(Logger.NETWORK_TAG, "connectivity established");
            synchronized (this) {
                notifyAll();
            }
            if (this.f3346f.compareAndSet(false, true)) {
                this.f3344c.m4568a(new lu());
            }
        }
    }

    public void m4650a() {
        this.f3342a.registerReceiver(this, f3341e);
    }

    public void m4651b() {
        try {
            this.f3342a.unregisterReceiver(this);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.NETWORK_TAG, "error unregistering network broadcast receiver - not registered");
        }
    }
}
