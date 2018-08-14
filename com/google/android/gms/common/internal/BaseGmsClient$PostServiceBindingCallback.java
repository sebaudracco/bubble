package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

protected final class BaseGmsClient$PostServiceBindingCallback extends BaseGmsClient$zza {
    private final /* synthetic */ BaseGmsClient zzru;

    @BinderThread
    public BaseGmsClient$PostServiceBindingCallback(BaseGmsClient baseGmsClient, @Nullable int i, Bundle bundle) {
        this.zzru = baseGmsClient;
        super(baseGmsClient, i, bundle);
    }

    protected final void handleServiceFailure(ConnectionResult connectionResult) {
        this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
        this.zzru.onConnectionFailed(connectionResult);
    }

    protected final boolean handleServiceSuccess() {
        this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
        return true;
    }
}
