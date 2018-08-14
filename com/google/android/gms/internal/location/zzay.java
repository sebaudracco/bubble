package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationListener;

final class zzay implements ListenerHolder$Notifier<LocationListener> {
    private final /* synthetic */ Location zzdd;

    zzay(zzax com_google_android_gms_internal_location_zzax, Location location) {
        this.zzdd = location;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationListener) obj).onLocationChanged(this.zzdd);
    }

    public final void onNotifyListenerFailed() {
    }
}
