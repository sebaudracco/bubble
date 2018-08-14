package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.telegram.messenger.voip.VoIPBaseService;

public class earcon extends C1079a {
    public static final String hl = earcon.class.getSimpleName();

    public IntentFilter getBRFilter() {
        try {
            if (hasPermission()) {
                return new IntentFilter(VoIPBaseService.ACTION_HEADSET_PLUG);
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
            if (VoIPBaseService.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                m2250b(hl, intent.getIntExtra("state", -1) == 0 ? SchemaSymbols.ATTVAL_FALSE : SchemaSymbols.ATTVAL_TRUE, C1041a.Boolean);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
