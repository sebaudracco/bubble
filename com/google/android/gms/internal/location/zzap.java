package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzal;

public final class zzap extends zza implements zzao {
    zzap(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final Location zza(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(21, obtainAndWriteInterfaceToken);
        Location location = (Location) zzc.zza(transactAndReadException, Location.CREATOR);
        transactAndReadException.recycle();
        return location;
    }

    public final void zza(long j, boolean z, PendingIntent pendingIntent) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        zzc.zza(obtainAndWriteInterfaceToken, true);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) pendingIntent);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    public final void zza(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) iStatusCallback);
        transactAndReadExceptionReturnVoid(73, obtainAndWriteInterfaceToken);
    }

    public final void zza(Location location) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) location);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_location_zzaj);
        transactAndReadExceptionReturnVoid(67, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzbf com_google_android_gms_internal_location_zzbf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_location_zzbf);
        transactAndReadExceptionReturnVoid(59, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzo com_google_android_gms_internal_location_zzo) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_internal_location_zzo);
        transactAndReadExceptionReturnVoid(75, obtainAndWriteInterfaceToken);
    }

    public final void zza(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) activityTransitionRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) iStatusCallback);
        transactAndReadExceptionReturnVoid(72, obtainAndWriteInterfaceToken);
    }

    public final void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzam com_google_android_gms_internal_location_zzam) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) geofencingRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_location_zzam);
        transactAndReadExceptionReturnVoid(57, obtainAndWriteInterfaceToken);
    }

    public final void zza(LocationSettingsRequest locationSettingsRequest, zzaq com_google_android_gms_internal_location_zzaq, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) locationSettingsRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_location_zzaq);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(63, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzal com_google_android_gms_location_zzal, zzam com_google_android_gms_internal_location_zzam) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) com_google_android_gms_location_zzal);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) com_google_android_gms_internal_location_zzam);
        transactAndReadExceptionReturnVoid(74, obtainAndWriteInterfaceToken);
    }

    public final void zza(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    public final LocationAvailability zzb(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(34, obtainAndWriteInterfaceToken);
        LocationAvailability locationAvailability = (LocationAvailability) zzc.zza(transactAndReadException, LocationAvailability.CREATOR);
        transactAndReadException.recycle();
        return locationAvailability;
    }

    public final void zzb(PendingIntent pendingIntent) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) pendingIntent);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
}
