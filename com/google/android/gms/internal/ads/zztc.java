package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztc extends zzlb {
    private final /* synthetic */ zzst zzbnw;

    zztc(zzst com_google_android_gms_internal_ads_zzst) {
        this.zzbnw = com_google_android_gms_internal_ads_zzst;
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztd(this, str, str2));
    }
}
