package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;

public interface AdAdapter {
    AdPlacementType getPlacementType();

    void onDestroy();
}
