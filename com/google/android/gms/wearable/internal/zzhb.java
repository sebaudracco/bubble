package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import java.util.List;
import java.util.concurrent.FutureTask;

final class zzhb extends zzgm<DataItemResult> {
    private final List<FutureTask<Boolean>> zzev;

    zzhb(ResultHolder<DataItemResult> resultHolder, List<FutureTask<Boolean>> list) {
        super(resultHolder);
        this.zzev = list;
    }

    public final void zza(zzfu com_google_android_gms_wearable_internal_zzfu) {
        zza(new zzcg(zzgd.zzb(com_google_android_gms_wearable_internal_zzfu.statusCode), com_google_android_gms_wearable_internal_zzfu.zzdy));
        if (com_google_android_gms_wearable_internal_zzfu.statusCode != 0) {
            for (FutureTask cancel : this.zzev) {
                cancel.cancel(true);
            }
        }
    }
}
