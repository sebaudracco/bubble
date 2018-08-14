package com.google.android.gms.internal.clearcut;

import android.database.ContentObserver;
import android.os.Handler;

final class zzac extends ContentObserver {
    private final /* synthetic */ zzab zzdm;

    zzac(zzab com_google_android_gms_internal_clearcut_zzab, Handler handler) {
        this.zzdm = com_google_android_gms_internal_clearcut_zzab;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzdm.zzh();
        this.zzdm.zzj();
    }
}
