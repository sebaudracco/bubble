package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze extends zzc<Void> {
    private final RegisterListenerMethod<AnyClient, ?> zzdw;
    private final UnregisterListenerMethod<AnyClient, ?> zzdx;

    public zze(zzbv com_google_android_gms_common_api_internal_zzbv, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zzdw = com_google_android_gms_common_api_internal_zzbv.zzlt;
        this.zzdx = com_google_android_gms_common_api_internal_zzbv.zzlu;
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull zzaa com_google_android_gms_common_api_internal_zzaa, boolean z) {
    }

    public final void zzb(zza<?> com_google_android_gms_common_api_internal_GoogleApiManager_zza_) throws RemoteException {
        this.zzdw.registerListener(com_google_android_gms_common_api_internal_GoogleApiManager_zza_.zzae(), this.zzdu);
        if (this.zzdw.getListenerKey() != null) {
            com_google_android_gms_common_api_internal_GoogleApiManager_zza_.zzbn().put(this.zzdw.getListenerKey(), new zzbv(this.zzdw, this.zzdx));
        }
    }
}
