package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzpx extends zzek implements zzpw {
    public zzpx() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    public static zzpw zzh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        return queryLocalInterface instanceof zzpw ? (zzpw) queryLocalInterface : new zzpy(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IInterface zzjy = zzjy();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjy);
                break;
            case 2:
                Parcelable uri = getUri();
                parcel2.writeNoException();
                zzel.zzb(parcel2, uri);
                break;
            case 3:
                double scale = getScale();
                parcel2.writeNoException();
                parcel2.writeDouble(scale);
                break;
            default:
                return false;
        }
        return true;
    }
}
