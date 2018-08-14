package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

final class BaseGmsClient$zzb extends Handler {
    private final /* synthetic */ BaseGmsClient zzru;

    public BaseGmsClient$zzb(BaseGmsClient baseGmsClient, Looper looper) {
        this.zzru = baseGmsClient;
        super(looper);
    }

    private static void zza(Message message) {
        BaseGmsClient$CallbackProxy baseGmsClient$CallbackProxy = (BaseGmsClient$CallbackProxy) message.obj;
        baseGmsClient$CallbackProxy.onDeliverCallbackFailed();
        baseGmsClient$CallbackProxy.unregister();
    }

    private static boolean zzb(Message message) {
        return message.what == 2 || message.what == 1 || message.what == 7;
    }

    public final void handleMessage(Message message) {
        PendingIntent pendingIntent = null;
        if (this.zzru.mDisconnectCount.get() != message.arg1) {
            if (zzb(message)) {
                zza(message);
            }
        } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !this.zzru.isConnecting()) {
            zza(message);
        } else if (message.what == 4) {
            BaseGmsClient.zza(this.zzru, new ConnectionResult(message.arg2));
            if (!BaseGmsClient.zzb(this.zzru) || BaseGmsClient.zzc(this.zzru)) {
                r0 = BaseGmsClient.zzd(this.zzru) != null ? BaseGmsClient.zzd(this.zzru) : new ConnectionResult(8);
                this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(r0);
                this.zzru.onConnectionFailed(r0);
                return;
            }
            BaseGmsClient.zza(this.zzru, 3, null);
        } else if (message.what == 5) {
            r0 = BaseGmsClient.zzd(this.zzru) != null ? BaseGmsClient.zzd(this.zzru) : new ConnectionResult(8);
            this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(r0);
            this.zzru.onConnectionFailed(r0);
        } else if (message.what == 3) {
            if (message.obj instanceof PendingIntent) {
                pendingIntent = (PendingIntent) message.obj;
            }
            ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
            this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
            this.zzru.onConnectionFailed(connectionResult);
        } else if (message.what == 6) {
            BaseGmsClient.zza(this.zzru, 5, null);
            if (BaseGmsClient.zze(this.zzru) != null) {
                BaseGmsClient.zze(this.zzru).onConnectionSuspended(message.arg2);
            }
            this.zzru.onConnectionSuspended(message.arg2);
            BaseGmsClient.zza(this.zzru, 5, 1, null);
        } else if (message.what == 2 && !this.zzru.isConnected()) {
            zza(message);
        } else if (zzb(message)) {
            ((BaseGmsClient$CallbackProxy) message.obj).deliverCallback();
        } else {
            Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
        }
    }
}
