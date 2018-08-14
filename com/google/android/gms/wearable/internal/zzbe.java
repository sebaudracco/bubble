package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzbe extends zzn<Status> {
    private final /* synthetic */ zzay zzcm;
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ long zzcq;
    private final /* synthetic */ long zzcr;

    zzbe(zzay com_google_android_gms_wearable_internal_zzay, GoogleApiClient googleApiClient, Uri uri, long j, long j2) {
        this.zzcm = com_google_android_gms_wearable_internal_zzay;
        this.zzco = uri;
        this.zzcq = j;
        this.zzcr = j2;
        super(googleApiClient);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzhg) anyClient).zza((ResultHolder) this, this.zzcm.zzce, this.zzco, this.zzcq, this.zzcr);
    }
}
