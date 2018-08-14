package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {

    public static abstract class zza extends zzb implements ILocationSourceDelegate {
        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }

        protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    zzah com_google_android_gms_maps_internal_zzah;
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        com_google_android_gms_maps_internal_zzah = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                        com_google_android_gms_maps_internal_zzah = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzai(readStrongBinder);
                    }
                    activate(com_google_android_gms_maps_internal_zzah);
                    break;
                case 2:
                    deactivate();
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void activate(zzah com_google_android_gms_maps_internal_zzah) throws RemoteException;

    void deactivate() throws RemoteException;
}
