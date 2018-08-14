package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzhp extends zzej implements zzho {
    zzhp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    public final zzhi zza(zzhl com_google_android_gms_internal_ads_zzhl) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_ads_zzhl);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzhi com_google_android_gms_internal_ads_zzhi = (zzhi) zzel.zza(transactAndReadException, zzhi.CREATOR);
        transactAndReadException.recycle();
        return com_google_android_gms_internal_ads_zzhi;
    }
}
