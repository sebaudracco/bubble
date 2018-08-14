package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkl extends zzek implements zzkk {
    public zzkl() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String mediationAdapterClassName;
        switch (i) {
            case 1:
                zzd((zzjj) zzel.zza(parcel, zzjj.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 3:
                boolean isLoading = isLoading();
                parcel2.writeNoException();
                zzel.zza(parcel2, isLoading);
                break;
            case 4:
                mediationAdapterClassName = zzck();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 5:
                zza((zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
