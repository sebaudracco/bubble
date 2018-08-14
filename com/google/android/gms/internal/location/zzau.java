package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

final class zzau implements ListenerHolder$Notifier<LocationCallback> {
    private final /* synthetic */ LocationResult zzdb;

    zzau(zzat com_google_android_gms_internal_location_zzat, LocationResult locationResult) {
        this.zzdb = locationResult;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationResult(this.zzdb);
    }

    public final void onNotifyListenerFailed() {
    }
}
