package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzadh
public final class zzabt extends zzajx {
    private final Object mLock;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    private final zzaej zzbzf;
    private final zzabv zzbzu;
    private Future<zzajh> zzbzv;

    public zzabt(Context context, zzbc com_google_android_gms_ads_internal_zzbc, zzaji com_google_android_gms_internal_ads_zzaji, zzci com_google_android_gms_internal_ads_zzci, zzabm com_google_android_gms_internal_ads_zzabm, zznx com_google_android_gms_internal_ads_zznx) {
        this(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzabm, new zzabv(context, com_google_android_gms_ads_internal_zzbc, new zzalt(context), com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zznx));
    }

    private zzabt(zzaji com_google_android_gms_internal_ads_zzaji, zzabm com_google_android_gms_internal_ads_zzabm, zzabv com_google_android_gms_internal_ads_zzabv) {
        this.mLock = new Object();
        this.zzbze = com_google_android_gms_internal_ads_zzaji;
        this.zzbzf = com_google_android_gms_internal_ads_zzaji.zzcos;
        this.zzbzd = com_google_android_gms_internal_ads_zzabm;
        this.zzbzu = com_google_android_gms_internal_ads_zzabv;
    }

    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzbzv != null) {
                this.zzbzv.cancel(true);
            }
        }
    }

    public final void zzdn() {
        zzajh com_google_android_gms_internal_ads_zzajh;
        int i = -2;
        try {
            synchronized (this.mLock) {
                this.zzbzv = zzaki.zza(this.zzbzu);
            }
            com_google_android_gms_internal_ads_zzajh = (zzajh) this.zzbzv.get(60000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            zzane.zzdk("Timed out waiting for native ad.");
            i = 2;
            this.zzbzv.cancel(true);
            com_google_android_gms_internal_ads_zzajh = null;
        } catch (ExecutionException e2) {
            i = 0;
            com_google_android_gms_internal_ads_zzajh = null;
        } catch (InterruptedException e3) {
            i = 0;
            com_google_android_gms_internal_ads_zzajh = null;
        } catch (CancellationException e4) {
            i = 0;
            com_google_android_gms_internal_ads_zzajh = null;
        }
        if (com_google_android_gms_internal_ads_zzajh == null) {
            com_google_android_gms_internal_ads_zzajh = new zzajh(this.zzbze.zzcgs.zzccv, null, null, i, null, null, this.zzbzf.orientation, this.zzbzf.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, null, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, false, this.zzbze.zzcos.zzcfp, null, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
        }
        zzakk.zzcrm.post(new zzabu(this, com_google_android_gms_internal_ads_zzajh));
    }
}
