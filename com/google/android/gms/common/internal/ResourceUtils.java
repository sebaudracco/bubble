package com.google.android.gms.common.internal;

import android.net.Uri;
import android.net.Uri.Builder;

public final class ResourceUtils {
    private static final Uri zzuw = new Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();

    private ResourceUtils() {
    }

    public static Uri getDrawableUri(String str) {
        Preconditions.checkNotNull(str, "Resource name must not be null.");
        return zzuw.buildUpon().appendPath(str).build();
    }
}
