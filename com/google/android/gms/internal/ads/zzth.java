package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzth implements zzts {
    zzth(zztg com_google_android_gms_internal_ads_zztg) {
    }

    public final void zzb(zztt com_google_android_gms_internal_ads_zztt) throws RemoteException {
        if (com_google_android_gms_internal_ads_zztt.zzbog != null) {
            com_google_android_gms_internal_ads_zztt.zzbog.onAdClicked();
        }
    }
}
