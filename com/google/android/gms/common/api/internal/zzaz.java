package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzaz implements ResultCallback<Status> {
    private final /* synthetic */ zzav zzit;
    private final /* synthetic */ StatusPendingResult zziv;
    private final /* synthetic */ boolean zziw;
    private final /* synthetic */ GoogleApiClient zzix;

    zzaz(zzav com_google_android_gms_common_api_internal_zzav, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.zzit = com_google_android_gms_common_api_internal_zzav;
        this.zziv = statusPendingResult;
        this.zziw = z;
        this.zzix = googleApiClient;
    }

    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        Storage.getInstance(this.zzit.mContext).removeSavedDefaultGoogleSignInAccount();
        if (status.isSuccess() && this.zzit.isConnected()) {
            this.zzit.reconnect();
        }
        this.zziv.setResult(status);
        if (this.zziw) {
            this.zzix.disconnect();
        }
    }
}
