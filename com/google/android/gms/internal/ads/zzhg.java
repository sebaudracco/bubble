package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;

final class zzhg implements BaseGmsClient$BaseConnectionCallbacks {
    private final /* synthetic */ zzhd zzajt;

    zzhg(zzhd com_google_android_gms_internal_ads_zzhd) {
        this.zzajt = com_google_android_gms_internal_ads_zzhd;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (zzhd.zzc(this.zzajt)) {
            try {
                if (zzhd.zzd(this.zzajt) != null) {
                    zzhd.zza(this.zzajt, zzhd.zzd(this.zzajt).zzhl());
                }
            } catch (Throwable e) {
                zzakb.zzb("Unable to obtain a cache service instance.", e);
                zzhd.zza(this.zzajt);
            }
            zzhd.zzc(this.zzajt).notifyAll();
        }
    }

    public final void onConnectionSuspended(int i) {
        synchronized (zzhd.zzc(this.zzajt)) {
            zzhd.zza(this.zzajt, null);
            zzhd.zzc(this.zzajt).notifyAll();
        }
    }
}
