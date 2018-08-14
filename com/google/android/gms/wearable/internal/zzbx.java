package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.PutDataRequest;

final class zzbx extends zzn<DataItemResult> {
    private final /* synthetic */ PutDataRequest zzdb;

    zzbx(zzbw com_google_android_gms_wearable_internal_zzbw, GoogleApiClient googleApiClient, PutDataRequest putDataRequest) {
        this.zzdb = putDataRequest;
        super(googleApiClient);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzcg(status, null);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzhg) anyClient).zza((ResultHolder) this, this.zzdb);
    }
}
