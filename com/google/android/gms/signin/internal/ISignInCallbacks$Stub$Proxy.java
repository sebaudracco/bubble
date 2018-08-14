package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzc;

public class ISignInCallbacks$Stub$Proxy extends zza implements ISignInCallbacks {
    ISignInCallbacks$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    public void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) connectionResult);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) authAccountResult);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) status);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) googleSignInAccount);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    public void onRecordConsentComplete(Status status) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) status);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    public void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) status);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    public void onSignInComplete(SignInResponse signInResponse) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) signInResponse);
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
}
