package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;

final class zzp extends zzbe {
    private final /* synthetic */ GoogleMap$OnPolygonClickListener zzx;

    zzp(GoogleMap googleMap, GoogleMap$OnPolygonClickListener googleMap$OnPolygonClickListener) {
        this.zzx = googleMap$OnPolygonClickListener;
    }

    public final void zza(zzw com_google_android_gms_internal_maps_zzw) {
        this.zzx.onPolygonClick(new Polygon(com_google_android_gms_internal_maps_zzw));
    }
}
