package com.google.android.gms.internal.ads;

final class zzcd implements Runnable {
    private final /* synthetic */ zzcc zzpx;

    zzcd(zzcc com_google_android_gms_internal_ads_zzcc) {
        this.zzpx = com_google_android_gms_internal_ads_zzcc;
    }

    public final void run() {
        if (this.zzpx.zzpv == null) {
            synchronized (zzcc.zzpt) {
                if (this.zzpx.zzpv != null) {
                    return;
                }
                boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbap)).booleanValue();
                if (booleanValue) {
                    try {
                        zzcc.zzpu = new zzhx(this.zzpx.zzps.zzrt, "ADSHIELD", null);
                    } catch (Throwable th) {
                        booleanValue = false;
                    }
                }
                this.zzpx.zzpv = Boolean.valueOf(booleanValue);
                zzcc.zzpt.open();
            }
        }
    }
}
