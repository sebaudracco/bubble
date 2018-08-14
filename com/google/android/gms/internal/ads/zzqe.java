package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzqe extends zzej implements zzqd {
    zzqe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper3);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        obtainAndWriteInterfaceToken = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = obtainAndWriteInterfaceToken.readStrongBinder();
        obtainAndWriteInterfaceToken.recycle();
        return readStrongBinder;
    }
}
