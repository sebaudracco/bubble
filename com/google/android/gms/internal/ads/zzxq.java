package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzxq extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzlo getVideoController() throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzaic com_google_android_gms_internal_ads_zzaic, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzaic com_google_android_gms_internal_ads_zzaic, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt, zzpl com_google_android_gms_internal_ads_zzpl, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException;

    void zza(zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2) throws RemoteException;

    void zzc(zzjj com_google_android_gms_internal_ads_zzjj, String str) throws RemoteException;

    void zzi(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzxz zzmo() throws RemoteException;

    zzyc zzmp() throws RemoteException;

    Bundle zzmq() throws RemoteException;

    Bundle zzmr() throws RemoteException;

    boolean zzms() throws RemoteException;

    zzqs zzmt() throws RemoteException;

    zzyf zzmu() throws RemoteException;
}
