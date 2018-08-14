package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;

final class zzg extends zzi {
    private final /* synthetic */ GoogleMap$InfoWindowAdapter zzo;

    zzg(GoogleMap googleMap, GoogleMap$InfoWindowAdapter googleMap$InfoWindowAdapter) {
        this.zzo = googleMap$InfoWindowAdapter;
    }

    public final IObjectWrapper zzh(zzt com_google_android_gms_internal_maps_zzt) {
        return ObjectWrapper.wrap(this.zzo.getInfoWindow(new Marker(com_google_android_gms_internal_maps_zzt)));
    }

    public final IObjectWrapper zzi(zzt com_google_android_gms_internal_maps_zzt) {
        return ObjectWrapper.wrap(this.zzo.getInfoContents(new Marker(com_google_android_gms_internal_maps_zzt)));
    }
}
