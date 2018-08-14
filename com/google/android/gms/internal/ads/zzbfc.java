package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfc<M extends zzbfc<M>> extends zzbfi {
    protected zzbfe zzebk;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbfc com_google_android_gms_internal_ads_zzbfc = (zzbfc) super.zzago();
        zzbfg.zza(this, com_google_android_gms_internal_ads_zzbfc);
        return com_google_android_gms_internal_ads_zzbfc;
    }

    public void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzebk != null) {
            for (int i = 0; i < this.zzebk.size(); i++) {
                this.zzebk.zzdg(i).zza(com_google_android_gms_internal_ads_zzbfa);
            }
        }
    }

    protected final boolean zza(zzbez com_google_android_gms_internal_ads_zzbez, int i) throws IOException {
        int position = com_google_android_gms_internal_ads_zzbez.getPosition();
        if (!com_google_android_gms_internal_ads_zzbez.zzbq(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbfk com_google_android_gms_internal_ads_zzbfk = new zzbfk(i, com_google_android_gms_internal_ads_zzbez.zzab(position, com_google_android_gms_internal_ads_zzbez.getPosition() - position));
        zzbff com_google_android_gms_internal_ads_zzbff = null;
        if (this.zzebk == null) {
            this.zzebk = new zzbfe();
        } else {
            com_google_android_gms_internal_ads_zzbff = this.zzebk.zzdf(i2);
        }
        if (com_google_android_gms_internal_ads_zzbff == null) {
            com_google_android_gms_internal_ads_zzbff = new zzbff();
            this.zzebk.zza(i2, com_google_android_gms_internal_ads_zzbff);
        }
        com_google_android_gms_internal_ads_zzbff.zza(com_google_android_gms_internal_ads_zzbfk);
        return true;
    }

    public final /* synthetic */ zzbfi zzago() throws CloneNotSupportedException {
        return (zzbfc) clone();
    }

    protected int zzr() {
        int i = 0;
        if (this.zzebk == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzebk.size()) {
            i2 += this.zzebk.zzdg(i).zzr();
            i++;
        }
        return i2;
    }
}
