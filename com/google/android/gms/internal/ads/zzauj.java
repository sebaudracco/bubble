package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzauj {
    private static final CopyOnWriteArrayList<zzaui> zzdhj = new CopyOnWriteArrayList();

    public static zzaui zzdx(String str) throws GeneralSecurityException {
        Iterator it = zzdhj.iterator();
        while (it.hasNext()) {
            zzaui com_google_android_gms_internal_ads_zzaui = (zzaui) it.next();
            if (com_google_android_gms_internal_ads_zzaui.zzdv(str)) {
                return com_google_android_gms_internal_ads_zzaui;
            }
        }
        String str2 = "No KMS client does support: ";
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
