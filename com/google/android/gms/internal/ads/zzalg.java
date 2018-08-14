package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class zzalg implements OnClickListener {
    private final zzald zzcsh;
    private final String zzzo;

    zzalg(zzald com_google_android_gms_internal_ads_zzald, String str) {
        this.zzcsh = com_google_android_gms_internal_ads_zzald;
        this.zzzo = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzcsh.zza(this.zzzo, dialogInterface, i);
    }
}
