package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;

final class zzd extends zzac {
    private final /* synthetic */ GoogleMap$OnInfoWindowClickListener zzl;

    zzd(GoogleMap googleMap, GoogleMap$OnInfoWindowClickListener googleMap$OnInfoWindowClickListener) {
        this.zzl = googleMap$OnInfoWindowClickListener;
    }

    public final void zze(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzl.onInfoWindowClick(new Marker(com_google_android_gms_internal_maps_zzt));
    }
}
