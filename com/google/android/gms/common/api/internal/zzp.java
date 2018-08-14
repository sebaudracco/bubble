package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;

public final class zzp implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api<?> mApi;
    private final boolean zzfo;
    private zzq zzfp;

    public zzp(Api<?> api, boolean z) {
        this.mApi = api;
        this.zzfo = z;
    }

    private final void zzy() {
        Preconditions.checkNotNull(this.zzfp, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    public final void onConnected(@Nullable Bundle bundle) {
        zzy();
        this.zzfp.onConnected(bundle);
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzy();
        this.zzfp.zza(connectionResult, this.mApi, this.zzfo);
    }

    public final void onConnectionSuspended(int i) {
        zzy();
        this.zzfp.onConnectionSuspended(i);
    }

    public final void zza(zzq com_google_android_gms_common_api_internal_zzq) {
        this.zzfp = com_google_android_gms_common_api_internal_zzq;
    }
}
