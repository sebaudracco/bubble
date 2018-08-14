package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzty {
    private final LinkedList<zztz> zzbon = new LinkedList();
    private zzjj zzboo;
    private final int zzbop;
    private boolean zzboq;
    private final String zzye;

    zzty(zzjj com_google_android_gms_internal_ads_zzjj, String str, int i) {
        Preconditions.checkNotNull(com_google_android_gms_internal_ads_zzjj);
        Preconditions.checkNotNull(str);
        this.zzboo = com_google_android_gms_internal_ads_zzjj;
        this.zzye = str;
        this.zzbop = i;
    }

    final String getAdUnitId() {
        return this.zzye;
    }

    final int getNetworkType() {
        return this.zzbop;
    }

    final int size() {
        return this.zzbon.size();
    }

    final void zza(zzss com_google_android_gms_internal_ads_zzss, zzjj com_google_android_gms_internal_ads_zzjj) {
        this.zzbon.add(new zztz(this, com_google_android_gms_internal_ads_zzss, com_google_android_gms_internal_ads_zzjj));
    }

    final boolean zzb(zzss com_google_android_gms_internal_ads_zzss) {
        zztz com_google_android_gms_internal_ads_zztz = new zztz(this, com_google_android_gms_internal_ads_zzss);
        this.zzbon.add(com_google_android_gms_internal_ads_zztz);
        return com_google_android_gms_internal_ads_zztz.load();
    }

    final zztz zzl(@Nullable zzjj com_google_android_gms_internal_ads_zzjj) {
        if (com_google_android_gms_internal_ads_zzjj != null) {
            this.zzboo = com_google_android_gms_internal_ads_zzjj;
        }
        return (zztz) this.zzbon.remove();
    }

    final zzjj zzlf() {
        return this.zzboo;
    }

    final int zzlg() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zztz) it.next()).zzwa ? i + 1 : i;
        }
        return i;
    }

    final int zzlh() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zztz) it.next()).load() ? i + 1 : i;
        }
        return i;
    }

    final void zzli() {
        this.zzboq = true;
    }

    final boolean zzlj() {
        return this.zzboq;
    }
}
