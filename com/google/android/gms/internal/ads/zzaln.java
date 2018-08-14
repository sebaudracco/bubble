package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

@zzadh
public final class zzaln extends zzaj {
    private final Context mContext;

    private zzaln(Context context, zzar com_google_android_gms_internal_ads_zzar) {
        super(com_google_android_gms_internal_ads_zzar);
        this.mContext = context;
    }

    public static zzv zzba(Context context) {
        zzv com_google_android_gms_internal_ads_zzv = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley")), new zzaln(context, new zzas()));
        com_google_android_gms_internal_ads_zzv.start();
        return com_google_android_gms_internal_ads_zzv;
    }

    public final zzp zzc(zzr<?> com_google_android_gms_internal_ads_zzr_) throws zzae {
        if (com_google_android_gms_internal_ads_zzr_.zzh() && com_google_android_gms_internal_ads_zzr_.getMethod() == 0) {
            if (Pattern.matches((String) zzkb.zzik().zzd(zznk.zzbdw), com_google_android_gms_internal_ads_zzr_.getUrl())) {
                zzkb.zzif();
                if (zzamu.zzbe(this.mContext)) {
                    zzp zzc = new zzsm(this.mContext).zzc((zzr) com_google_android_gms_internal_ads_zzr_);
                    String valueOf;
                    if (zzc != null) {
                        String str = "Got gmscore asset response: ";
                        valueOf = String.valueOf(com_google_android_gms_internal_ads_zzr_.getUrl());
                        zzakb.m3428v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return zzc;
                    }
                    String str2 = "Failed to get gmscore asset response: ";
                    valueOf = String.valueOf(com_google_android_gms_internal_ads_zzr_.getUrl());
                    zzakb.m3428v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
        return super.zzc(com_google_android_gms_internal_ads_zzr_);
    }
}
