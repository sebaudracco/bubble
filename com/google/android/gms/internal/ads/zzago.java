package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzago {
    private static final zzxm zzcku = new zzxm();
    private final zzxn zzckv;
    private final zzbw zzckw;
    private final Map<String, zzaib> zzckx = new HashMap();
    private final zzahu zzcky;
    private final zzb zzckz;
    private final zzabm zzcla;

    public zzago(zzbw com_google_android_gms_ads_internal_zzbw, zzxn com_google_android_gms_internal_ads_zzxn, zzahu com_google_android_gms_internal_ads_zzahu, zzb com_google_android_gms_ads_internal_gmsg_zzb, zzabm com_google_android_gms_internal_ads_zzabm) {
        this.zzckw = com_google_android_gms_ads_internal_zzbw;
        this.zzckv = com_google_android_gms_internal_ads_zzxn;
        this.zzcky = com_google_android_gms_internal_ads_zzahu;
        this.zzckz = com_google_android_gms_ads_internal_gmsg_zzb;
        this.zzcla = com_google_android_gms_internal_ads_zzabm;
    }

    public static boolean zza(zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        return true;
    }

    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib com_google_android_gms_internal_ads_zzaib = (zzaib) this.zzckx.get(str);
                if (!(com_google_android_gms_internal_ads_zzaib == null || com_google_android_gms_internal_ads_zzaib.zzpe() == null)) {
                    com_google_android_gms_internal_ads_zzaib.zzpe().destroy();
                }
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onContextChanged(@NonNull Context context) {
        for (zzaib zzpe : this.zzckx.values()) {
            try {
                zzpe.zzpe().zzi(ObjectWrapper.wrap(context));
            } catch (Throwable e) {
                zzane.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib com_google_android_gms_internal_ads_zzaib = (zzaib) this.zzckx.get(str);
                if (!(com_google_android_gms_internal_ads_zzaib == null || com_google_android_gms_internal_ads_zzaib.zzpe() == null)) {
                    com_google_android_gms_internal_ads_zzaib.zzpe().pause();
                }
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib com_google_android_gms_internal_ads_zzaib = (zzaib) this.zzckx.get(str);
                if (!(com_google_android_gms_internal_ads_zzaib == null || com_google_android_gms_internal_ads_zzaib.zzpe() == null)) {
                    com_google_android_gms_internal_ads_zzaib.zzpe().resume();
                }
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    @Nullable
    public final zzaib zzca(String str) {
        Throwable th;
        String str2;
        String valueOf;
        zzaib com_google_android_gms_internal_ads_zzaib = (zzaib) this.zzckx.get(str);
        if (com_google_android_gms_internal_ads_zzaib != null) {
            return com_google_android_gms_internal_ads_zzaib;
        }
        zzaib com_google_android_gms_internal_ads_zzaib2;
        try {
            com_google_android_gms_internal_ads_zzaib2 = new zzaib(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzcku : this.zzckv).zzbm(str), this.zzcky);
            try {
                this.zzckx.put(str, com_google_android_gms_internal_ads_zzaib2);
                return com_google_android_gms_internal_ads_zzaib2;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                zzane.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return com_google_android_gms_internal_ads_zzaib2;
            }
        } catch (Throwable e2) {
            th = e2;
            com_google_android_gms_internal_ads_zzaib2 = com_google_android_gms_internal_ads_zzaib;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            zzane.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return com_google_android_gms_internal_ads_zzaib2;
        }
    }

    public final zzaig zzd(zzaig com_google_android_gms_internal_ads_zzaig) {
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzcod == null || TextUtils.isEmpty(this.zzckw.zzacw.zzcod.zzbsv))) {
            com_google_android_gms_internal_ads_zzaig = new zzaig(this.zzckw.zzacw.zzcod.zzbsv, this.zzckw.zzacw.zzcod.zzbsw);
        }
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzbtw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw.zzbtw.zzbsd, this.zzckw.zzadr, com_google_android_gms_internal_ads_zzaig);
        }
        return com_google_android_gms_internal_ads_zzaig;
    }

    public final zzb zzos() {
        return this.zzckz;
    }

    public final zzabm zzot() {
        return this.zzcla;
    }

    public final void zzou() {
        this.zzckw.zzadv = 0;
        zzbw com_google_android_gms_ads_internal_zzbw = this.zzckw;
        zzbv.zzej();
        zzalc com_google_android_gms_internal_ads_zzahx = new zzahx(this.zzckw.zzrt, this.zzckw.zzacx, this);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzahx.getClass().getName());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        com_google_android_gms_internal_ads_zzahx.zznt();
        com_google_android_gms_ads_internal_zzbw.zzacu = com_google_android_gms_internal_ads_zzahx;
    }

    public final void zzov() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbsc);
        }
    }

    public final void zzow() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbse);
        }
    }

    public final void zzw(boolean z) {
        zzaib zzca = zzca(this.zzckw.zzacw.zzbty);
        if (zzca != null && zzca.zzpe() != null) {
            try {
                zzca.zzpe().setImmersiveMode(z);
                zzca.zzpe().showVideo();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
