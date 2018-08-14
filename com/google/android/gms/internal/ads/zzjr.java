package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.FrameLayout;
import com.appnext.base.p023b.C1042c;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public class zzjr {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private zzld zzari;
    private final zzjh zzarj;
    private final zzjg zzark;
    private final zzme zzarl;
    private final zzrv zzarm;
    private final zzahi zzarn;
    private final zzaao zzaro;
    private final zzrw zzarp;

    public zzjr(zzjh com_google_android_gms_internal_ads_zzjh, zzjg com_google_android_gms_internal_ads_zzjg, zzme com_google_android_gms_internal_ads_zzme, zzrv com_google_android_gms_internal_ads_zzrv, zzahi com_google_android_gms_internal_ads_zzahi, zzaao com_google_android_gms_internal_ads_zzaao, zzrw com_google_android_gms_internal_ads_zzrw) {
        this.zzarj = com_google_android_gms_internal_ads_zzjh;
        this.zzark = com_google_android_gms_internal_ads_zzjg;
        this.zzarl = com_google_android_gms_internal_ads_zzme;
        this.zzarm = com_google_android_gms_internal_ads_zzrv;
        this.zzarn = com_google_android_gms_internal_ads_zzahi;
        this.zzaro = com_google_android_gms_internal_ads_zzaao;
        this.zzarp = com_google_android_gms_internal_ads_zzrw;
    }

    @VisibleForTesting
    static <T> T zza(Context context, boolean z, zza<T> com_google_android_gms_internal_ads_zzjr_zza_T) {
        Object obj = 1;
        Object obj2 = z ? 1 : null;
        if (obj2 == null) {
            zzkb.zzif();
            if (!zzamu.zzbe(context)) {
                zzane.zzck("Google Play Services is not available");
                obj2 = 1;
            }
        }
        zzkb.zzif();
        int zzbg = zzamu.zzbg(context);
        zzkb.zzif();
        if (zzbg <= zzamu.zzbf(context)) {
            obj = obj2;
        }
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzber)).booleanValue()) {
            obj = null;
        }
        T zzic;
        if (obj != null) {
            zzic = com_google_android_gms_internal_ads_zzjr_zza_T.zzic();
            return zzic == null ? com_google_android_gms_internal_ads_zzjr_zza_T.zzid() : zzic;
        } else {
            zzic = com_google_android_gms_internal_ads_zzjr_zza_T.zzid();
            return zzic == null ? com_google_android_gms_internal_ads_zzjr_zza_T.zzic() : zzic;
        }
    }

    private static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(C1042c.jL, "no_ads_fallback");
        bundle.putString("flow", str);
        zzkb.zzif().zza(context, null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzld zzhz() {
        try {
            Object newInstance = zzjr.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzle.asInterface((IBinder) newInstance);
            }
            zzane.zzdk("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            zzane.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final zzld zzia() {
        zzld com_google_android_gms_internal_ads_zzld;
        synchronized (this.mLock) {
            if (this.zzari == null) {
                this.zzari = zzhz();
            }
            com_google_android_gms_internal_ads_zzld = this.zzari;
        }
        return com_google_android_gms_internal_ads_zzld;
    }

    public final zzqa zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqa) zza(context, false, new zzjx(this, frameLayout, frameLayout2, context));
    }

    public final zzqf zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzqf) zza(view.getContext(), false, new zzjy(this, view, hashMap, hashMap2));
    }

    @Nullable
    public final zzaap zzb(Activity activity) {
        boolean z = false;
        String str = "com.google.android.gms.ads.internal.overlay.useClientJar";
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            z = intent.getBooleanExtra(str, false);
        } else {
            zzane.m3427e("useClientJar flag not found in activity intent extras.");
        }
        return (zzaap) zza((Context) activity, z, new zzka(this, activity));
    }

    public final zzkn zzb(Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn) {
        return (zzkn) zza(context, false, new zzjv(this, context, str, com_google_android_gms_internal_ads_zzxn));
    }
}
