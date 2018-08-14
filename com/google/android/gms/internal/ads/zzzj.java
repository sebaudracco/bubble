package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzzj extends IInterface {
    zzlo getVideoController() throws RemoteException;

    void showInterstitial() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, zzzm com_google_android_gms_internal_ads_zzzm) throws RemoteException;

    void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzf com_google_android_gms_internal_ads_zzzf, zzxt com_google_android_gms_internal_ads_zzxt, zzjn com_google_android_gms_internal_ads_zzjn) throws RemoteException;

    void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzh com_google_android_gms_internal_ads_zzzh, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException;

    zzzt zznc() throws RemoteException;

    zzzt zznd() throws RemoteException;
}
