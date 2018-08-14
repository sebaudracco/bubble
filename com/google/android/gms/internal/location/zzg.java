package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzg extends zzj {
    private final /* synthetic */ PendingIntent zzbx;

    zzg(zze com_google_android_gms_internal_location_zze, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.zzbx = pendingIntent;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zzb(this.zzbx);
        setResult(Status.RESULT_SUCCESS);
    }
}
