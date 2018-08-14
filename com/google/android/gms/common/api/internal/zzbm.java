package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager.zza;

final class zzbm implements Runnable {
    private final /* synthetic */ zzbl zzkm;

    zzbm(zzbl com_google_android_gms_common_api_internal_zzbl) {
        this.zzkm = com_google_android_gms_common_api_internal_zzbl;
    }

    public final void run() {
        zza.zze(this.zzkm.zzkk).disconnect();
    }
}
