package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class zzev implements zzgd {
    private WeakReference<zzoz> zzafl;

    public zzev(zzoz com_google_android_gms_internal_ads_zzoz) {
        this.zzafl = new WeakReference(com_google_android_gms_internal_ads_zzoz);
    }

    @Nullable
    public final View zzgh() {
        zzoz com_google_android_gms_internal_ads_zzoz = (zzoz) this.zzafl.get();
        return com_google_android_gms_internal_ads_zzoz != null ? com_google_android_gms_internal_ads_zzoz.zzkr() : null;
    }

    public final boolean zzgi() {
        return this.zzafl.get() == null;
    }

    public final zzgd zzgj() {
        return new zzex((zzoz) this.zzafl.get());
    }
}
