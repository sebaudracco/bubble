package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.RewardItem;

@zzadh
public final class zzahh implements RewardItem {
    private final zzagu zzcli;

    public zzahh(zzagu com_google_android_gms_internal_ads_zzagu) {
        this.zzcli = com_google_android_gms_internal_ads_zzagu;
    }

    public final int getAmount() {
        int i = 0;
        if (this.zzcli != null) {
            try {
                i = this.zzcli.getAmount();
            } catch (Throwable e) {
                zzane.zzc("Could not forward getAmount to RewardItem", e);
            }
        }
        return i;
    }

    public final String getType() {
        String str = null;
        if (this.zzcli != null) {
            try {
                str = this.zzcli.getType();
            } catch (Throwable e) {
                zzane.zzc("Could not forward getType to RewardItem", e);
            }
        }
        return str;
    }
}
