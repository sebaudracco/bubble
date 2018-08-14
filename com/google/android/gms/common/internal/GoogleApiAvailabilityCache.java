package com.google.android.gms.common.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.Client;

public class GoogleApiAvailabilityCache {
    private final SparseIntArray zzug;
    private GoogleApiAvailabilityLight zzuh;

    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }

    public GoogleApiAvailabilityCache(@NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zzug = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zzuh = googleApiAvailabilityLight;
    }

    public void flush() {
        this.zzug.clear();
    }

    public int getClientAvailability(@NonNull Context context, @NonNull Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int i = this.zzug.get(minApkVersion, -1);
        if (i != -1) {
            return i;
        }
        int i2;
        for (i2 = 0; i2 < this.zzug.size(); i2++) {
            int keyAt = this.zzug.keyAt(i2);
            if (keyAt > minApkVersion && this.zzug.get(keyAt) == 0) {
                i2 = 0;
                break;
            }
        }
        i2 = i;
        if (i2 == -1) {
            i2 = this.zzuh.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zzug.put(minApkVersion, i2);
        return i2;
    }
}
