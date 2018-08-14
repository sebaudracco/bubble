package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.internal.zzah;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm implements OnLocationChangedListener {
    private final /* synthetic */ zzah zzu;

    zzm(zzl com_google_android_gms_maps_zzl, zzah com_google_android_gms_maps_internal_zzah) {
        this.zzu = com_google_android_gms_maps_internal_zzah;
    }

    public final void onLocationChanged(Location location) {
        try {
            this.zzu.zza(location);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
