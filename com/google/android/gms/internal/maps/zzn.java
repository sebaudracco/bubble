package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface zzn extends IInterface {
    int getActiveLevelIndex() throws RemoteException;

    int getDefaultLevelIndex() throws RemoteException;

    List<IBinder> getLevels() throws RemoteException;

    boolean isUnderground() throws RemoteException;

    boolean zzb(zzn com_google_android_gms_internal_maps_zzn) throws RemoteException;

    int zzi() throws RemoteException;
}
