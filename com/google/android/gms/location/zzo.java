package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends UnregisterListenerMethod<zzaz, LocationCallback> {
    private final /* synthetic */ FusedLocationProviderClient zzaa;

    zzo(FusedLocationProviderClient fusedLocationProviderClient, ListenerKey listenerKey) {
        this.zzaa = fusedLocationProviderClient;
        super(listenerKey);
    }

    protected final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        try {
            ((zzaz) anyClient).zzb(getListenerKey(), this.zzaa.zza(taskCompletionSource));
        } catch (Exception e) {
            taskCompletionSource.trySetException(e);
        }
    }
}
