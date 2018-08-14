package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzny;
import com.google.android.gms.internal.ads.zzoa;

final class zzl implements Runnable {
    final /* synthetic */ zzaji zzwg;
    final /* synthetic */ zzi zzwm;
    final /* synthetic */ zzait zzwn;
    private final /* synthetic */ zznx zzwo;

    zzl(zzi com_google_android_gms_ads_internal_zzi, zzaji com_google_android_gms_internal_ads_zzaji, zzait com_google_android_gms_internal_ads_zzait, zznx com_google_android_gms_internal_ads_zznx) {
        this.zzwm = com_google_android_gms_ads_internal_zzi;
        this.zzwg = com_google_android_gms_internal_ads_zzaji;
        this.zzwn = com_google_android_gms_internal_ads_zzait;
        this.zzwo = com_google_android_gms_internal_ads_zznx;
    }

    public final void run() {
        if (this.zzwg.zzcos.zzcez && this.zzwm.zzvw.zzado != null) {
            String str = null;
            if (this.zzwg.zzcos.zzbyq != null) {
                zzbv.zzek();
                str = zzakk.zzcu(this.zzwg.zzcos.zzbyq);
            }
            zzoa com_google_android_gms_internal_ads_zzny = new zzny(this.zzwm, str, this.zzwg.zzcos.zzceo);
            this.zzwm.zzvw.zzadv = 1;
            try {
                this.zzwm.zzvu = false;
                this.zzwm.zzvw.zzado.zza(com_google_android_gms_internal_ads_zzny);
                return;
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
                this.zzwm.zzvu = true;
            }
        }
        zzx com_google_android_gms_ads_internal_zzx = new zzx(this.zzwm.zzvw.zzrt, this.zzwn, this.zzwg.zzcos.zzcfi);
        try {
            zzaqw zza = this.zzwm.zza(this.zzwg, com_google_android_gms_ads_internal_zzx, this.zzwn);
            zza.setOnTouchListener(new zzn(this, com_google_android_gms_ads_internal_zzx));
            zza.setOnClickListener(new zzo(this, com_google_android_gms_ads_internal_zzx));
            this.zzwm.zzvw.zzadv = 0;
            zzbw com_google_android_gms_ads_internal_zzbw = this.zzwm.zzvw;
            zzbv.zzej();
            com_google_android_gms_ads_internal_zzbw.zzacu = zzabl.zza(this.zzwm.zzvw.zzrt, this.zzwm, this.zzwg, this.zzwm.zzvw.zzacq, zza, this.zzwm.zzwh, this.zzwm, this.zzwo);
        } catch (Throwable e2) {
            zzakb.zzb("Could not obtain webview.", e2);
            zzakk.zzcrm.post(new zzm(this));
        }
    }
}
