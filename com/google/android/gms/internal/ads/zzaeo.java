package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzaeo extends zzek implements zzaen {
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaet com_google_android_gms_internal_ads_zzaet = null;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        zzaey com_google_android_gms_internal_ads_zzaey;
        switch (i) {
            case 1:
                Parcelable zzb = zzb((zzaef) zzel.zza(parcel, zzaef.CREATOR));
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzb);
                break;
            case 2:
                zzaeq com_google_android_gms_internal_ads_zzaes;
                zzaef com_google_android_gms_internal_ads_zzaef = (zzaef) zzel.zza(parcel, zzaef.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    com_google_android_gms_internal_ads_zzaes = queryLocalInterface instanceof zzaeq ? (zzaeq) queryLocalInterface : new zzaes(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzaef, com_google_android_gms_internal_ads_zzaes);
                parcel2.writeNoException();
                break;
            case 4:
                com_google_android_gms_internal_ads_zzaey = (zzaey) zzel.zza(parcel, zzaey.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    com_google_android_gms_internal_ads_zzaet = queryLocalInterface instanceof zzaet ? (zzaet) queryLocalInterface : new zzaeu(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzaey, com_google_android_gms_internal_ads_zzaet);
                parcel2.writeNoException();
                break;
            case 5:
                com_google_android_gms_internal_ads_zzaey = (zzaey) zzel.zza(parcel, zzaey.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    com_google_android_gms_internal_ads_zzaet = queryLocalInterface instanceof zzaet ? (zzaet) queryLocalInterface : new zzaeu(readStrongBinder);
                }
                zzb(com_google_android_gms_internal_ads_zzaey, com_google_android_gms_internal_ads_zzaet);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
