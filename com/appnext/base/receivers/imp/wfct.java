package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.receivers.C1079a;

public class wfct extends C1079a {
    public static final String hl = wfct.class.getSimpleName();

    public IntentFilter getBRFilter() {
        try {
            if (hasPermission()) {
                return new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
            }
            return null;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.ACCESS_WIFI_STATE");
    }

    public void onReceive(Context context, Intent intent) {
        boolean z = true;
        try {
            super.onReceive(context, intent);
            if (hasPermission()) {
                int intExtra = intent.getIntExtra("wifi_state", 4);
                if (intExtra != 3) {
                    if (intExtra == 1) {
                        z = false;
                    } else {
                        return;
                    }
                }
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ wfct iZ;

                    public void run() {
                        this.iZ.m2250b(wfct.hl, String.valueOf(z), C1041a.Boolean);
                    }
                }, 15000);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
