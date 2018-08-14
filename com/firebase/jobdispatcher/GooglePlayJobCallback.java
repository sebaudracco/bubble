package com.firebase.jobdispatcher;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class GooglePlayJobCallback implements JobCallback {
    private static final String DESCRIPTOR = "com.google.android.gms.gcm.INetworkTaskCallback";
    private static final int TRANSACTION_TASK_FINISHED = 2;
    private final IBinder mRemote;

    public GooglePlayJobCallback(IBinder binder) {
        this.mRemote = binder;
    }

    public void jobFinished(int status) {
        Parcel request = Parcel.obtain();
        Parcel response = Parcel.obtain();
        try {
            request.writeInterfaceToken(DESCRIPTOR);
            request.writeInt(status);
            this.mRemote.transact(2, request, response, 0);
            response.readException();
            request.recycle();
            response.recycle();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            request.recycle();
            response.recycle();
        }
    }
}
