package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener;

final class zzbh implements BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zzjy;

    zzbh(GoogleApiManager googleApiManager) {
        this.zzjy = googleApiManager;
    }

    public final void onBackgroundStateChanged(boolean z) {
        GoogleApiManager.zza(this.zzjy).sendMessage(GoogleApiManager.zza(this.zzjy).obtainMessage(1, Boolean.valueOf(z)));
    }
}
