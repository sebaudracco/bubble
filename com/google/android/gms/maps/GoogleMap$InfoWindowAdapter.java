package com.google.android.gms.maps;

import android.view.View;
import com.google.android.gms.maps.model.Marker;

public interface GoogleMap$InfoWindowAdapter {
    View getInfoContents(Marker marker);

    View getInfoWindow(Marker marker);
}
