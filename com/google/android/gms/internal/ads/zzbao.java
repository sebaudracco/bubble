package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbao extends zzban {
    protected final byte[] zzdpw;

    zzbao(byte[] bArr) {
        this.zzdpw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbah)) {
            return false;
        }
        if (size() != ((zzbah) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbao)) {
            return obj.equals(this);
        }
        zzbao com_google_android_gms_internal_ads_zzbao = (zzbao) obj;
        int zzabg = zzabg();
        int zzabg2 = com_google_android_gms_internal_ads_zzbao.zzabg();
        return (zzabg == 0 || zzabg2 == 0 || zzabg == zzabg2) ? zza((zzbao) obj, 0, size()) : false;
    }

    public int size() {
        return this.zzdpw.length;
    }

    protected final String zza(Charset charset) {
        return new String(this.zzdpw, zzabh(), size(), charset);
    }

    final void zza(zzbag com_google_android_gms_internal_ads_zzbag) throws IOException {
        com_google_android_gms_internal_ads_zzbag.zzb(this.zzdpw, zzabh(), size());
    }

    protected void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzdpw, 0, bArr, 0, i3);
    }

    final boolean zza(zzbah com_google_android_gms_internal_ads_zzbah, int i, int i2) {
        if (i2 > com_google_android_gms_internal_ads_zzbah.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i2 > com_google_android_gms_internal_ads_zzbah.size()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + com_google_android_gms_internal_ads_zzbah.size());
        } else if (!(com_google_android_gms_internal_ads_zzbah instanceof zzbao)) {
            return com_google_android_gms_internal_ads_zzbah.zzk(0, i2).equals(zzk(0, i2));
        } else {
            zzbao com_google_android_gms_internal_ads_zzbao = (zzbao) com_google_android_gms_internal_ads_zzbah;
            byte[] bArr = this.zzdpw;
            byte[] bArr2 = com_google_android_gms_internal_ads_zzbao.zzdpw;
            int zzabh = zzabh() + i2;
            int zzabh2 = zzabh();
            int zzabh3 = com_google_android_gms_internal_ads_zzbao.zzabh();
            while (zzabh2 < zzabh) {
                if (bArr[zzabh2] != bArr2[zzabh3]) {
                    return false;
                }
                zzabh2++;
                zzabh3++;
            }
            return true;
        }
    }

    public final boolean zzabe() {
        int zzabh = zzabh();
        return zzbem.zzf(this.zzdpw, zzabh, size() + zzabh);
    }

    public final zzbaq zzabf() {
        return zzbaq.zza(this.zzdpw, zzabh(), size(), true);
    }

    protected int zzabh() {
        return 0;
    }

    public byte zzbn(int i) {
        return this.zzdpw[i];
    }

    protected final int zzc(int i, int i2, int i3) {
        return zzbbq.zza(i, this.zzdpw, zzabh(), i3);
    }

    public final zzbah zzk(int i, int i2) {
        int zzd = zzbah.zzd(0, i2, size());
        return zzd == 0 ? zzbah.zzdpq : new zzbak(this.zzdpw, zzabh(), zzd);
    }
}
