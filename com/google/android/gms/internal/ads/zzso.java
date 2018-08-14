package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;

final class zzso implements BaseGmsClient$BaseConnectionCallbacks {
    final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    private final /* synthetic */ zzsg zzbnp;

    zzso(zzsm com_google_android_gms_internal_ads_zzsm, zzaoj com_google_android_gms_internal_ads_zzaoj, zzsg com_google_android_gms_internal_ads_zzsg) {
        this.zzbnn = com_google_android_gms_internal_ads_zzsm;
        this.zzbno = com_google_android_gms_internal_ads_zzaoj;
        this.zzbnp = com_google_android_gms_internal_ads_zzsg;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (zzsm.zzb(this.zzbnn)) {
            if (zzsm.zzc(this.zzbnn)) {
                return;
            }
            zzsm.zza(this.zzbnn, true);
            zzsf zzd = zzsm.zzd(this.zzbnn);
            if (zzd == null) {
                return;
            }
            this.zzbno.zza(new zzsq(this.zzbno, zzaki.zzb(new zzsp(this, zzd, this.zzbno, this.zzbnp))), zzaoe.zzcvz);
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
