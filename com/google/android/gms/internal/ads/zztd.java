package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztd implements zzts {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzbny;

    zztd(zztc com_google_android_gms_internal_ads_zztc, String str, String str2) {
        this.val$name = str;
        this.zzbny = str2;
    }

    public final void zzb(zztt com_google_android_gms_internal_ads_zztt) throws RemoteException {
        if (com_google_android_gms_internal_ads_zztt.zzboe != null) {
            com_google_android_gms_internal_ads_zztt.zzboe.onAppEvent(this.val$name, this.zzbny);
        }
    }
}
