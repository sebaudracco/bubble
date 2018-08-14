package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

final class zzp implements zzv<Object> {
    zzp() {
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = "Received log message: ";
        String valueOf = String.valueOf((String) map.get(SchemaSymbols.ATTVAL_STRING));
        zzakb.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
