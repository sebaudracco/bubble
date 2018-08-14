package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Callable;

final class zzagd implements Callable<zzaga> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzagc zzckk;

    zzagd(zzagc com_google_android_gms_internal_ads_zzagc, Context context) {
        this.zzckk = com_google_android_gms_internal_ads_zzagc;
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzaga zzoo;
        zzage com_google_android_gms_internal_ads_zzage = (zzage) zzagc.zza(this.zzckk).get(this.val$context);
        if (com_google_android_gms_internal_ads_zzage != null) {
            if ((com_google_android_gms_internal_ads_zzage.zzckl + ((Long) zzkb.zzik().zzd(zznk.zzazw)).longValue() < zzbv.zzer().currentTimeMillis() ? 1 : null) == null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzazv)).booleanValue()) {
                    zzoo = new zzagb(this.val$context, com_google_android_gms_internal_ads_zzage.zzckm).zzoo();
                    zzagc.zza(this.zzckk).put(this.val$context, new zzage(this.zzckk, zzoo));
                    return zzoo;
                }
            }
        }
        zzoo = new zzagb(this.val$context).zzoo();
        zzagc.zza(this.zzckk).put(this.val$context, new zzage(this.zzckk, zzoo));
        return zzoo;
    }
}
