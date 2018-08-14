package com.google.android.gms.internal.ads;

final /* synthetic */ class zzayu {
    static final /* synthetic */ int[] zzdnm = new int[zzayw.values().length];
    static final /* synthetic */ int[] zzdnn = new int[zzayv.values().length];

    static {
        try {
            zzdnn[zzayv.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzdnn[zzayv.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzdnn[zzayv.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzdnm[zzayw.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzdnm[zzayw.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
    }
}
