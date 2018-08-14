package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaha extends zzek implements zzagz {
    public zzaha() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzagz zzy(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return queryLocalInterface instanceof zzagz ? (zzagz) queryLocalInterface : new zzahb(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzagx com_google_android_gms_internal_ads_zzagx = null;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                zza((zzahk) zzel.zza(parcel, zzahk.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                show();
                parcel2.writeNoException();
                break;
            case 3:
                zzahe com_google_android_gms_internal_ads_zzahg;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    com_google_android_gms_internal_ads_zzahg = queryLocalInterface instanceof zzahe ? (zzahe) queryLocalInterface : new zzahg(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzahg);
                parcel2.writeNoException();
                break;
            case 5:
                boolean isLoaded = isLoaded();
                parcel2.writeNoException();
                zzel.zza(parcel2, isLoaded);
                break;
            case 6:
                pause();
                parcel2.writeNoException();
                break;
            case 7:
                resume();
                parcel2.writeNoException();
                break;
            case 8:
                destroy();
                parcel2.writeNoException();
                break;
            case 9:
                zzd(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 10:
                zze(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                zzf(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 12:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 13:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            case 14:
                zza(zzky.zzc(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                Parcelable zzba = zzba();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzba);
                break;
            case 16:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener");
                    com_google_android_gms_internal_ads_zzagx = queryLocalInterface instanceof zzagx ? (zzagx) queryLocalInterface : new zzagy(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzagx);
                parcel2.writeNoException();
                break;
            case 34:
                setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
