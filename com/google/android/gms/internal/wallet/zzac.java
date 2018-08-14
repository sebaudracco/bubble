package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet$zza;

final class zzac extends Wallet$zza<BooleanResult> {
    private final /* synthetic */ IsReadyToPayRequest zzef;

    zzac(zzw com_google_android_gms_internal_wallet_zzw, GoogleApiClient googleApiClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzef = isReadyToPayRequest;
        super(googleApiClient);
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new BooleanResult(status, false);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzad) anyClient);
    }

    protected final void zza(zzad com_google_android_gms_internal_wallet_zzad) {
        com_google_android_gms_internal_wallet_zzad.zza(this.zzef, (ResultHolder) this);
    }
}
