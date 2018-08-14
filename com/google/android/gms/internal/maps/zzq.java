package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzq extends IInterface {
    void activate() throws RemoteException;

    String getName() throws RemoteException;

    String getShortName() throws RemoteException;

    boolean zzb(zzq com_google_android_gms_internal_maps_zzq) throws RemoteException;

    int zzi() throws RemoteException;
}
