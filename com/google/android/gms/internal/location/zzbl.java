package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.location.LocationServices$zza;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

final class zzbl extends LocationServices$zza<LocationSettingsResult> {
    private final /* synthetic */ LocationSettingsRequest zzdp;
    private final /* synthetic */ String zzdq = null;

    zzbl(zzbk com_google_android_gms_internal_location_zzbk, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        this.zzdp = locationSettingsRequest;
        super(googleApiClient);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return new LocationSettingsResult(status);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzdp, (ResultHolder) this, this.zzdq);
    }
}
