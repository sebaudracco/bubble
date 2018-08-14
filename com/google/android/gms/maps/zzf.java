package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

final class zzf extends zzae {
    private final /* synthetic */ GoogleMap$OnInfoWindowCloseListener zzn;

    zzf(GoogleMap googleMap, GoogleMap$OnInfoWindowCloseListener googleMap$OnInfoWindowCloseListener) {
        this.zzn = googleMap$OnInfoWindowCloseListener;
    }

    public final void zzg(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzn.onInfoWindowClose(new Marker(com_google_android_gms_internal_maps_zzt));
    }
}
