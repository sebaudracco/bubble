package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzaw;

final class zzi extends zzaw {
    private final /* synthetic */ GoogleMap$OnMyLocationButtonClickListener zzq;

    zzi(GoogleMap googleMap, GoogleMap$OnMyLocationButtonClickListener googleMap$OnMyLocationButtonClickListener) {
        this.zzq = googleMap$OnMyLocationButtonClickListener;
    }

    public final boolean onMyLocationButtonClick() throws RemoteException {
        return this.zzq.onMyLocationButtonClick();
    }
}
