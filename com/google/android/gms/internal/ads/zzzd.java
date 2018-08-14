package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.AdRequest$Gender;

final /* synthetic */ class zzzd {
    private static final /* synthetic */ int[] zzbvf = new int[AdRequest$Gender.values().length];
    static final /* synthetic */ int[] zzbvg = new int[AdRequest$ErrorCode.values().length];

    static {
        try {
            zzbvg[AdRequest$ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzbvg[AdRequest$ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzbvg[AdRequest$ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzbvg[AdRequest$ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzbvf[AdRequest$Gender.FEMALE.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzbvf[AdRequest$Gender.MALE.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zzbvf[AdRequest$Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
    }
}
