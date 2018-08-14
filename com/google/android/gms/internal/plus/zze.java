package com.google.android.gms.internal.plus;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.zzh;

public final class zze implements Account {
    public final void clearDefaultAccount(GoogleApiClient googleApiClient) {
        zzh zza = Plus.zza(googleApiClient, false);
        if (zza != null) {
            zza.zza();
        }
    }

    public final String getAccountName(GoogleApiClient googleApiClient) {
        return Plus.zza(googleApiClient, true).getAccountName();
    }

    @SuppressLint({"MissingRemoteException"})
    public final PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzf(this, googleApiClient));
    }
}
