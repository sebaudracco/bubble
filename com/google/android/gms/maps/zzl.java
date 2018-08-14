package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate.zza;
import com.google.android.gms.maps.internal.zzah;

final class zzl extends zza {
    private final /* synthetic */ LocationSource zzt;

    zzl(GoogleMap googleMap, LocationSource locationSource) {
        this.zzt = locationSource;
    }

    public final void activate(zzah com_google_android_gms_maps_internal_zzah) {
        this.zzt.activate(new zzm(this, com_google_android_gms_maps_internal_zzah));
    }

    public final void deactivate() {
        this.zzt.deactivate();
    }
}
