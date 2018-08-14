package com.google.android.gms.maps;

import com.google.android.gms.maps.model.IndoorBuilding;

public interface GoogleMap$OnIndoorStateChangeListener {
    void onIndoorBuildingFocused();

    void onIndoorLevelActivated(IndoorBuilding indoorBuilding);
}
