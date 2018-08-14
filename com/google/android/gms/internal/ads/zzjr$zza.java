package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

@VisibleForTesting
abstract class zzjr$zza<T> {
    private final /* synthetic */ zzjr zzart;

    zzjr$zza(zzjr com_google_android_gms_internal_ads_zzjr) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
    }

    @Nullable
    protected abstract T zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException;

    @Nullable
    protected abstract T zzib() throws RemoteException;

    @Nullable
    protected final T zzic() {
        T t = null;
        zzld zza = zzjr.zza(this.zzart);
        if (zza == null) {
            zzane.zzdk("ClientApi class cannot be loaded.");
        } else {
            try {
                t = zza(zza);
            } catch (Throwable e) {
                zzane.zzc("Cannot invoke local loader using ClientApi class", e);
            }
        }
        return t;
    }

    @Nullable
    protected final T zzid() {
        try {
            return zzib();
        } catch (Throwable e) {
            zzane.zzc("Cannot invoke remote loader", e);
            return null;
        }
    }
}
