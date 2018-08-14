package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzk extends zzp {
    private final /* synthetic */ int zzaj;
    private final /* synthetic */ String zzak;

    zzk(zzj com_google_android_gms_internal_plus_zzj, GoogleApiClient googleApiClient, int i, String str) {
        this.zzaj = i;
        this.zzak = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        setCancelToken(((zzh) anyClient).zza(this, this.zzaj, this.zzak));
    }
}
