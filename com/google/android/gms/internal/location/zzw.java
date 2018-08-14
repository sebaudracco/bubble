package com.google.android.gms.internal.location;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzw extends zzab {
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationListener zzcl;
    private final /* synthetic */ Looper zzcp;

    zzw(zzq com_google_android_gms_internal_location_zzq, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        this.zzck = locationRequest;
        this.zzcl = locationListener;
        this.zzcp = looper;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzck, ListenerHolders.createListenerHolder(this.zzcl, zzbm.zza(this.zzcp), LocationListener.class.getSimpleName()), new zzac(this));
    }
}
