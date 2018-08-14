package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.SystemClock;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public abstract class zzabh extends zzajx {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzabm zzbzd;
    protected final zzaji zzbze;
    @GuardedBy("mLock")
    protected zzaej zzbzf;
    protected final Object zzbzh = new Object();

    protected zzabh(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzabm com_google_android_gms_internal_ads_zzabm) {
        super(true);
        this.mContext = context;
        this.zzbze = com_google_android_gms_internal_ads_zzaji;
        this.zzbzf = com_google_android_gms_internal_ads_zzaji.zzcos;
        this.zzbzd = com_google_android_gms_internal_ads_zzabm;
    }

    public void onStop() {
    }

    protected abstract zzajh zzaa(int i);

    public final void zzdn() {
        int errorCode;
        synchronized (this.mLock) {
            zzane.zzck("AdRendererBackgroundTask started.");
            int i = this.zzbze.errorCode;
            try {
                zze(SystemClock.elapsedRealtime());
            } catch (zzabk e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzane.zzdj(e.getMessage());
                } else {
                    zzane.zzdk(e.getMessage());
                }
                if (this.zzbzf == null) {
                    this.zzbzf = new zzaej(errorCode);
                } else {
                    this.zzbzf = new zzaej(errorCode, this.zzbzf.zzbsu);
                }
                zzakk.zzcrm.post(new zzabi(this));
                i = errorCode;
            }
            zzakk.zzcrm.post(new zzabj(this, zzaa(i)));
        }
    }

    protected abstract void zze(long j) throws zzabk;
}
