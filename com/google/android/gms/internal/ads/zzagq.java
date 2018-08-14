package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzagq extends zzaha {
    private final Context mContext;
    private final Object mLock;
    @GuardedBy("mLock")
    private final zzagr zzcld;
    private final zzang zzyf;

    public zzagq(Context context, zzw com_google_android_gms_ads_internal_zzw, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang) {
        this(context, com_google_android_gms_internal_ads_zzang, new zzagr(context, com_google_android_gms_ads_internal_zzw, zzjn.zzhx(), com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang));
    }

    @VisibleForTesting
    private zzagq(Context context, zzang com_google_android_gms_internal_ads_zzang, zzagr com_google_android_gms_internal_ads_zzagr) {
        this.mLock = new Object();
        this.mContext = context;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzcld = com_google_android_gms_internal_ads_zzagr;
    }

    public final void destroy() {
        zzf(null);
    }

    public final String getMediationAdapterClassName() {
        String mediationAdapterClassName;
        synchronized (this.mLock) {
            mediationAdapterClassName = this.zzcld.getMediationAdapterClassName();
        }
        return mediationAdapterClassName;
    }

    public final boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.mLock) {
            isLoaded = this.zzcld.isLoaded();
        }
        return isLoaded;
    }

    public final void pause() {
        zzd(null);
    }

    public final void resume() {
        zze(null);
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            this.zzcld.setImmersiveMode(z);
        }
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzcld.setUserId(str);
        }
    }

    public final void show() {
        synchronized (this.mLock) {
            this.zzcld.zzoy();
        }
    }

    public final void zza(zzagx com_google_android_gms_internal_ads_zzagx) {
        synchronized (this.mLock) {
            this.zzcld.zza(com_google_android_gms_internal_ads_zzagx);
        }
    }

    public final void zza(zzahe com_google_android_gms_internal_ads_zzahe) {
        synchronized (this.mLock) {
            this.zzcld.zza(com_google_android_gms_internal_ads_zzahe);
        }
    }

    public final void zza(zzahk com_google_android_gms_internal_ads_zzahk) {
        synchronized (this.mLock) {
            this.zzcld.zza(com_google_android_gms_internal_ads_zzahk);
        }
    }

    public final void zza(zzkx com_google_android_gms_internal_ads_zzkx) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            synchronized (this.mLock) {
                this.zzcld.zza(com_google_android_gms_internal_ads_zzkx);
            }
        }
    }

    public final Bundle zzba() {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            return new Bundle();
        }
        Bundle zzba;
        synchronized (this.mLock) {
            zzba = this.zzcld.zzba();
        }
        return zzba;
    }

    public final void zzd(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.pause();
        }
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            Context context = iObjectWrapper == null ? null : (Context) ObjectWrapper.unwrap(iObjectWrapper);
            if (context != null) {
                try {
                    this.zzcld.onContextChanged(context);
                } catch (Throwable e) {
                    zzane.zzc("Unable to extract updated context.", e);
                }
            }
            this.zzcld.resume();
        }
    }

    public final void zzf(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.destroy();
        }
    }
}
