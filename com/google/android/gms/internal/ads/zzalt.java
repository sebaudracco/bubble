package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzalt {
    private static zzv zzctf;
    private static final Object zzctg = new Object();
    @Deprecated
    private static final zzalz<Void> zzcth = new zzalu();

    public zzalt(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        zzbb(context);
    }

    @VisibleForTesting
    private static zzv zzbb(Context context) {
        zzv zzba;
        synchronized (zzctg) {
            if (zzctf == null) {
                zznk.initialize(context);
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbdv)).booleanValue()) {
                    zzba = zzaln.zzba(context);
                } else {
                    zzba = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj(new zzas()));
                    zzba.start();
                }
                zzctf = zzba;
            }
            zzba = zzctf;
        }
        return zzba;
    }

    public final zzanz<String> zza(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        Object com_google_android_gms_internal_ads_zzama = new zzama(null);
        zzy com_google_android_gms_internal_ads_zzalx = new zzalx(this, str, com_google_android_gms_internal_ads_zzama);
        zzamy com_google_android_gms_internal_ads_zzamy = new zzamy(null);
        zzr com_google_android_gms_internal_ads_zzaly = new zzaly(this, i, str, com_google_android_gms_internal_ads_zzama, com_google_android_gms_internal_ads_zzalx, bArr, map, com_google_android_gms_internal_ads_zzamy);
        if (zzamy.isEnabled()) {
            try {
                com_google_android_gms_internal_ads_zzamy.zza(str, "GET", com_google_android_gms_internal_ads_zzaly.getHeaders(), com_google_android_gms_internal_ads_zzaly.zzg());
            } catch (Throwable e) {
                zzane.zzdk(e.getMessage());
            }
        }
        zzctf.zze(com_google_android_gms_internal_ads_zzaly);
        return com_google_android_gms_internal_ads_zzama;
    }

    @Deprecated
    public final <T> zzanz<T> zza(String str, zzalz<T> com_google_android_gms_internal_ads_zzalz_T) {
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        zzctf.zze(new zzamb(str, com_google_android_gms_internal_ads_zzaoj));
        return zzano.zza(zzano.zza(com_google_android_gms_internal_ads_zzaoj, new zzalw(this, com_google_android_gms_internal_ads_zzalz_T), zzaki.zzcrj), Throwable.class, new zzalv(this, com_google_android_gms_internal_ads_zzalz_T), zzaoe.zzcvz);
    }

    public final zzanz<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
