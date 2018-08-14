package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzxc {
    private final String zzbnr;
    private final Uri zzbns;
    private final String zzbnt;
    private final String zzbnu;
    private final boolean zzbnv;
    private final boolean zzbnw;

    public zzxc(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    private zzxc(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.zzbnr = null;
        this.zzbns = uri;
        this.zzbnt = str2;
        this.zzbnu = str3;
        this.zzbnv = false;
        this.zzbnw = false;
    }

    public final zzws<Double> zzb(String str, double d) {
        return zzws.zzb(this, str, d);
    }

    public final zzws<Integer> zzd(String str, int i) {
        return zzws.zzb(this, str, i);
    }

    public final zzws<Long> zze(String str, long j) {
        return zzws.zzb(this, str, j);
    }

    public final zzws<Boolean> zzf(String str, boolean z) {
        return zzws.zzb(this, str, z);
    }

    public final zzws<String> zzv(String str, String str2) {
        return zzws.zzb(this, str, str2);
    }
}
