package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.plus.internal.zzh;
import java.util.Collection;

final class zzn extends zzp {
    private final /* synthetic */ Collection zzal;

    zzn(zzj com_google_android_gms_internal_plus_zzj, GoogleApiClient googleApiClient, Collection collection) {
        this.zzal = collection;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzh) anyClient).zza((ResultHolder) this, this.zzal);
    }
}
