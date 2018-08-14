package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.util.List;

final class zzakl implements zzoi {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ List zzcrs;
    private final /* synthetic */ zzoh zzcrt;

    zzakl(zzakk com_google_android_gms_internal_ads_zzakk, List list, zzoh com_google_android_gms_internal_ads_zzoh, Context context) {
        this.zzcrs = list;
        this.zzcrt = com_google_android_gms_internal_ads_zzoh;
        this.val$context = context;
    }

    public final void zzjp() {
        for (String str : this.zzcrs) {
            String str2 = "Pinging url: ";
            String valueOf = String.valueOf(str);
            zzakb.zzdj(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.zzcrt.mayLaunchUrl(Uri.parse(str), null, null);
        }
        this.zzcrt.zzc((Activity) this.val$context);
    }

    public final void zzjq() {
    }
}
