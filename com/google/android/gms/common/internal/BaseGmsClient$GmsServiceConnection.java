package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.IGmsServiceBroker.Stub;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class BaseGmsClient$GmsServiceConnection implements ServiceConnection {
    private final /* synthetic */ BaseGmsClient zzru;
    private final int zzrx;

    public BaseGmsClient$GmsServiceConnection(BaseGmsClient baseGmsClient, int i) {
        this.zzru = baseGmsClient;
        this.zzrx = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            BaseGmsClient.zza(this.zzru, 16);
            return;
        }
        synchronized (BaseGmsClient.zza(this.zzru)) {
            BaseGmsClient.zza(this.zzru, Stub.asInterface(iBinder));
        }
        this.zzru.onPostServiceBindingHandler(0, null, this.zzrx);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (BaseGmsClient.zza(this.zzru)) {
            BaseGmsClient.zza(this.zzru, null);
        }
        this.zzru.mHandler.sendMessage(this.zzru.mHandler.obtainMessage(6, this.zzrx, 1));
    }
}
