package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzmu;

@zzadh
public final class VideoOptions {
    private final boolean zzuz;
    private final boolean zzva;
    private final boolean zzvb;

    private VideoOptions(Builder builder) {
        this.zzuz = Builder.zza(builder);
        this.zzva = Builder.zzb(builder);
        this.zzvb = Builder.zzc(builder);
    }

    public VideoOptions(zzmu com_google_android_gms_internal_ads_zzmu) {
        this.zzuz = com_google_android_gms_internal_ads_zzmu.zzato;
        this.zzva = com_google_android_gms_internal_ads_zzmu.zzatp;
        this.zzvb = com_google_android_gms_internal_ads_zzmu.zzatq;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzvb;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzva;
    }

    public final boolean getStartMuted() {
        return this.zzuz;
    }
}
