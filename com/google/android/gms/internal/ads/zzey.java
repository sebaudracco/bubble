package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;

public final class zzey implements zzgd {
    @Nullable
    private final View mView;
    @Nullable
    private final zzajh zzafn;

    public zzey(View view, zzajh com_google_android_gms_internal_ads_zzajh) {
        this.mView = view;
        this.zzafn = com_google_android_gms_internal_ads_zzajh;
    }

    public final View zzgh() {
        return this.mView;
    }

    public final boolean zzgi() {
        return this.zzafn == null || this.mView == null;
    }

    public final zzgd zzgj() {
        return this;
    }
}
