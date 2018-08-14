package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzrd extends zzek implements zzrc {
    public zzrd() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzrc zzn(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        return queryLocalInterface instanceof zzrc ? (zzrc) queryLocalInterface : new zzre(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzqs com_google_android_gms_internal_ads_zzqs;
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_ads_zzqs = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
            com_google_android_gms_internal_ads_zzqs = queryLocalInterface instanceof zzqs ? (zzqs) queryLocalInterface : new zzqu(readStrongBinder);
        }
        zzb(com_google_android_gms_internal_ads_zzqs, parcel.readString());
        parcel2.writeNoException();
        return true;
    }
}
