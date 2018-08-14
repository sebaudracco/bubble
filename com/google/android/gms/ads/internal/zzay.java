package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzagr;
import com.google.android.gms.internal.ads.zzaib;
import com.google.android.gms.internal.ads.zzald;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlk;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzay extends zzlk {
    private static final Object sLock = new Object();
    @Nullable
    @GuardedBy("sLock")
    private static zzay zzzu;
    private final Context mContext;
    private final Object mLock = new Object();
    private boolean zzzv;
    private zzang zzzw;

    @VisibleForTesting
    private zzay(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        this.mContext = context;
        this.zzzw = com_google_android_gms_internal_ads_zzang;
        this.zzzv = false;
    }

    public static zzay zza(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        zzay com_google_android_gms_ads_internal_zzay;
        synchronized (sLock) {
            if (zzzu == null) {
                zzzu = new zzay(context.getApplicationContext(), com_google_android_gms_internal_ads_zzang);
            }
            com_google_android_gms_ads_internal_zzay = zzzu;
        }
        return com_google_android_gms_ads_internal_zzay;
    }

    public final void setAppMuted(boolean z) {
        zzbv.zzfj().setAppMuted(z);
    }

    public final void setAppVolume(float f) {
        zzbv.zzfj().setAppVolume(f);
    }

    public final void zza() {
        synchronized (sLock) {
            if (this.zzzv) {
                zzane.zzdk("Mobile ads is initialized already.");
                return;
            }
            this.zzzv = true;
            zznk.initialize(this.mContext);
            zzbv.zzeo().zzd(this.mContext, this.zzzw);
            zzbv.zzeq().initialize(this.mContext);
        }
    }

    final /* synthetic */ void zza(Runnable runnable) {
        Context context = this.mContext;
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map zzpw = zzbv.zzeo().zzqh().zzra().zzpw();
        if (zzpw != null && !zzpw.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzane.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzagr zzox = zzagr.zzox();
            if (zzox != null) {
                String valueOf;
                Collection<zzwy> values = zzpw.values();
                Map hashMap = new HashMap();
                IObjectWrapper wrap = ObjectWrapper.wrap(context);
                for (zzwy com_google_android_gms_internal_ads_zzwy : values) {
                    for (zzwx com_google_android_gms_internal_ads_zzwx : com_google_android_gms_internal_ads_zzwy.zzbsm) {
                        String str = com_google_android_gms_internal_ads_zzwx.zzbsb;
                        for (String valueOf2 : com_google_android_gms_internal_ads_zzwx.zzbrt) {
                            if (!hashMap.containsKey(valueOf2)) {
                                hashMap.put(valueOf2, new ArrayList());
                            }
                            if (str != null) {
                                ((Collection) hashMap.get(valueOf2)).add(str);
                            }
                        }
                    }
                }
                for (Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    try {
                        zzaib zzca = zzox.zzca(str2);
                        if (zzca != null) {
                            zzxq zzpe = zzca.zzpe();
                            if (!zzpe.isInitialized() && zzpe.zzms()) {
                                zzpe.zza(wrap, zzca.zzpf(), (List) entry.getValue());
                                String str3 = "Initialized rewarded video mediation adapter ";
                                valueOf2 = String.valueOf(str2);
                                zzane.zzck(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                            }
                        }
                    } catch (Throwable th2) {
                        zzane.zzc(new StringBuilder(String.valueOf(str2).length() + 56).append("Failed to initialize rewarded video mediation adapter \"").append(str2).append("\"").toString(), th2);
                    }
                }
            }
        }
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        if (!TextUtils.isEmpty(str)) {
            Runnable com_google_android_gms_ads_internal_zzaz;
            int i;
            zznk.initialize(this.mContext);
            int booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbcs)).booleanValue() | ((Boolean) zzkb.zzik().zzd(zznk.zzayd)).booleanValue();
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayd)).booleanValue()) {
                com_google_android_gms_ads_internal_zzaz = new zzaz(this, (Runnable) ObjectWrapper.unwrap(iObjectWrapper));
                i = 1;
            } else {
                com_google_android_gms_ads_internal_zzaz = null;
                i = booleanValue;
            }
            if (i != 0) {
                zzbv.zzes().zza(this.mContext, this.zzzw, str, com_google_android_gms_ads_internal_zzaz);
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzane.m3427e("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            zzane.m3427e("Context is null. Failed to open debug menu.");
            return;
        }
        zzald com_google_android_gms_internal_ads_zzald = new zzald(context);
        com_google_android_gms_internal_ads_zzald.setAdUnitId(str);
        com_google_android_gms_internal_ads_zzald.zzda(this.zzzw.zzcw);
        com_google_android_gms_internal_ads_zzald.showDialog();
    }

    public final float zzdo() {
        return zzbv.zzfj().zzdo();
    }

    public final boolean zzdp() {
        return zzbv.zzfj().zzdp();
    }

    public final void zzt(String str) {
        zznk.initialize(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcs)).booleanValue()) {
                zzbv.zzes().zza(this.mContext, this.zzzw, str, null);
            }
        }
    }
}
