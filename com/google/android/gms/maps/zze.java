package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

final class zze extends zzag {
    private final /* synthetic */ GoogleMap$OnInfoWindowLongClickListener zzm;

    zze(GoogleMap googleMap, GoogleMap$OnInfoWindowLongClickListener googleMap$OnInfoWindowLongClickListener) {
        this.zzm = googleMap$OnInfoWindowLongClickListener;
    }

    public final void zzf(zzt com_google_android_gms_internal_maps_zzt) {
        this.zzm.onInfoWindowLongClick(new Marker(com_google_android_gms_internal_maps_zzt));
    }
}
