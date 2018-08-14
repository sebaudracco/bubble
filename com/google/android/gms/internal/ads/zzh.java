package com.google.android.gms.internal.ads;

public final class zzh implements zzab {
    private int zzr;
    private int zzs;
    private final int zzt;
    private final float zzu;

    public zzh() {
        this(2500, 1, 1.0f);
    }

    private zzh(int i, int i2, float f) {
        this.zzr = 2500;
        this.zzt = 1;
        this.zzu = 1.0f;
    }

    public final void zza(zzae com_google_android_gms_internal_ads_zzae) throws zzae {
        this.zzs++;
        this.zzr = (int) (((float) this.zzr) + (((float) this.zzr) * this.zzu));
        if ((this.zzs <= this.zzt ? 1 : null) == null) {
            throw com_google_android_gms_internal_ads_zzae;
        }
    }

    public final int zzc() {
        return this.zzr;
    }

    public final int zzd() {
        return this.zzs;
    }
}
