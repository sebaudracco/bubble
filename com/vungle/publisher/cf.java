package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.vungle.publisher.env.n;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class cf extends BroadcastReceiver {
    @Inject
    Context f2756a;
    @Inject
    ci f2757b;
    @Inject
    n f2758c;

    @Inject
    cf() {
    }

    public void m3505a() {
        this.f2756a.registerReceiver(this, new IntentFilter("com.vungle.publisher.db.DUMP_TABLES"));
    }

    public void m3506b() {
        try {
            this.f2756a.unregisterReceiver(this);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error unregistering database broadcast receiver - not registered");
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if ("com.vungle.publisher.db.DUMP_TABLES".equals(intent.getAction())) {
                Logger.d(Logger.DATABASE_DUMP_TAG, this.f2758c.b() + " received dump tables request");
                this.f2757b.m3515a(intent.getStringArrayExtra("tables"));
            }
        } catch (Throwable e) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error dumping database", e);
        }
    }
}
