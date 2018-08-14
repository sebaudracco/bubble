package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class bact extends C1079a {
    public static final String hl = bact.class.getSimpleName();

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "android.permission.BLUETOOTH");
    }

    public IntentFilter getBRFilter() {
        try {
            if (!hasPermission()) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            return intentFilter;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public void onReceive(Context context, final Intent intent) {
        try {
            C1058l.m2184k("Receiver", getClass().getSimpleName());
            super.onReceive(context, intent);
            new Thread(new Runnable(this) {
                final /* synthetic */ bact hQ;

                public void run() {
                    String str = null;
                    if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                        intent.getExtras();
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                        if (intExtra == 10 || intExtra == 13) {
                            str = SchemaSymbols.ATTVAL_FALSE;
                        } else if (intExtra == 12 || intExtra == 11) {
                            str = SchemaSymbols.ATTVAL_TRUE;
                        }
                    }
                    this.hQ.m2250b(bact.hl, str, C1041a.Boolean);
                }
            }).start();
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
