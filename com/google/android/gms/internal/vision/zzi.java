package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzi extends zza implements zzh {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final zzf zza(IObjectWrapper iObjectWrapper, zzc com_google_android_gms_internal_vision_zzc) throws RemoteException {
        zzf com_google_android_gms_internal_vision_zzf;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzb.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzb.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_vision_zzc);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_vision_zzf = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            com_google_android_gms_internal_vision_zzf = queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzg(readStrongBinder);
        }
        transactAndReadException.recycle();
        return com_google_android_gms_internal_vision_zzf;
    }
}
