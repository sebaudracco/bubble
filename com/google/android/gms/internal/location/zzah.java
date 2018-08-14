package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.location.zzal;

final class zzah extends zzai {
    private final /* synthetic */ zzal zzct;

    zzah(zzaf com_google_android_gms_internal_location_zzaf, GoogleApiClient googleApiClient, zzal com_google_android_gms_location_zzal) {
        this.zzct = com_google_android_gms_location_zzal;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzct, (ResultHolder) this);
    }
}
