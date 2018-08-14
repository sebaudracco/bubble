package com.google.android.gms.phenotype;

import android.database.ContentObserver;
import android.os.Handler;

final class zzb extends ContentObserver {
    private final /* synthetic */ zza zzm;

    zzb(zza com_google_android_gms_phenotype_zza, Handler handler) {
        this.zzm = com_google_android_gms_phenotype_zza;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzm.zzb();
    }
}
