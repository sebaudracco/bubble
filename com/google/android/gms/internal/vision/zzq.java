package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzq extends zza implements zzp {
    zzq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final zzt[] zza(IObjectWrapper iObjectWrapper, zzk com_google_android_gms_internal_vision_zzk, zzv com_google_android_gms_internal_vision_zzv) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzb.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzb.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_vision_zzk);
        zzb.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_vision_zzv);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        zzt[] com_google_android_gms_internal_vision_zztArr = (zzt[]) transactAndReadException.createTypedArray(zzt.CREATOR);
        transactAndReadException.recycle();
        return com_google_android_gms_internal_vision_zztArr;
    }

    public final void zzi() throws RemoteException {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }
}
