package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmh extends zzkl {
    final /* synthetic */ zzmf zzati;

    private zzmh(zzmf com_google_android_gms_internal_ads_zzmf) {
        this.zzati = com_google_android_gms_internal_ads_zzmf;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, int i) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post(new zzmi(this));
    }

    public final String zzck() throws RemoteException {
        return null;
    }

    public final void zzd(zzjj com_google_android_gms_internal_ads_zzjj) throws RemoteException {
        zza(com_google_android_gms_internal_ads_zzjj, 1);
    }
}
