package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztp implements zzts {
    private final /* synthetic */ int zzbnx;

    zztp(zzti com_google_android_gms_internal_ads_zzti, int i) {
        this.zzbnx = i;
    }

    public final void zzb(zztt com_google_android_gms_internal_ads_zztt) throws RemoteException {
        if (com_google_android_gms_internal_ads_zztt.zzboh != null) {
            com_google_android_gms_internal_ads_zztt.zzboh.onRewardedVideoAdFailedToLoad(this.zzbnx);
        }
    }
}
