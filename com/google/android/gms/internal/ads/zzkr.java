package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkr extends zzej implements zzkq {
    zzkr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, String str, zzxn com_google_android_gms_internal_ads_zzxn, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_ads_zzxn);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        obtainAndWriteInterfaceToken = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = obtainAndWriteInterfaceToken.readStrongBinder();
        obtainAndWriteInterfaceToken.recycle();
        return readStrongBinder;
    }
}
