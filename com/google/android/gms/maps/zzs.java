package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzs extends zzbc {
    private final /* synthetic */ GoogleMap$OnPoiClickListener zzaa;

    zzs(GoogleMap googleMap, GoogleMap$OnPoiClickListener googleMap$OnPoiClickListener) {
        this.zzaa = googleMap$OnPoiClickListener;
    }

    public final void zza(PointOfInterest pointOfInterest) throws RemoteException {
        this.zzaa.onPoiClick(pointOfInterest);
    }
}
