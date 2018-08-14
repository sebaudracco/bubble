package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgf {
    private final Object mLock = new Object();
    private int zzahm;
    private List<zzge> zzahn = new LinkedList();

    public final boolean zza(zzge com_google_android_gms_internal_ads_zzge) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzahn.contains(com_google_android_gms_internal_ads_zzge)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final boolean zzb(zzge com_google_android_gms_internal_ads_zzge) {
        synchronized (this.mLock) {
            Iterator it = this.zzahn.iterator();
            while (it.hasNext()) {
                zzge com_google_android_gms_internal_ads_zzge2 = (zzge) it.next();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() || zzbv.zzeo().zzqh().zzqu()) {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw() && com_google_android_gms_internal_ads_zzge != com_google_android_gms_internal_ads_zzge2 && com_google_android_gms_internal_ads_zzge2.zzgp().equals(com_google_android_gms_internal_ads_zzge.zzgp())) {
                        it.remove();
                        return true;
                    }
                } else if (com_google_android_gms_internal_ads_zzge != com_google_android_gms_internal_ads_zzge2 && com_google_android_gms_internal_ads_zzge2.getSignature().equals(com_google_android_gms_internal_ads_zzge.getSignature())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzge com_google_android_gms_internal_ads_zzge) {
        synchronized (this.mLock) {
            if (this.zzahn.size() >= 10) {
                zzane.zzck("Queue is full, current size = " + this.zzahn.size());
                this.zzahn.remove(0);
            }
            int i = this.zzahm;
            this.zzahm = i + 1;
            com_google_android_gms_internal_ads_zzge.zzo(i);
            this.zzahn.add(com_google_android_gms_internal_ads_zzge);
        }
    }

    @Nullable
    public final zzge zzgv() {
        zzge com_google_android_gms_internal_ads_zzge = null;
        int i = 0;
        synchronized (this.mLock) {
            if (this.zzahn.size() == 0) {
                zzane.zzck("Queue empty");
                return null;
            } else if (this.zzahn.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzge com_google_android_gms_internal_ads_zzge2 : this.zzahn) {
                    zzge com_google_android_gms_internal_ads_zzge3;
                    int i4;
                    int score = com_google_android_gms_internal_ads_zzge2.getScore();
                    if (score > i2) {
                        i = score;
                        com_google_android_gms_internal_ads_zzge3 = com_google_android_gms_internal_ads_zzge2;
                        i4 = i3;
                    } else {
                        i4 = i;
                        com_google_android_gms_internal_ads_zzge3 = com_google_android_gms_internal_ads_zzge;
                        i = i2;
                    }
                    i3++;
                    i2 = i;
                    com_google_android_gms_internal_ads_zzge = com_google_android_gms_internal_ads_zzge3;
                    i = i4;
                }
                this.zzahn.remove(i);
                return com_google_android_gms_internal_ads_zzge;
            } else {
                com_google_android_gms_internal_ads_zzge2 = (zzge) this.zzahn.get(0);
                com_google_android_gms_internal_ads_zzge2.zzgq();
                return com_google_android_gms_internal_ads_zzge2;
            }
        }
    }
}
