package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

final class zzz extends zzab {
    private final /* synthetic */ LocationListener zzcl;

    zzz(zzq com_google_android_gms_internal_location_zzq, GoogleApiClient googleApiClient, LocationListener locationListener) {
        this.zzcl = locationListener;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(ListenerHolders.createListenerKey(this.zzcl, LocationListener.class.getSimpleName()), new zzac(this));
    }
}
