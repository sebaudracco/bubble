package com.google.android.gms.wallet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class Wallet$zzb extends Wallet$zza<Status> {
    public Wallet$zzb(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }
}
