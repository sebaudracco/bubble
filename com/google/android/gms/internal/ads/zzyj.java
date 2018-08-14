package com.google.android.gms.internal.ads;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@zzadh
public final class zzyj implements MediationAdRequest {
    private final int zzaqn;
    private final boolean zzaqz;
    private final int zzbur;
    private final Date zzhl;
    private final Set<String> zzhn;
    private final boolean zzho;
    private final Location zzhp;

    public zzyj(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, boolean z2) {
        this.zzhl = date;
        this.zzaqn = i;
        this.zzhn = set;
        this.zzhp = location;
        this.zzho = z;
        this.zzbur = i2;
        this.zzaqz = z2;
    }

    public final Date getBirthday() {
        return this.zzhl;
    }

    public final int getGender() {
        return this.zzaqn;
    }

    public final Set<String> getKeywords() {
        return this.zzhn;
    }

    public final Location getLocation() {
        return this.zzhp;
    }

    public final boolean isDesignedForFamilies() {
        return this.zzaqz;
    }

    public final boolean isTesting() {
        return this.zzho;
    }

    public final int taggedForChildDirectedTreatment() {
        return this.zzbur;
    }
}
