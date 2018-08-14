package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfi {
    protected volatile int zzebt = -1;

    public static final <T extends zzbfi> T zza(T t, byte[] bArr) throws zzbfh {
        return zza(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzbfi> T zza(T t, byte[] bArr, int i, int i2) throws zzbfh {
        try {
            zzbez zzi = zzbez.zzi(bArr, 0, i2);
            t.zza(zzi);
            zzi.zzbp(0);
            return t;
        } catch (zzbfh e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zzb(zzbfi com_google_android_gms_internal_ads_zzbfi) {
        byte[] bArr = new byte[com_google_android_gms_internal_ads_zzbfi.zzacw()];
        try {
            zzbfa zzj = zzbfa.zzj(bArr, 0, bArr.length);
            com_google_android_gms_internal_ads_zzbfi.zza(zzj);
            zzj.zzacl();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzago();
    }

    public String toString() {
        return zzbfj.zzc(this);
    }

    public abstract zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException;

    public void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
    }

    public final int zzacw() {
        int zzr = zzr();
        this.zzebt = zzr;
        return zzr;
    }

    public zzbfi zzago() throws CloneNotSupportedException {
        return (zzbfi) super.clone();
    }

    protected int zzr() {
        return 0;
    }
}
