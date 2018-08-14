package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@TargetApi(21)
@ParametersAreNonnullByDefault
final class zzaiw {
    private static final Map<String, String> zzcnl;
    private final List<String> zzcnm;
    private final zzaii zzcnn;
    private final Context zzrt;

    static {
        Map hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzcnl = hashMap;
    }

    zzaiw(Context context, List<String> list, zzaii com_google_android_gms_internal_ads_zzaii) {
        this.zzrt = context;
        this.zzcnm = list;
        this.zzcnn = com_google_android_gms_internal_ads_zzaii;
    }

    final List<String> zzc(String[] strArr) {
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            Object obj;
            for (String str2 : this.zzcnm) {
                String str22;
                if (!str22.equals(str)) {
                    String valueOf = String.valueOf("android.webkit.resource.");
                    str22 = String.valueOf(str22);
                    if ((str22.length() != 0 ? valueOf.concat(str22) : new String(valueOf)).equals(str)) {
                        int i = 1;
                        break;
                    }
                }
                obj = 1;
                break;
            }
            obj = null;
            if (obj != null) {
                if (zzcnl.containsKey(str)) {
                    zzbv.zzek();
                    if (!zzakk.zzl(this.zzrt, (String) zzcnl.get(str))) {
                        obj = null;
                        if (obj == null) {
                            arrayList.add(str);
                        } else {
                            this.zzcnn.zzch(str);
                        }
                    }
                }
                obj = 1;
                if (obj == null) {
                    this.zzcnn.zzch(str);
                } else {
                    arrayList.add(str);
                }
            } else {
                this.zzcnn.zzcg(str);
            }
        }
        return arrayList;
    }
}
