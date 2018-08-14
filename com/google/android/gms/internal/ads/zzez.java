package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzez implements zzgd {
    private final WeakReference<View> zzafo;
    private final WeakReference<zzajh> zzafp;

    public zzez(View view, zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzafo = new WeakReference(view);
        this.zzafp = new WeakReference(com_google_android_gms_internal_ads_zzajh);
    }

    public final View zzgh() {
        return (View) this.zzafo.get();
    }

    public final boolean zzgi() {
        return this.zzafo.get() == null || this.zzafp.get() == null;
    }

    public final zzgd zzgj() {
        return new zzey((View) this.zzafo.get(), (zzajh) this.zzafp.get());
    }
}
