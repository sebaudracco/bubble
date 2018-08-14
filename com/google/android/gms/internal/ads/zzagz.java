package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzagz extends IInterface {
    void destroy() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoaded() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void show() throws RemoteException;

    void zza(zzagx com_google_android_gms_internal_ads_zzagx) throws RemoteException;

    void zza(zzahe com_google_android_gms_internal_ads_zzahe) throws RemoteException;

    void zza(zzahk com_google_android_gms_internal_ads_zzahk) throws RemoteException;

    void zza(zzkx com_google_android_gms_internal_ads_zzkx) throws RemoteException;

    Bundle zzba() throws RemoteException;

    void zzd(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zze(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzf(IObjectWrapper iObjectWrapper) throws RemoteException;
}
