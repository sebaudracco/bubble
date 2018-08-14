package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzzk extends zzek implements zzzj {
    public zzzk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzzj zzt(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzzj ? (zzzj) queryLocalInterface : new zzzl(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzzh com_google_android_gms_internal_ads_zzzh = null;
        Parcelable zznc;
        byte[] createByteArray;
        String readString;
        Bundle bundle;
        IObjectWrapper asInterface;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                zzzm com_google_android_gms_internal_ads_zzzm;
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                String readString2 = parcel.readString();
                Bundle bundle2 = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 == null) {
                    com_google_android_gms_internal_ads_zzzm = null;
                } else {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    com_google_android_gms_internal_ads_zzzm = queryLocalInterface2 instanceof zzzm ? (zzzm) queryLocalInterface2 : new zzzn(readStrongBinder2);
                }
                zza(asInterface2, readString2, bundle2, com_google_android_gms_internal_ads_zzzm);
                parcel2.writeNoException();
                break;
            case 2:
                zznc = zznc();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zznc);
                break;
            case 3:
                zznc = zznd();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zznc);
                break;
            case 4:
                zzzf com_google_android_gms_internal_ads_zzzg;
                createByteArray = parcel.createByteArray();
                readString = parcel.readString();
                bundle = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    com_google_android_gms_internal_ads_zzzg = queryLocalInterface instanceof zzzf ? (zzzf) queryLocalInterface : new zzzg(readStrongBinder);
                }
                zza(createByteArray, readString, bundle, asInterface, com_google_android_gms_internal_ads_zzzg, zzxu.zzs(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                queryLocalInterface = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 6:
                createByteArray = parcel.createByteArray();
                readString = parcel.readString();
                bundle = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    com_google_android_gms_internal_ads_zzzh = queryLocalInterface instanceof zzzh ? (zzzh) queryLocalInterface : new zzzi(readStrongBinder);
                }
                zza(createByteArray, readString, bundle, asInterface, com_google_android_gms_internal_ads_zzzh, zzxu.zzs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                showInterstitial();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
