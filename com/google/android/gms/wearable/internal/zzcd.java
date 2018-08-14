package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi.GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemAsset;

final class zzcd extends zzn<GetFdForAssetResult> {
    private final /* synthetic */ DataItemAsset zzde;

    zzcd(zzbw com_google_android_gms_wearable_internal_zzbw, GoogleApiClient googleApiClient, DataItemAsset dataItemAsset) {
        this.zzde = dataItemAsset;
        super(googleApiClient);
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new zzci(status, null);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzhg) anyClient).zza((ResultHolder) this, Asset.createFromRef(this.zzde.getId()));
    }
}
