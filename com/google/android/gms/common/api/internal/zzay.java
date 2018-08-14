package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;

final class zzay implements OnConnectionFailedListener {
    private final /* synthetic */ StatusPendingResult zziv;

    zzay(zzav com_google_android_gms_common_api_internal_zzav, StatusPendingResult statusPendingResult) {
        this.zziv = statusPendingResult;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zziv.setResult(new Status(8));
    }
}
