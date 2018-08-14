package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi.RemoveLocalCapabilityResult;

final class zzs extends zzn<RemoveLocalCapabilityResult> {
    private final /* synthetic */ String zzbp;

    zzs(zzo com_google_android_gms_wearable_internal_zzo, GoogleApiClient googleApiClient, String str) {
        this.zzbp = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new zzu(status);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzhg com_google_android_gms_wearable_internal_zzhg = (zzhg) anyClient;
        ((zzep) com_google_android_gms_wearable_internal_zzhg.getService()).zzb(new zzhd(this), this.zzbp);
    }
}
