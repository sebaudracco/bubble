package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;

public interface GoogleMap$OnMarkerDragListener {
    void onMarkerDrag(Marker marker);

    void onMarkerDragEnd(Marker marker);

    void onMarkerDragStart(Marker marker);
}
