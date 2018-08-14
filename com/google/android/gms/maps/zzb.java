package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzas {
    private final /* synthetic */ GoogleMap$OnMarkerClickListener zzj;

    zzb(GoogleMap googleMap, GoogleMap$OnMarkerClickListener googleMap$OnMarkerClickListener) {
        this.zzj = googleMap$OnMarkerClickListener;
    }

    public final boolean zza(zzt com_google_android_gms_internal_maps_zzt) {
        return this.zzj.onMarkerClick(new Marker(com_google_android_gms_internal_maps_zzt));
    }
}
