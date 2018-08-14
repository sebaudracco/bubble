package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

protected class BaseGmsClient$LegacyClientCallbackAdapter implements BaseGmsClient$ConnectionProgressReportCallbacks {
    private final /* synthetic */ BaseGmsClient zzru;

    public BaseGmsClient$LegacyClientCallbackAdapter(BaseGmsClient baseGmsClient) {
        this.zzru = baseGmsClient;
    }

    public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzru.getRemoteService(null, this.zzru.getScopes());
        } else if (BaseGmsClient.zzg(this.zzru) != null) {
            BaseGmsClient.zzg(this.zzru).onConnectionFailed(connectionResult);
        }
    }
}
