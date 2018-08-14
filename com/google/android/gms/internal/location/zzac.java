package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzac extends zzak {
    private final ResultHolder<Status> zzcq;

    public zzac(ResultHolder<Status> resultHolder) {
        this.zzcq = resultHolder;
    }

    public final void zza(zzad com_google_android_gms_internal_location_zzad) {
        this.zzcq.setResult(com_google_android_gms_internal_location_zzad.getStatus());
    }
}
