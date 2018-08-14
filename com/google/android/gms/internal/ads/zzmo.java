package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzmo extends zzaha {
    private zzahe zzatl;

    public final void destroy() throws RemoteException {
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
    }

    public final void resume() throws RemoteException {
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
    }

    public final void show() throws RemoteException {
    }

    public final void zza(zzagx com_google_android_gms_internal_ads_zzagx) throws RemoteException {
    }

    public final void zza(zzahe com_google_android_gms_internal_ads_zzahe) throws RemoteException {
        this.zzatl = com_google_android_gms_internal_ads_zzahe;
    }

    public final void zza(zzahk com_google_android_gms_internal_ads_zzahk) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post(new zzmp(this));
    }

    public final void zza(zzkx com_google_android_gms_internal_ads_zzkx) throws RemoteException {
    }

    public final Bundle zzba() throws RemoteException {
        return new Bundle();
    }

    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}
