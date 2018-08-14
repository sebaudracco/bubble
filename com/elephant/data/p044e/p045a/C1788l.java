package com.elephant.data.p044e.p045a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class C1788l extends BroadcastReceiver {
    final /* synthetic */ C1785i f3773a;

    C1788l(C1785i c1785i) {
        this.f3773a = c1785i;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            new Thread(new C1789m(this, context)).start();
        } else if (intent.getStringExtra(C1785i.f3751o) != null && intent.getStringExtra(C1785i.f3751o).equals(this.f3773a.f3753c.getPackageName()) && !action.equals(C1785i.f3748l) && action.equals(C1785i.f3749m)) {
            C1783g a = this.f3773a.f3756f.m5002a(intent.getStringExtra(C1785i.f3750n));
            if (a != null) {
                C1785i.m5141a(this.f3773a, a);
                this.f3773a.m5163a(true);
            }
        }
    }
}
