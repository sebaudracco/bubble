package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;

final class zzhh implements BaseGmsClient$BaseOnConnectionFailedListener {
    private final /* synthetic */ zzhd zzajt;

    zzhh(zzhd com_google_android_gms_internal_ads_zzhd) {
        this.zzajt = com_google_android_gms_internal_ads_zzhd;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (zzhd.zzc(this.zzajt)) {
            zzhd.zza(this.zzajt, null);
            if (zzhd.zzd(this.zzajt) != null) {
                zzhd.zza(this.zzajt, null);
            }
            zzhd.zzc(this.zzajt).notifyAll();
        }
    }
}
