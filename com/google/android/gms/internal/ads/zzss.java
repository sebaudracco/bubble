package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
@VisibleForTesting
public final class zzss {
    private final Context mContext;
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzang zzyf;

    zzss(Context context, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        this.mContext = context;
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzwc = com_google_android_gms_ads_internal_zzw;
    }

    @VisibleForTesting
    public final Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    @VisibleForTesting
    public final zzal zzav(String str) {
        return new zzal(this.mContext, new zzjn(), str, this.zzwh, this.zzyf, this.zzwc);
    }

    @VisibleForTesting
    public final zzal zzaw(String str) {
        return new zzal(this.mContext.getApplicationContext(), new zzjn(), str, this.zzwh, this.zzyf, this.zzwc);
    }

    @VisibleForTesting
    public final zzss zzlc() {
        return new zzss(this.mContext.getApplicationContext(), this.zzwh, this.zzyf, this.zzwc);
    }
}
