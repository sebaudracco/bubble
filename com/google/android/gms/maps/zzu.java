package com.google.android.gms.maps;

final class zzu extends com.google.android.gms.maps.internal.zzu {
    private final /* synthetic */ GoogleMap$OnCameraMoveStartedListener zzac;

    zzu(GoogleMap googleMap, GoogleMap$OnCameraMoveStartedListener googleMap$OnCameraMoveStartedListener) {
        this.zzac = googleMap$OnCameraMoveStartedListener;
    }

    public final void onCameraMoveStarted(int i) {
        this.zzac.onCameraMoveStarted(i);
    }
}
