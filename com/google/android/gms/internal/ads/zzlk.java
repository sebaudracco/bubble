package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzlk extends zzek implements zzlj {
    public zzlk() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza();
                parcel2.writeNoException();
                break;
            case 2:
                setAppVolume(parcel.readFloat());
                parcel2.writeNoException();
                break;
            case 3:
                zzt(parcel.readString());
                parcel2.writeNoException();
                break;
            case 4:
                setAppMuted(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 5:
                zzb(Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zza(parcel.readString(), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                float zzdo = zzdo();
                parcel2.writeNoException();
                parcel2.writeFloat(zzdo);
                break;
            case 8:
                boolean zzdp = zzdp();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzdp);
                break;
            default:
                return false;
        }
        return true;
    }
}
