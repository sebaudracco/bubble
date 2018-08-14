package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet$zzb;

final class zzaa extends Wallet$zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ String zzgf;
    private final /* synthetic */ String zzgg;

    zzaa(zzw com_google_android_gms_internal_wallet_zzw, GoogleApiClient googleApiClient, String str, String str2, int i) {
        this.zzgf = str;
        this.zzgg = str2;
        this.val$requestCode = i;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzad) anyClient);
    }

    protected final void zza(zzad com_google_android_gms_internal_wallet_zzad) {
        com_google_android_gms_internal_wallet_zzad.zza(this.zzgf, this.zzgg, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
