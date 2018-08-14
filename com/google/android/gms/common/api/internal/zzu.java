package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzu implements zzbq {
    private final /* synthetic */ zzr zzgc;

    private zzu(zzr com_google_android_gms_common_api_internal_zzr) {
        this.zzgc = com_google_android_gms_common_api_internal_zzr;
    }

    public final void zzb(int i, boolean z) {
        this.zzgc.zzga.lock();
        try {
            if (this.zzgc.zzfz) {
                this.zzgc.zzfz = false;
                this.zzgc.zza(i, z);
                return;
            }
            this.zzgc.zzfz = true;
            this.zzgc.zzfr.onConnectionSuspended(i);
            this.zzgc.zzga.unlock();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }

    public final void zzb(@Nullable Bundle bundle) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzfy = ConnectionResult.RESULT_SUCCESS;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }

    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzfy = connectionResult;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }
}
