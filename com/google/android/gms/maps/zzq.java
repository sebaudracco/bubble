package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;

final class zzq extends zzbg {
    private final /* synthetic */ GoogleMap$OnPolylineClickListener zzy;

    zzq(GoogleMap googleMap, GoogleMap$OnPolylineClickListener googleMap$OnPolylineClickListener) {
        this.zzy = googleMap$OnPolylineClickListener;
    }

    public final void zza(zzz com_google_android_gms_internal_maps_zzz) {
        this.zzy.onPolylineClick(new Polyline(com_google_android_gms_internal_maps_zzz));
    }
}
