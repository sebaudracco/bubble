package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzakk;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzx {
    private final Context mContext;
    private boolean zzxc;
    private zzait zzxd;
    private zzael zzxe;

    public zzx(Context context, zzait com_google_android_gms_internal_ads_zzait, zzael com_google_android_gms_internal_ads_zzael) {
        this.mContext = context;
        this.zzxd = com_google_android_gms_internal_ads_zzait;
        this.zzxe = com_google_android_gms_internal_ads_zzael;
        if (this.zzxe == null) {
            this.zzxe = new zzael();
        }
    }

    private final boolean zzcx() {
        return (this.zzxd != null && this.zzxd.zzpg().zzcni) || this.zzxe.zzcfr;
    }

    public final void recordClick() {
        this.zzxc = true;
    }

    public final boolean zzcy() {
        return !zzcx() || this.zzxc;
    }

    public final void zzs(@Nullable String str) {
        if (zzcx()) {
            if (str == null) {
                str = "";
            }
            if (this.zzxd != null) {
                this.zzxd.zza(str, null, 3);
            } else if (this.zzxe.zzcfr && this.zzxe.zzcfs != null) {
                for (String str2 : this.zzxe.zzcfs) {
                    String str22;
                    if (!TextUtils.isEmpty(str22)) {
                        str22 = str22.replace("{NAVIGATION_URL}", Uri.encode(str));
                        zzbv.zzek();
                        zzakk.zzd(this.mContext, "", str22);
                    }
                }
            }
        }
    }
}
