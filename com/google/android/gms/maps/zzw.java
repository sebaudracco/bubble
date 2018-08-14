package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzq;

final class zzw extends zzq {
    private final /* synthetic */ GoogleMap$OnCameraMoveCanceledListener zzae;

    zzw(GoogleMap googleMap, GoogleMap$OnCameraMoveCanceledListener googleMap$OnCameraMoveCanceledListener) {
        this.zzae = googleMap$OnCameraMoveCanceledListener;
    }

    public final void onCameraMoveCanceled() {
        this.zzae.onCameraMoveCanceled();
    }
}
