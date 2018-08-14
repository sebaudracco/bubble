package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;

final class zzo extends zzw {
    private final /* synthetic */ GoogleMap$OnCircleClickListener zzw;

    zzo(GoogleMap googleMap, GoogleMap$OnCircleClickListener googleMap$OnCircleClickListener) {
        this.zzw = googleMap$OnCircleClickListener;
    }

    public final void zza(zzh com_google_android_gms_internal_maps_zzh) {
        this.zzw.onCircleClick(new Circle(com_google_android_gms_internal_maps_zzh));
    }
}
