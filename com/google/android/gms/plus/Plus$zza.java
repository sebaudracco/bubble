package com.google.android.gms.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.plus.internal.zzh;

public abstract class Plus$zza<R extends Result> extends ApiMethodImpl<R, zzh> {
    public Plus$zza(GoogleApiClient googleApiClient) {
        super(Plus.CLIENT_KEY, googleApiClient);
    }
}
