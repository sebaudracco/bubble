package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzs extends zza implements zzr {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
    }

    public final zzp zza(IObjectWrapper iObjectWrapper, zzaa com_google_android_gms_internal_vision_zzaa) throws RemoteException {
        zzp com_google_android_gms_internal_vision_zzp;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzb.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzb.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_vision_zzaa);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_vision_zzp = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            com_google_android_gms_internal_vision_zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzq(readStrongBinder);
        }
        transactAndReadException.recycle();
        return com_google_android_gms_internal_vision_zzp;
    }
}
