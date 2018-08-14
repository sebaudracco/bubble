package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.ChannelApi.OpenChannelResult;

final class zzak extends zzn<OpenChannelResult> {
    private final /* synthetic */ String zzcb;
    private final /* synthetic */ String zzcc;

    zzak(zzaj com_google_android_gms_wearable_internal_zzaj, GoogleApiClient googleApiClient, String str, String str2) {
        this.zzcb = str;
        this.zzcc = str2;
        super(googleApiClient);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzam(status, null);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzhg com_google_android_gms_wearable_internal_zzhg = (zzhg) anyClient;
        ((zzep) com_google_android_gms_wearable_internal_zzhg.getService()).zza(new zzha(this), this.zzcb, this.zzcc);
    }
}
