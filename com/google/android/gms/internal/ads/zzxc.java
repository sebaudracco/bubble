package com.google.android.gms.internal.ads;

final class zzxc implements Runnable {
    private final /* synthetic */ zzxa zzbts;
    private final /* synthetic */ zzxb zzbtt;

    zzxc(zzxb com_google_android_gms_internal_ads_zzxb, zzxa com_google_android_gms_internal_ads_zzxa) {
        this.zzbtt = com_google_android_gms_internal_ads_zzxb;
        this.zzbts = com_google_android_gms_internal_ads_zzxa;
    }

    public final void run() {
        synchronized (zzxb.zza(this.zzbtt)) {
            if (zzxb.zzb(this.zzbtt) != -2) {
                return;
            }
            zzxb.zza(this.zzbtt, zzxb.zzc(this.zzbtt));
            if (zzxb.zzd(this.zzbtt) == null) {
                this.zzbtt.zzx(4);
            } else if (!zzxb.zze(this.zzbtt) || zzxb.zza(this.zzbtt, 1)) {
                this.zzbts.zza(this.zzbtt);
                zzxb.zza(this.zzbtt, this.zzbts);
            } else {
                String zzf = zzxb.zzf(this.zzbtt);
                zzakb.zzdk(new StringBuilder(String.valueOf(zzf).length() + 56).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
                this.zzbtt.zzx(2);
            }
        }
    }
}
