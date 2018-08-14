package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzaag implements OnClickListener {
    private final /* synthetic */ zzaae zzbwq;

    zzaag(zzaae com_google_android_gms_internal_ads_zzaae) {
        this.zzbwq = com_google_android_gms_internal_ads_zzaae;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzbwq.zzbw("User canceled the download.");
    }
}
