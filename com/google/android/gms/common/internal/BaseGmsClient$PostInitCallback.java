package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

protected final class BaseGmsClient$PostInitCallback extends BaseGmsClient$zza {
    public final IBinder service;
    private final /* synthetic */ BaseGmsClient zzru;

    @BinderThread
    public BaseGmsClient$PostInitCallback(BaseGmsClient baseGmsClient, int i, IBinder iBinder, Bundle bundle) {
        this.zzru = baseGmsClient;
        super(baseGmsClient, i, bundle);
        this.service = iBinder;
    }

    protected final void handleServiceFailure(ConnectionResult connectionResult) {
        if (BaseGmsClient.zzg(this.zzru) != null) {
            BaseGmsClient.zzg(this.zzru).onConnectionFailed(connectionResult);
        }
        this.zzru.onConnectionFailed(connectionResult);
    }

    protected final boolean handleServiceSuccess() {
        try {
            String interfaceDescriptor = this.service.getInterfaceDescriptor();
            if (this.zzru.getServiceDescriptor().equals(interfaceDescriptor)) {
                IInterface createServiceInterface = this.zzru.createServiceInterface(this.service);
                if (createServiceInterface == null) {
                    return false;
                }
                if (!BaseGmsClient.zza(this.zzru, 2, 4, createServiceInterface) && !BaseGmsClient.zza(this.zzru, 3, 4, createServiceInterface)) {
                    return false;
                }
                BaseGmsClient.zza(this.zzru, null);
                Bundle connectionHint = this.zzru.getConnectionHint();
                if (BaseGmsClient.zze(this.zzru) != null) {
                    BaseGmsClient.zze(this.zzru).onConnected(connectionHint);
                }
                return true;
            }
            String serviceDescriptor = this.zzru.getServiceDescriptor();
            Log.e("GmsClient", new StringBuilder((String.valueOf(serviceDescriptor).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(serviceDescriptor).append(" vs. ").append(interfaceDescriptor).toString());
            return false;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
