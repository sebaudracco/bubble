package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.clearcut.zze;

public final class zzo extends zza implements zzn {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    public final void zza(zzl com_google_android_gms_internal_clearcut_zzl, zze com_google_android_gms_clearcut_zze) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_clearcut_zzl);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_clearcut_zze);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
