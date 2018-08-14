package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzlr extends IInterface {
    void onVideoEnd() throws RemoteException;

    void onVideoMute(boolean z) throws RemoteException;

    void onVideoPause() throws RemoteException;

    void onVideoPlay() throws RemoteException;

    void onVideoStart() throws RemoteException;
}
