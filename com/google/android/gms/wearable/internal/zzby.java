package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi.DataItemResult;

final class zzby extends zzn<DataItemResult> {
    private final /* synthetic */ Uri zzco;

    zzby(zzbw com_google_android_gms_wearable_internal_zzbw, GoogleApiClient googleApiClient, Uri uri) {
        this.zzco = uri;
        super(googleApiClient);
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new zzcg(status, null);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzhg com_google_android_gms_wearable_internal_zzhg = (zzhg) anyClient;
        ((zzep) com_google_android_gms_wearable_internal_zzhg.getService()).zza(new zzgv(this), this.zzco);
    }
}
