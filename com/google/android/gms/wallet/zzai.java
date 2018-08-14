package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends TaskApiCall<zzad, Boolean> {
    private final /* synthetic */ IsReadyToPayRequest zzef;

    zzai(PaymentsClient paymentsClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzef = isReadyToPayRequest;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzad) anyClient).zza(this.zzef, taskCompletionSource);
    }
}
