package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsw implements zzts {
    private final /* synthetic */ int zzbnx;

    zzsw(zzsu com_google_android_gms_internal_ads_zzsu, int i) {
        this.zzbnx = i;
    }

    public final void zzb(zztt com_google_android_gms_internal_ads_zztt) throws RemoteException {
        if (com_google_android_gms_internal_ads_zztt.zzxs != null) {
            com_google_android_gms_internal_ads_zztt.zzxs.onAdFailedToLoad(this.zzbnx);
        }
    }
}
