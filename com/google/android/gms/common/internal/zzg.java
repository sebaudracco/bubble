package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzg implements BaseGmsClient$BaseOnConnectionFailedListener {
    private final /* synthetic */ OnConnectionFailedListener zzte;

    zzg(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzte = onConnectionFailedListener;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzte.onConnectionFailed(connectionResult);
    }
}
