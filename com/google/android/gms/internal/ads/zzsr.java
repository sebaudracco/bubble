package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;

final class zzsr implements BaseGmsClient$BaseOnConnectionFailedListener {
    private final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;

    zzsr(zzsm com_google_android_gms_internal_ads_zzsm, zzaoj com_google_android_gms_internal_ads_zzaoj) {
        this.zzbnn = com_google_android_gms_internal_ads_zzsm;
        this.zzbno = com_google_android_gms_internal_ads_zzaoj;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (zzsm.zzb(this.zzbnn)) {
            this.zzbno.setException(new RuntimeException("Connection failed."));
        }
    }
}
