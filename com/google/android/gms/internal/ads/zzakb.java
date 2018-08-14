package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.ads.AdRequest;

@zzadh
public final class zzakb extends zzane {
    public static void m3428v(String str) {
        if (zzqp()) {
            Log.v(AdRequest.LOGTAG, str);
        }
    }

    public static boolean zzqp() {
        if (zzane.isLoggable(2)) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzazr)).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
