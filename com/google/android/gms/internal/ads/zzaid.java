package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaid extends zzek implements zzaic {
    public zzaid() {
        super("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    public static zzaic zzaa(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
        return queryLocalInterface instanceof zzaic ? (zzaic) queryLocalInterface : new zzaie(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzq(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 2:
                zzc(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                zzr(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 4:
                zzs(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 5:
                zzt(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 6:
                zzu(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 7:
                zza(Stub.asInterface(parcel.readStrongBinder()), (zzaig) zzel.zza(parcel, zzaig.CREATOR));
                break;
            case 8:
                zzv(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 9:
                zzd(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                zzw(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 11:
                zzx(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 12:
                zzc((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
