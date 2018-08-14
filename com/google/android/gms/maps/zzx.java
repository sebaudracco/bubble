package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzo;

final class zzx extends zzo {
    private final /* synthetic */ GoogleMap$OnCameraIdleListener zzaf;

    zzx(GoogleMap googleMap, GoogleMap$OnCameraIdleListener googleMap$OnCameraIdleListener) {
        this.zzaf = googleMap$OnCameraIdleListener;
    }

    public final void onCameraIdle() {
        this.zzaf.onCameraIdle();
    }
}
