package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import java.util.ArrayList;
import java.util.List;

final class zzgu extends zzgm<GetConnectedNodesResult> {
    public zzgu(ResultHolder<GetConnectedNodesResult> resultHolder) {
        super(resultHolder);
    }

    public final void zza(zzea com_google_android_gms_wearable_internal_zzea) {
        List arrayList = new ArrayList();
        if (com_google_android_gms_wearable_internal_zzea.zzdx != null) {
            arrayList.addAll(com_google_android_gms_wearable_internal_zzea.zzdx);
        }
        zza(new zzfj(zzgd.zzb(com_google_android_gms_wearable_internal_zzea.statusCode), arrayList));
    }
}
