package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public abstract class zzrs extends zzek implements zzrr {
    public zzrs() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String headline;
        IInterface zzjz;
        switch (i) {
            case 2:
                headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 3:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 4:
                headline = getBody();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 5:
                zzjz = zzjz();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjz);
                break;
            case 6:
                headline = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 7:
                headline = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 8:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                break;
            case 9:
                headline = getStore();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 10:
                headline = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 11:
                zzjz = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjz);
                break;
            case 12:
                headline = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 13:
                destroy();
                parcel2.writeNoException();
                break;
            case 14:
                zzjz = zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjz);
                break;
            case 15:
                performClick((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 16:
                boolean recordImpression = recordImpression((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzel.zza(parcel2, recordImpression);
                break;
            case 17:
                reportTouchEvent((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 18:
                zzjz = zzka();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjz);
                break;
            case 19:
                zzjz = zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzjz);
                break;
            case 20:
                Parcelable extras = getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, extras);
                break;
            case 21:
                zzro com_google_android_gms_internal_ads_zzro;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    com_google_android_gms_internal_ads_zzro = null;
                } else {
                    zzjz = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    com_google_android_gms_internal_ads_zzro = zzjz instanceof zzro ? (zzro) zzjz : new zzrq(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzro);
                parcel2.writeNoException();
                break;
            case 22:
                cancelUnconfirmedClick();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
