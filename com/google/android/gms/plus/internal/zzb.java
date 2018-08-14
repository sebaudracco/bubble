package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.server.response.SafeParcelResponse;
import com.google.android.gms.internal.plus.zzr;

public interface zzb extends IInterface {
    void zza(int i, Bundle bundle) throws RemoteException;

    void zza(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void zza(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void zza(int i, Bundle bundle, SafeParcelResponse safeParcelResponse) throws RemoteException;

    void zza(int i, zzr com_google_android_gms_internal_plus_zzr) throws RemoteException;

    void zza(Status status) throws RemoteException;

    void zza(DataHolder dataHolder, String str) throws RemoteException;

    void zza(DataHolder dataHolder, String str, String str2) throws RemoteException;

    void zzb(String str) throws RemoteException;

    void zzc(String str) throws RemoteException;
}
