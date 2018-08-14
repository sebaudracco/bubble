package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;

public class scror extends C1079a {
    public static final String hl = scror.class.getSimpleName();

    public IntentFilter getBRFilter() {
        try {
            if (hasPermission()) {
                return new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");
            }
            return null;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public boolean hasPermission() {
        return true;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
            C1058l.m2184k("Receiver", hl);
            if ("android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction())) {
                String str;
                if (context.getResources().getConfiguration().orientation == 2) {
                    str = "LANDSCAPE";
                } else {
                    str = "PORTRAIT";
                }
                m2250b(hl, str, C1041a.String);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
