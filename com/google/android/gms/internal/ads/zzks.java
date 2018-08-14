package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzks extends IInterface {
    void destroy() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzlo getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzaaw com_google_android_gms_internal_ads_zzaaw) throws RemoteException;

    void zza(zzabc com_google_android_gms_internal_ads_zzabc, String str) throws RemoteException;

    void zza(zzahe com_google_android_gms_internal_ads_zzahe) throws RemoteException;

    void zza(zzjn com_google_android_gms_internal_ads_zzjn) throws RemoteException;

    void zza(zzke com_google_android_gms_internal_ads_zzke) throws RemoteException;

    void zza(zzkh com_google_android_gms_internal_ads_zzkh) throws RemoteException;

    void zza(zzkx com_google_android_gms_internal_ads_zzkx) throws RemoteException;

    void zza(zzla com_google_android_gms_internal_ads_zzla) throws RemoteException;

    void zza(zzlg com_google_android_gms_internal_ads_zzlg) throws RemoteException;

    void zza(zzlu com_google_android_gms_internal_ads_zzlu) throws RemoteException;

    void zza(zzmu com_google_android_gms_internal_ads_zzmu) throws RemoteException;

    void zza(zzod com_google_android_gms_internal_ads_zzod) throws RemoteException;

    boolean zzb(zzjj com_google_android_gms_internal_ads_zzjj) throws RemoteException;

    Bundle zzba() throws RemoteException;

    IObjectWrapper zzbj() throws RemoteException;

    zzjn zzbk() throws RemoteException;

    void zzbm() throws RemoteException;

    zzla zzbw() throws RemoteException;

    zzkh zzbx() throws RemoteException;

    String zzck() throws RemoteException;
}
