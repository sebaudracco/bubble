package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Iterator;

final class zzakf extends zzakg {
    private final /* synthetic */ zzakd zzcrh;
    private final /* synthetic */ Bundle zzcri;

    zzakf(zzakd com_google_android_gms_internal_ads_zzakd, Bundle bundle) {
        this.zzcrh = com_google_android_gms_internal_ads_zzakd;
        this.zzcri = bundle;
        super();
    }

    public final void zzdn() {
        Iterator it = zzakd.zzr(this.zzcrh).iterator();
        while (it.hasNext()) {
            ((zzakh) it.next()).zzd(this.zzcri);
        }
    }
}
