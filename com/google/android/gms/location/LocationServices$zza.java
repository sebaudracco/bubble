package com.google.android.gms.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.internal.location.zzaz;

public abstract class LocationServices$zza<R extends Result> extends ApiMethodImpl<R, zzaz> {
    public LocationServices$zza(GoogleApiClient googleApiClient) {
        super(LocationServices.API, googleApiClient);
    }
}
