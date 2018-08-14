package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpb;
import java.util.List;

final class zzbf implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ zzpb zzaaj;
    private final /* synthetic */ List zzaak;

    zzbf(zzbc com_google_android_gms_ads_internal_zzbc, zzpb com_google_android_gms_internal_ads_zzpb, int i, List list) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzaaj = com_google_android_gms_internal_ads_zzpb;
        this.zzaag = i;
        this.zzaak = list;
    }

    public final void run() {
        boolean z = true;
        try {
            zzbc com_google_android_gms_ads_internal_zzbc;
            Object zzb;
            if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadg != null) {
                com_google_android_gms_ads_internal_zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                com_google_android_gms_ads_internal_zzbc.zzvu = z;
                zzb = zzbc.zzb(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb);
                this.zzaaf.zzb(zzb.zzka());
            } else if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadf != null) {
                com_google_android_gms_ads_internal_zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                com_google_android_gms_ads_internal_zzbc.zzvu = z;
                zzoq com_google_android_gms_internal_ads_zzoq = (zzoq) this.zzaaj;
                this.zzaaf.zzvw.zzadf.zza(com_google_android_gms_internal_ads_zzoq);
                this.zzaaf.zzb(com_google_android_gms_internal_ads_zzoq.zzka());
            } else if ((this.zzaaj instanceof zzoo) && this.zzaaf.zzvw.zzadg != null) {
                com_google_android_gms_ads_internal_zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                com_google_android_gms_ads_internal_zzbc.zzvu = z;
                zzb = zzbc.zzb(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb);
                this.zzaaf.zzb(zzb.zzka());
            } else if (!(this.zzaaj instanceof zzoo) || this.zzaaf.zzvw.zzade == null) {
                zza com_google_android_gms_ads_internal_zza = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                com_google_android_gms_ads_internal_zza.zzc(3, z);
            } else {
                com_google_android_gms_ads_internal_zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                com_google_android_gms_ads_internal_zzbc.zzvu = z;
                zzoo com_google_android_gms_internal_ads_zzoo = (zzoo) this.zzaaj;
                this.zzaaf.zzvw.zzade.zza(com_google_android_gms_internal_ads_zzoo);
                this.zzaaf.zzb(com_google_android_gms_internal_ads_zzoo.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
