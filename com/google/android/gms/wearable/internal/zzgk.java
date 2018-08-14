package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzgk {
    private static Map<String, CapabilityInfo> zza(List<zzah> list) {
        Map hashMap = new HashMap();
        if (list != null) {
            for (zzah com_google_android_gms_wearable_internal_zzah : list) {
                hashMap.put(com_google_android_gms_wearable_internal_zzah.getName(), new zzw(com_google_android_gms_wearable_internal_zzah));
            }
        }
        return hashMap;
    }
}
