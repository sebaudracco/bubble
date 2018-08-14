package com.google.android.gms.internal.vision;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public interface zzf extends IInterface {
    Barcode[] zza(IObjectWrapper iObjectWrapper, zzk com_google_android_gms_internal_vision_zzk) throws RemoteException;

    Barcode[] zzb(IObjectWrapper iObjectWrapper, zzk com_google_android_gms_internal_vision_zzk) throws RemoteException;

    void zzf() throws RemoteException;
}
