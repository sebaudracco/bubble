package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzes implements zzfa {
    private final Object mLock = new Object();
    private final WeakHashMap<zzajh, zzet> zzaem = new WeakHashMap();
    private final ArrayList<zzet> zzaen = new ArrayList();
    private final Context zzaeo;
    private final zzvf zzaep;
    private final zzang zzyf;

    public zzes(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzaeo = context.getApplicationContext();
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzaep = new zzvf(context.getApplicationContext(), com_google_android_gms_internal_ads_zzang, (String) zzkb.zzik().zzd(zznk.zzaub));
    }

    private final boolean zzg(zzajh com_google_android_gms_internal_ads_zzajh) {
        boolean z;
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            z = com_google_android_gms_internal_ads_zzet != null && com_google_android_gms_internal_ads_zzet.zzge();
        }
        return z;
    }

    public final void zza(zzet com_google_android_gms_internal_ads_zzet) {
        synchronized (this.mLock) {
            if (!com_google_android_gms_internal_ads_zzet.zzge()) {
                this.zzaen.remove(com_google_android_gms_internal_ads_zzet);
                Iterator it = this.zzaem.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == com_google_android_gms_internal_ads_zzet) {
                        it.remove();
                    }
                }
            }
        }
    }

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn, zzajh com_google_android_gms_internal_ads_zzajh) {
        zza(com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh.zzbyo.getView());
    }

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn, zzajh com_google_android_gms_internal_ads_zzajh, View view) {
        zza(com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzajh, new zzez(view, com_google_android_gms_internal_ads_zzajh), null);
    }

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn, zzajh com_google_android_gms_internal_ads_zzajh, View view, zzaqw com_google_android_gms_internal_ads_zzaqw) {
        zza(com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzajh, new zzez(view, com_google_android_gms_internal_ads_zzajh), com_google_android_gms_internal_ads_zzaqw);
    }

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn, zzajh com_google_android_gms_internal_ads_zzajh, zzgd com_google_android_gms_internal_ads_zzgd, @Nullable zzaqw com_google_android_gms_internal_ads_zzaqw) {
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet;
            if (zzg(com_google_android_gms_internal_ads_zzajh)) {
                com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            } else {
                com_google_android_gms_internal_ads_zzet = new zzet(this.zzaeo, com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzajh, this.zzyf, com_google_android_gms_internal_ads_zzgd);
                com_google_android_gms_internal_ads_zzet.zza((zzfa) this);
                this.zzaem.put(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzet);
                this.zzaen.add(com_google_android_gms_internal_ads_zzet);
            }
            if (com_google_android_gms_internal_ads_zzaqw != null) {
                com_google_android_gms_internal_ads_zzet.zza(new zzfb(com_google_android_gms_internal_ads_zzet, com_google_android_gms_internal_ads_zzaqw));
            } else {
                com_google_android_gms_internal_ads_zzet.zza(new zzff(com_google_android_gms_internal_ads_zzet, this.zzaep, this.zzaeo));
            }
        }
    }

    public final void zzh(zzajh com_google_android_gms_internal_ads_zzajh) {
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            if (com_google_android_gms_internal_ads_zzet != null) {
                com_google_android_gms_internal_ads_zzet.zzgc();
            }
        }
    }

    public final void zzi(zzajh com_google_android_gms_internal_ads_zzajh) {
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            if (com_google_android_gms_internal_ads_zzet != null) {
                com_google_android_gms_internal_ads_zzet.stop();
            }
        }
    }

    public final void zzj(zzajh com_google_android_gms_internal_ads_zzajh) {
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            if (com_google_android_gms_internal_ads_zzet != null) {
                com_google_android_gms_internal_ads_zzet.pause();
            }
        }
    }

    public final void zzk(zzajh com_google_android_gms_internal_ads_zzajh) {
        synchronized (this.mLock) {
            zzet com_google_android_gms_internal_ads_zzet = (zzet) this.zzaem.get(com_google_android_gms_internal_ads_zzajh);
            if (com_google_android_gms_internal_ads_zzet != null) {
                com_google_android_gms_internal_ads_zzet.resume();
            }
        }
    }
}
