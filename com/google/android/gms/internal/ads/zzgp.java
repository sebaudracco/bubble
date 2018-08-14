package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgp {
    private final float zzais;
    private final float zzait;
    private final float zzaiu;
    private final float zzaiv;
    private final int zzaiw;

    @VisibleForTesting
    public zzgp(float f, float f2, float f3, float f4, int i) {
        this.zzais = f;
        this.zzait = f2;
        this.zzaiu = f + f3;
        this.zzaiv = f2 + f4;
        this.zzaiw = i;
    }

    final float zzhb() {
        return this.zzais;
    }

    final float zzhc() {
        return this.zzait;
    }

    final float zzhd() {
        return this.zzaiu;
    }

    final float zzhe() {
        return this.zzaiv;
    }

    final int zzhf() {
        return this.zzaiw;
    }
}
