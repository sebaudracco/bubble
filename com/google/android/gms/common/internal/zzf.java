package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

final class zzf implements BaseGmsClient$BaseConnectionCallbacks {
    private final /* synthetic */ ConnectionCallbacks zztd;

    zzf(ConnectionCallbacks connectionCallbacks) {
        this.zztd = connectionCallbacks;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        this.zztd.onConnected(bundle);
    }

    public final void onConnectionSuspended(int i) {
        this.zztd.onConnectionSuspended(i);
    }
}
