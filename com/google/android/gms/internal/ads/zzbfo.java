package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfo extends zzbfc<zzbfo> {
    private static volatile zzbfo[] zzecw;
    public byte[] zzecx;
    public byte[] zzecy;

    public zzbfo() {
        this.zzecx = null;
        this.zzecy = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzbfo[] zzagt() {
        if (zzecw == null) {
            synchronized (zzbfg.zzebs) {
                if (zzecw == null) {
                    zzecw = new zzbfo[0];
                }
            }
        }
        return zzecw;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzecx = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 18:
                    this.zzecy = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_ads_zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        com_google_android_gms_internal_ads_zzbfa.zza(1, this.zzecx);
        if (this.zzecy != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzecy);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr() + zzbfa.zzb(1, this.zzecx);
        return this.zzecy != null ? zzr + zzbfa.zzb(2, this.zzecy) : zzr;
    }
}
