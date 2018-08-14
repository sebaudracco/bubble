package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsy implements zzts {
    zzsy(zzsu com_google_android_gms_internal_ads_zzsu) {
    }

    public final void zzb(zztt com_google_android_gms_internal_ads_zztt) throws RemoteException {
        if (com_google_android_gms_internal_ads_zztt.zzxs != null) {
            com_google_android_gms_internal_ads_zztt.zzxs.onAdLoaded();
        }
    }
}
