package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

final class zza extends zzaa {
    private final /* synthetic */ GoogleMap$OnIndoorStateChangeListener zzi;

    zza(GoogleMap googleMap, GoogleMap$OnIndoorStateChangeListener googleMap$OnIndoorStateChangeListener) {
        this.zzi = googleMap$OnIndoorStateChangeListener;
    }

    public final void onIndoorBuildingFocused() {
        this.zzi.onIndoorBuildingFocused();
    }

    public final void zza(zzn com_google_android_gms_internal_maps_zzn) {
        this.zzi.onIndoorLevelActivated(new IndoorBuilding(com_google_android_gms_internal_maps_zzn));
    }
}
