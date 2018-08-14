package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzxr extends zzek implements zzxq {
    public zzxr() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzxt com_google_android_gms_internal_ads_zzxt = null;
        IObjectWrapper asInterface;
        zzjn com_google_android_gms_internal_ads_zzjn;
        zzjj com_google_android_gms_internal_ads_zzjj;
        String readString;
        IBinder readStrongBinder;
        zzxt com_google_android_gms_internal_ads_zzxt2;
        IInterface queryLocalInterface;
        String readString2;
        zzjj com_google_android_gms_internal_ads_zzjj2;
        boolean isInitialized;
        Parcelable zzmq;
        switch (i) {
            case 1:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                com_google_android_gms_internal_ads_zzjn = (zzjn) zzel.zza(parcel, zzjn.CREATOR);
                com_google_android_gms_internal_ads_zzjj = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    com_google_android_gms_internal_ads_zzxt2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    com_google_android_gms_internal_ads_zzxt2 = queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(readStrongBinder);
                }
                zza(asInterface, com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzjj, readString, com_google_android_gms_internal_ads_zzxt2);
                parcel2.writeNoException();
                break;
            case 2:
                queryLocalInterface = getView();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 3:
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                zzjj com_google_android_gms_internal_ads_zzjj3 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    com_google_android_gms_internal_ads_zzxt = queryLocalInterface2 instanceof zzxt ? (zzxt) queryLocalInterface2 : new zzxv(readStrongBinder2);
                }
                zza(asInterface2, com_google_android_gms_internal_ads_zzjj3, readString2, com_google_android_gms_internal_ads_zzxt);
                parcel2.writeNoException();
                break;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 5:
                destroy();
                parcel2.writeNoException();
                break;
            case 6:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                com_google_android_gms_internal_ads_zzjn = (zzjn) zzel.zza(parcel, zzjn.CREATOR);
                com_google_android_gms_internal_ads_zzjj = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                readString = parcel.readString();
                String readString3 = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    com_google_android_gms_internal_ads_zzxt = queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(readStrongBinder);
                }
                zza(asInterface, com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzjj, readString, readString3, com_google_android_gms_internal_ads_zzxt);
                parcel2.writeNoException();
                break;
            case 7:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                com_google_android_gms_internal_ads_zzjj2 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    com_google_android_gms_internal_ads_zzxt2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    com_google_android_gms_internal_ads_zzxt2 = queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(readStrongBinder);
                }
                zza(asInterface, com_google_android_gms_internal_ads_zzjj2, readString2, readString, com_google_android_gms_internal_ads_zzxt2);
                parcel2.writeNoException();
                break;
            case 8:
                pause();
                parcel2.writeNoException();
                break;
            case 9:
                resume();
                parcel2.writeNoException();
                break;
            case 10:
                zza(Stub.asInterface(parcel.readStrongBinder()), (zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString(), zzaid.zzaa(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                zzc((zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString());
                parcel2.writeNoException();
                break;
            case 12:
                showVideo();
                parcel2.writeNoException();
                break;
            case 13:
                isInitialized = isInitialized();
                parcel2.writeNoException();
                zzel.zza(parcel2, isInitialized);
                break;
            case 14:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                com_google_android_gms_internal_ads_zzjj2 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    com_google_android_gms_internal_ads_zzxt2 = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    com_google_android_gms_internal_ads_zzxt2 = queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(readStrongBinder);
                }
                zza(asInterface, com_google_android_gms_internal_ads_zzjj2, readString2, readString, com_google_android_gms_internal_ads_zzxt2, (zzpl) zzel.zza(parcel, zzpl.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 15:
                queryLocalInterface = zzmo();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 16:
                queryLocalInterface = zzmp();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 17:
                zzmq = zzmq();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzmq);
                break;
            case 18:
                zzmq = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzmq);
                break;
            case 19:
                zzmq = zzmr();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzmq);
                break;
            case 20:
                zza((zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 21:
                zzi(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 22:
                isInitialized = zzms();
                parcel2.writeNoException();
                zzel.zza(parcel2, isInitialized);
                break;
            case 23:
                zza(Stub.asInterface(parcel.readStrongBinder()), zzaid.zzaa(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 24:
                queryLocalInterface = zzmt();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 25:
                setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 26:
                queryLocalInterface = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 27:
                queryLocalInterface = zzmu();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            default:
                return false;
        }
        return true;
    }
}
