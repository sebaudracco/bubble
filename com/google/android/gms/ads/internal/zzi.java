package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzxn;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzi extends zzd implements zzaf, zzaam {
    private boolean zzwl;

    public zzi(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        super(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
    }

    protected zzaqw zza(zzaji com_google_android_gms_internal_ads_zzaji, @Nullable zzx com_google_android_gms_ads_internal_zzx, @Nullable zzait com_google_android_gms_internal_ads_zzait) throws zzarg {
        View nextView = this.zzvw.zzacs.getNextView();
        if (nextView instanceof zzaqw) {
            ((zzaqw) nextView).destroy();
        }
        if (nextView != null) {
            this.zzvw.zzacs.removeView(nextView);
        }
        zzbv.zzel();
        zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, com_google_android_gms_internal_ads_zzaji.zzcoq);
        if (this.zzvw.zzacv.zzard == null) {
            zzg(zza.getView());
        }
        zza.zzuf().zza(this, this, this, this, this, false, null, com_google_android_gms_ads_internal_zzx, this, com_google_android_gms_internal_ads_zzait);
        zza(zza);
        zza.zzdr(com_google_android_gms_internal_ads_zzaji.zzcgs.zzcdi);
        return zza;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzbp();
    }

    protected void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx) {
        if (com_google_android_gms_internal_ads_zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzk(this, com_google_android_gms_internal_ads_zzaji));
            return;
        }
        if (com_google_android_gms_internal_ads_zzaji.zzacv != null) {
            this.zzvw.zzacv = com_google_android_gms_internal_ads_zzaji.zzacv;
        }
        if (!com_google_android_gms_internal_ads_zzaji.zzcos.zzceq || com_google_android_gms_internal_ads_zzaji.zzcos.zzarg) {
            zzakk.zzcrm.post(new zzl(this, com_google_android_gms_internal_ads_zzaji, this.zzwc.zzxa.zza(this.zzvw.zzrt, this.zzvw.zzacr, com_google_android_gms_internal_ads_zzaji.zzcos), com_google_android_gms_internal_ads_zznx));
            return;
        }
        this.zzvw.zzadv = 0;
        zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
        zzbv.zzej();
        com_google_android_gms_ads_internal_zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, com_google_android_gms_internal_ads_zzaji, this.zzvw.zzacq, null, this.zzwh, this, com_google_android_gms_internal_ads_zznx);
    }

    protected final void zza(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        com_google_android_gms_internal_ads_zzaqw.zza("/trackActiveViewUnit", new zzj(this));
    }

    public final void zza(zzod com_google_android_gms_internal_ads_zzod) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzvw.zzado = com_google_android_gms_internal_ads_zzod;
    }

    protected boolean zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().zzdb(com_google_android_gms_internal_ads_zzajh2.zzcev);
        }
        try {
            if (!(com_google_android_gms_internal_ads_zzajh2.zzbyo == null || com_google_android_gms_internal_ads_zzajh2.zzceq || !com_google_android_gms_internal_ads_zzajh2.zzcor)) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbfq)).booleanValue() && !com_google_android_gms_internal_ads_zzajh2.zzccv.extras.containsKey("sdk_less_server_data")) {
                    try {
                        com_google_android_gms_internal_ads_zzajh2.zzbyo.zzus();
                    } catch (Throwable th) {
                        zzakb.m3428v("Could not render test Ad label.");
                    }
                }
            }
        } catch (RuntimeException e) {
            zzakb.m3428v("Could not render test AdLabel.");
        }
        return super.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2);
    }

    @VisibleForTesting
    final void zzb(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        if (this.zzvw.zzacw != null) {
            this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw, com_google_android_gms_internal_ads_zzaqw.getView(), com_google_android_gms_internal_ads_zzaqw);
            this.zzwl = false;
            return;
        }
        this.zzwl = true;
        zzane.zzdk("Request to enable ActiveView before adState is available.");
    }

    protected void zzbq() {
        super.zzbq();
        if (this.zzwl) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcb)).booleanValue()) {
                zzb(this.zzvw.zzacw.zzbyo);
            }
        }
    }

    public final void zzcn() {
        onAdClicked();
    }

    public final void zzco() {
        recordImpression();
        zzbm();
    }

    protected final boolean zzcp() {
        return (this.zzvw.zzacx == null || this.zzvw.zzacx.zzcos == null || !this.zzvw.zzacx.zzcos.zzcfp) ? false : true;
    }

    public final void zzcq() {
        zzbn();
    }

    public final void zzh(View view) {
        this.zzvw.zzadu = view;
        zzb(new zzajh(this.zzvw.zzacx, null, null, null, null, null, null, null));
    }
}
