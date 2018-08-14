package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

final class zzf extends ContentObserver {
    private final /* synthetic */ zzh zzagr;

    zzf(Handler handler, zzh com_google_android_gms_internal_stable_zzh) {
        this.zzagr = com_google_android_gms_internal_stable_zzh;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzagr.zzagu.set(true);
    }
}
