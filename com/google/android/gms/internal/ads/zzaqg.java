package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzadh
public final class zzaqg implements Iterable<zzaqe> {
    private final List<zzaqe> zzday = new ArrayList();

    public static boolean zzb(zzapw com_google_android_gms_internal_ads_zzapw) {
        zzaqe zzc = zzc(com_google_android_gms_internal_ads_zzapw);
        if (zzc == null) {
            return false;
        }
        zzc.zzdav.abort();
        return true;
    }

    static zzaqe zzc(zzapw com_google_android_gms_internal_ads_zzapw) {
        Iterator it = zzbv.zzff().iterator();
        while (it.hasNext()) {
            zzaqe com_google_android_gms_internal_ads_zzaqe = (zzaqe) it.next();
            if (com_google_android_gms_internal_ads_zzaqe.zzcyg == com_google_android_gms_internal_ads_zzapw) {
                return com_google_android_gms_internal_ads_zzaqe;
            }
        }
        return null;
    }

    public final Iterator<zzaqe> iterator() {
        return this.zzday.iterator();
    }

    public final void zza(zzaqe com_google_android_gms_internal_ads_zzaqe) {
        this.zzday.add(com_google_android_gms_internal_ads_zzaqe);
    }

    public final void zzb(zzaqe com_google_android_gms_internal_ads_zzaqe) {
        this.zzday.remove(com_google_android_gms_internal_ads_zzaqe);
    }

    public final int zztx() {
        return this.zzday.size();
    }
}
