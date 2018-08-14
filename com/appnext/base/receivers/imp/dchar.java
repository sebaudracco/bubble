package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;

public class dchar extends C1079a {
    public static final String hl = dchar.class.getSimpleName();

    public boolean hasPermission() {
        return true;
    }

    public IntentFilter getBRFilter() {
        try {
            if (!hasPermission()) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            return intentFilter;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
            C1058l.m2184k("Receiver", hl);
            m2250b(hl, String.valueOf(Boolean.valueOf(C1045f.m2137k(context.getApplicationContext()))), C1041a.Boolean);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
