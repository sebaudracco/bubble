package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

final class zzc extends zzau {
    private final /* synthetic */ GoogleMap$OnMarkerDragListener zzk;

    zzc(GoogleMap googleMap, GoogleMap$OnMarkerDragListener googleMap$OnMarkerDragListener) {
        this.zzk = googleMap$OnMarkerDragListener;
    }

    public final void zzb(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzk.onMarkerDragStart(new Marker(com_google_android_gms_internal_maps_zzt));
    }

    public final void zzc(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzk.onMarkerDragEnd(new Marker(com_google_android_gms_internal_maps_zzt));
    }

    public final void zzd(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzk.onMarkerDrag(new Marker(com_google_android_gms_internal_maps_zzt));
    }
}
