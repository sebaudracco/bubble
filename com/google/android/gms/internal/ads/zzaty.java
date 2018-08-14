package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzaty extends zzej implements zzatx {
    zzaty(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzatv zza(zzatt com_google_android_gms_internal_ads_zzatt) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_ads_zzatt);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzatv com_google_android_gms_internal_ads_zzatv = (zzatv) zzel.zza(transactAndReadException, zzatv.CREATOR);
        transactAndReadException.recycle();
        return com_google_android_gms_internal_ads_zzatv;
    }
}
