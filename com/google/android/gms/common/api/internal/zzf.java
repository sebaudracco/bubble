package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf<ResultT> extends zzb {
    private final TaskCompletionSource<ResultT> zzdu;
    private final TaskApiCall<AnyClient, ResultT> zzdy;
    private final StatusExceptionMapper zzdz;

    public zzf(int i, TaskApiCall<AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zzdu = taskCompletionSource;
        this.zzdy = taskApiCall;
        this.zzdz = statusExceptionMapper;
    }

    @Nullable
    public final Feature[] getRequiredFeatures() {
        return this.zzdy.zzca();
    }

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zzdy.shouldAutoResolveMissingFeatures();
    }

    public final void zza(@NonNull Status status) {
        this.zzdu.trySetException(this.zzdz.getException(status));
    }

    public final void zza(zza<?> com_google_android_gms_common_api_internal_GoogleApiManager_zza_) throws DeadObjectException {
        try {
            this.zzdy.doExecute(com_google_android_gms_common_api_internal_GoogleApiManager_zza_.zzae(), this.zzdu);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zza(zzb.zza(e2));
        } catch (RuntimeException e3) {
            zza(e3);
        }
    }

    public final void zza(@NonNull zzaa com_google_android_gms_common_api_internal_zzaa, boolean z) {
        com_google_android_gms_common_api_internal_zzaa.zza(this.zzdu, z);
    }

    public final void zza(@NonNull RuntimeException runtimeException) {
        this.zzdu.trySetException(runtimeException);
    }
}
