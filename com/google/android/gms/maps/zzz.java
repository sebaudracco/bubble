package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;

final class zzz extends zzao {
    private final /* synthetic */ GoogleMap$OnMapLongClickListener zzah;

    zzz(GoogleMap googleMap, GoogleMap$OnMapLongClickListener googleMap$OnMapLongClickListener) {
        this.zzah = googleMap$OnMapLongClickListener;
    }

    public final void onMapLongClick(LatLng latLng) {
        this.zzah.onMapLongClick(latLng);
    }
}
