package com.google.android.gms.internal.ads;

import android.os.RemoteException;

@zzadh
public class zzzo {
    public static zzzj zzbs(String str) throws RemoteException {
        try {
            return new zzzp((zzatg) Class.forName(str, false, zzzo.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (Throwable th) {
            RemoteException remoteException = new RemoteException();
        }
    }
}
