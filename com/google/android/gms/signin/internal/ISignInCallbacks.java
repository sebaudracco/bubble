package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInCallbacks extends IInterface {

    public static abstract class Stub extends zzb implements ISignInCallbacks {
        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static ISignInCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            return queryLocalInterface instanceof ISignInCallbacks ? (ISignInCallbacks) queryLocalInterface : new Proxy(iBinder);
        }

        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 3:
                    onAuthAccountComplete((ConnectionResult) zzc.zza(parcel, ConnectionResult.CREATOR), (AuthAccountResult) zzc.zza(parcel, AuthAccountResult.CREATOR));
                    break;
                case 4:
                    onSaveAccountToSessionStoreComplete((Status) zzc.zza(parcel, Status.CREATOR));
                    break;
                case 6:
                    onRecordConsentComplete((Status) zzc.zza(parcel, Status.CREATOR));
                    break;
                case 7:
                    onGetCurrentAccountComplete((Status) zzc.zza(parcel, Status.CREATOR), (GoogleSignInAccount) zzc.zza(parcel, GoogleSignInAccount.CREATOR));
                    break;
                case 8:
                    onSignInComplete((SignInResponse) zzc.zza(parcel, SignInResponse.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException;

    void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException;

    void onRecordConsentComplete(Status status) throws RemoteException;

    void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException;

    void onSignInComplete(SignInResponse signInResponse) throws RemoteException;
}
