package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;

final class zzy extends zzak {
    private final /* synthetic */ GoogleMap$OnMapClickListener zzag;

    zzy(GoogleMap googleMap, GoogleMap$OnMapClickListener googleMap$OnMapClickListener) {
        this.zzag = googleMap$OnMapClickListener;
    }

    public final void onMapClick(LatLng latLng) {
        this.zzag.onMapClick(latLng);
    }
}
