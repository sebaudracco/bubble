package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzti extends zzahf {
    private final /* synthetic */ zzst zzbnw;

    zzti(zzst com_google_android_gms_internal_ads_zzst) {
        this.zzbnw = com_google_android_gms_internal_ads_zzst;
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztm(this));
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztp(this, i));
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zzto(this));
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztj(this));
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztk(this));
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztq(this));
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztl(this));
    }

    public final void zza(zzagu com_google_android_gms_internal_ads_zzagu) throws RemoteException {
        zzst.zza(this.zzbnw).add(new zztn(this, com_google_android_gms_internal_ads_zzagu));
    }
}
