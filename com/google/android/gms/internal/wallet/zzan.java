package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.Wallet$zzb;

final class zzan extends Wallet$zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ CreateWalletObjectsRequest zzet;

    zzan(zzam com_google_android_gms_internal_wallet_zzam, GoogleApiClient googleApiClient, CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        this.zzet = createWalletObjectsRequest;
        this.val$requestCode = i;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzad) anyClient);
    }

    protected final void zza(zzad com_google_android_gms_internal_wallet_zzad) {
        com_google_android_gms_internal_wallet_zzad.zza(this.zzet, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
