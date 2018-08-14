package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;

final class zzn extends zzy {
    private final /* synthetic */ GoogleMap$OnGroundOverlayClickListener zzv;

    zzn(GoogleMap googleMap, GoogleMap$OnGroundOverlayClickListener googleMap$OnGroundOverlayClickListener) {
        this.zzv = googleMap$OnGroundOverlayClickListener;
    }

    public final void zza(zzk com_google_android_gms_internal_maps_zzk) {
        this.zzv.onGroundOverlayClick(new GroundOverlay(com_google_android_gms_internal_maps_zzk));
    }
}
