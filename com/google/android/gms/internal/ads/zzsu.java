package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsu extends zzki {
    private final /* synthetic */ zzst zzbnw;

    zzsu(zzst com_google_android_gms_internal_ads_zzst) {
        this.zzbnw = com_google_android_gms_internal_ads_zzst;
    }

    public final void onAdClicked() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztb(this));
    }

    public final void onAdClosed() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzsv(this));
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzsw(this, i));
        zzakb.v("Pooled interstitial failed to load.");
    }

    public final void onAdImpression() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzta(this));
    }

    public final void onAdLeftApplication() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzsx(this));
    }

    public final void onAdLoaded() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzsy(this));
        zzakb.v("Pooled interstitial loaded.");
    }

    public final void onAdOpened() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzsz(this));
    }
}
