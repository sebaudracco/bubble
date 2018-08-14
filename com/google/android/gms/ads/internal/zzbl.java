package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbl {
    private final zzbn zzaan;
    @Nullable
    private zzjj zzaao;
    private boolean zzaap;
    private boolean zzaaq;
    private long zzaar;
    private final Runnable zzy;

    public zzbl(zza com_google_android_gms_ads_internal_zza) {
        this(com_google_android_gms_ads_internal_zza, new zzbn(zzakk.zzcrm));
    }

    @VisibleForTesting
    private zzbl(zza com_google_android_gms_ads_internal_zza, zzbn com_google_android_gms_ads_internal_zzbn) {
        this.zzaap = false;
        this.zzaaq = false;
        this.zzaar = 0;
        this.zzaan = com_google_android_gms_ads_internal_zzbn;
        this.zzy = new zzbm(this, new WeakReference(com_google_android_gms_ads_internal_zza));
    }

    public final void cancel() {
        this.zzaap = false;
        this.zzaan.removeCallbacks(this.zzy);
    }

    public final void pause() {
        this.zzaaq = true;
        if (this.zzaap) {
            this.zzaan.removeCallbacks(this.zzy);
        }
    }

    public final void resume() {
        this.zzaaq = false;
        if (this.zzaap) {
            this.zzaap = false;
            zza(this.zzaao, this.zzaar);
        }
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, long j) {
        if (this.zzaap) {
            zzane.zzdk("An ad refresh is already scheduled.");
            return;
        }
        this.zzaao = com_google_android_gms_internal_ads_zzjj;
        this.zzaap = true;
        this.zzaar = j;
        if (!this.zzaaq) {
            zzane.zzdj("Scheduling ad refresh " + j + " milliseconds from now.");
            this.zzaan.postDelayed(this.zzy, j);
        }
    }

    public final void zzdy() {
        this.zzaaq = false;
        this.zzaap = false;
        if (!(this.zzaao == null || this.zzaao.extras == null)) {
            this.zzaao.extras.remove("_ad");
        }
        zza(this.zzaao, 0);
    }

    public final boolean zzdz() {
        return this.zzaap;
    }

    public final void zzf(zzjj com_google_android_gms_internal_ads_zzjj) {
        this.zzaao = com_google_android_gms_internal_ads_zzjj;
    }

    public final void zzg(zzjj com_google_android_gms_internal_ads_zzjj) {
        zza(com_google_android_gms_internal_ads_zzjj, 60000);
    }
}
