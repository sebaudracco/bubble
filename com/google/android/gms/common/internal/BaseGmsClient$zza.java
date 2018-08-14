package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

abstract class BaseGmsClient$zza extends BaseGmsClient$CallbackProxy<Boolean> {
    public final Bundle resolution;
    public final int statusCode;
    private final /* synthetic */ BaseGmsClient zzru;

    @BinderThread
    protected BaseGmsClient$zza(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
        this.zzru = baseGmsClient;
        super(baseGmsClient, Boolean.valueOf(true));
        this.statusCode = i;
        this.resolution = bundle;
    }

    protected void deliverCallback(Boolean bool) {
        PendingIntent pendingIntent = null;
        if (bool == null) {
            BaseGmsClient.zza(this.zzru, 1, null);
            return;
        }
        switch (this.statusCode) {
            case 0:
                if (!handleServiceSuccess()) {
                    BaseGmsClient.zza(this.zzru, 1, null);
                    handleServiceFailure(new ConnectionResult(8, null));
                    return;
                }
                return;
            case 10:
                BaseGmsClient.zza(this.zzru, 1, null);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                BaseGmsClient.zza(this.zzru, 1, null);
                if (this.resolution != null) {
                    pendingIntent = (PendingIntent) this.resolution.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                }
                handleServiceFailure(new ConnectionResult(this.statusCode, pendingIntent));
                return;
        }
    }

    protected abstract void handleServiceFailure(ConnectionResult connectionResult);

    protected abstract boolean handleServiceSuccess();

    protected void onDeliverCallbackFailed() {
    }
}
