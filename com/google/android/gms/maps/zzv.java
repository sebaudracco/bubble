package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzs;

final class zzv extends zzs {
    private final /* synthetic */ GoogleMap$OnCameraMoveListener zzad;

    zzv(GoogleMap googleMap, GoogleMap$OnCameraMoveListener googleMap$OnCameraMoveListener) {
        this.zzad = googleMap$OnCameraMoveListener;
    }

    public final void onCameraMove() {
        this.zzad.onCameraMove();
    }
}
