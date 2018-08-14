package com.google.android.gms.internal.ads;

final /* synthetic */ class zzavi {
    static final /* synthetic */ int[] zzdhz = new int[zzaxa.values().length];
    static final /* synthetic */ int[] zzdia = new int[zzawy.values().length];
    static final /* synthetic */ int[] zzdib = new int[zzawk.values().length];

    static {
        try {
            zzdib[zzawk.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzdib[zzawk.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzdia[zzawy.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzdia[zzawy.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzdia[zzawy.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzdhz[zzaxa.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zzdhz[zzaxa.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            zzdhz[zzaxa.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
    }
}
