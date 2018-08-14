package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@zzadh
public final class zzajv implements zzgj {
    private final Object lock;
    @VisibleForTesting
    private final zzajr zzcqn;
    @VisibleForTesting
    private final HashSet<zzajj> zzcqo;
    @VisibleForTesting
    private final HashSet<zzaju> zzcqp;

    public zzajv() {
        this(zzkb.zzih());
    }

    private zzajv(String str) {
        this.lock = new Object();
        this.zzcqo = new HashSet();
        this.zzcqp = new HashSet();
        this.zzcqn = new zzajr(str);
    }

    public final Bundle zza(Context context, zzajs com_google_android_gms_internal_ads_zzajs, String str) {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzcqn.zzk(context, str));
            Bundle bundle2 = new Bundle();
            Iterator it = this.zzcqp.iterator();
            while (it.hasNext()) {
                zzaju com_google_android_gms_internal_ads_zzaju = (zzaju) it.next();
                bundle2.putBundle(com_google_android_gms_internal_ads_zzaju.zzqm(), com_google_android_gms_internal_ads_zzaju.toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            it = this.zzcqo.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzajj) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            com_google_android_gms_internal_ads_zzajs.zza(this.zzcqo);
            this.zzcqo.clear();
        }
        return bundle;
    }

    public final void zza(zzajj com_google_android_gms_internal_ads_zzajj) {
        synchronized (this.lock) {
            this.zzcqo.add(com_google_android_gms_internal_ads_zzajj);
        }
    }

    public final void zza(zzaju com_google_android_gms_internal_ads_zzaju) {
        synchronized (this.lock) {
            this.zzcqp.add(com_google_android_gms_internal_ads_zzaju);
        }
    }

    public final void zzb(zzjj com_google_android_gms_internal_ads_zzjj, long j) {
        synchronized (this.lock) {
            this.zzcqn.zzb(com_google_android_gms_internal_ads_zzjj, j);
        }
    }

    public final void zzb(HashSet<zzajj> hashSet) {
        synchronized (this.lock) {
            this.zzcqo.addAll(hashSet);
        }
    }

    public final void zzh(boolean z) {
        long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        if (z) {
            if (currentTimeMillis - zzbv.zzeo().zzqh().zzrb() > ((Long) zzkb.zzik().zzd(zznk.zzayi)).longValue()) {
                this.zzcqn.zzcqg = -1;
                return;
            }
            this.zzcqn.zzcqg = zzbv.zzeo().zzqh().zzrc();
            return;
        }
        zzbv.zzeo().zzqh().zzj(currentTimeMillis);
        zzbv.zzeo().zzqh().zzaf(this.zzcqn.zzcqg);
    }

    public final void zzpm() {
        synchronized (this.lock) {
            this.zzcqn.zzpm();
        }
    }

    public final void zzpn() {
        synchronized (this.lock) {
            this.zzcqn.zzpn();
        }
    }
}
