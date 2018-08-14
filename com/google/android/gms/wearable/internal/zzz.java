package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wearable.CapabilityApi.CapabilityListener;

final class zzz extends zzn<Status> {
    private CapabilityListener zzbs;

    private zzz(GoogleApiClient googleApiClient, CapabilityListener capabilityListener) {
        super(googleApiClient);
        this.zzbs = capabilityListener;
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        this.zzbs = null;
        return status;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzhg) anyClient).zza((ResultHolder) this, this.zzbs);
        this.zzbs = null;
    }
}
