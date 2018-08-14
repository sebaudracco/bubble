package com.google.android.gms.wallet;

import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.internal.wallet.zzad;

public abstract class Wallet$zza<R extends Result> extends ApiMethodImpl<R, zzad> {
    public Wallet$zza(GoogleApiClient googleApiClient) {
        super(Wallet.API, googleApiClient);
    }

    @VisibleForTesting
    protected /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzad) anyClient);
    }

    @VisibleForTesting
    protected abstract void zza(zzad com_google_android_gms_internal_wallet_zzad) throws RemoteException;
}
