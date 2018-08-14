package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

final class zzax implements ConnectionCallbacks {
    private final /* synthetic */ zzav zzit;
    private final /* synthetic */ AtomicReference zziu;
    private final /* synthetic */ StatusPendingResult zziv;

    zzax(zzav com_google_android_gms_common_api_internal_zzav, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.zzit = com_google_android_gms_common_api_internal_zzav;
        this.zziu = atomicReference;
        this.zziv = statusPendingResult;
    }

    public final void onConnected(Bundle bundle) {
        this.zzit.zza((GoogleApiClient) this.zziu.get(), this.zziv, true);
    }

    public final void onConnectionSuspended(int i) {
    }
}
