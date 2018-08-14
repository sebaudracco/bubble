package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzpt extends zzek implements zzps {
    public zzpt() {
        super("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    public static zzps zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        return queryLocalInterface instanceof zzps ? (zzps) queryLocalInterface : new zzpu(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                String text = getText();
                parcel2.writeNoException();
                parcel2.writeString(text);
                break;
            case 3:
                List zzjr = zzjr();
                parcel2.writeNoException();
                parcel2.writeList(zzjr);
                break;
            default:
                return false;
        }
        return true;
    }
}
