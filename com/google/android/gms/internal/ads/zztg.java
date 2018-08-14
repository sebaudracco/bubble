package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztg extends zzkf {
    private final /* synthetic */ zzst zzbnw;

    zztg(zzst com_google_android_gms_internal_ads_zzst) {
        this.zzbnw = com_google_android_gms_internal_ads_zzst;
    }

    public final void onAdClicked() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzth(this));
    }
}
