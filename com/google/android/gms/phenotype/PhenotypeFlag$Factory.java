package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PhenotypeFlag$Factory {
    private final String zzax;
    private final Uri zzay;
    private final String zzaz;
    private final String zzba;
    private final boolean zzbb;
    private final boolean zzbc;

    @KeepForSdk
    public PhenotypeFlag$Factory(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    private PhenotypeFlag$Factory(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.zzax = str;
        this.zzay = uri;
        this.zzaz = str2;
        this.zzba = str3;
        this.zzbb = z;
        this.zzbc = z2;
    }

    @KeepForSdk
    public PhenotypeFlag<String> createFlag(String str, String str2) {
        return PhenotypeFlag.zzb(this, str, str2);
    }

    @KeepForSdk
    public PhenotypeFlag$Factory withGservicePrefix(String str) {
        if (this.zzbb) {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }
        return new PhenotypeFlag$Factory(this.zzax, this.zzay, str, this.zzba, this.zzbb, this.zzbc);
    }

    @KeepForSdk
    public PhenotypeFlag$Factory withPhenotypePrefix(String str) {
        return new PhenotypeFlag$Factory(this.zzax, this.zzay, this.zzaz, str, this.zzbb, this.zzbc);
    }
}
