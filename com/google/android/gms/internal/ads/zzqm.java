package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public final class zzqm extends zzej implements zzqk {
    zzqm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    public final void destroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
    }

    public final String getBody() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final String getCallToAction() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final Bundle getExtras() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final String getHeadline() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final List getImages() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        List zzb = zzel.zzb(transactAndReadException);
        transactAndReadException.recycle();
        return zzb;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final String getPrice() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final double getStarRating() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        double readDouble = transactAndReadException.readDouble();
        transactAndReadException.recycle();
        return readDouble;
    }

    public final String getStore() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final zzlo getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken());
        zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }

    public final void performClick(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }

    public final boolean recordImpression(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        obtainAndWriteInterfaceToken = transactAndReadException(15, obtainAndWriteInterfaceToken);
        boolean zza = zzel.zza(obtainAndWriteInterfaceToken);
        obtainAndWriteInterfaceToken.recycle();
        return zza;
    }

    public final void reportTouchEvent(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    public final zzpw zzjz() throws RemoteException {
        zzpw com_google_android_gms_internal_ads_zzpw;
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_ads_zzpw = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            com_google_android_gms_internal_ads_zzpw = queryLocalInterface instanceof zzpw ? (zzpw) queryLocalInterface : new zzpy(readStrongBinder);
        }
        transactAndReadException.recycle();
        return com_google_android_gms_internal_ads_zzpw;
    }

    public final IObjectWrapper zzka() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzke() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    public final zzps zzkf() throws RemoteException {
        zzps com_google_android_gms_internal_ads_zzps;
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_ads_zzps = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            com_google_android_gms_internal_ads_zzps = queryLocalInterface instanceof zzps ? (zzps) queryLocalInterface : new zzpu(readStrongBinder);
        }
        transactAndReadException.recycle();
        return com_google_android_gms_internal_ads_zzps;
    }
}
