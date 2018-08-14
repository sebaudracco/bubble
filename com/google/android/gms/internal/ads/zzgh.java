package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@TargetApi(14)
final class zzgh implements ActivityLifecycleCallbacks {
    @Nullable
    private Activity mActivity;
    private Context mContext;
    private final Object mLock = new Object();
    private boolean zzahr = true;
    private boolean zzahs = false;
    @GuardedBy("mLock")
    private final List<zzgj> zzaht = new ArrayList();
    @GuardedBy("mLock")
    private final List<zzgw> zzahu = new ArrayList();
    private Runnable zzahv;
    private long zzahw;
    private boolean zzzv = false;

    zzgh() {
    }

    private final void setActivity(Activity activity) {
        synchronized (this.mLock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    @Nullable
    public final Activity getActivity() {
        return this.mActivity;
    }

    @Nullable
    public final Context getContext() {
        return this.mContext;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.mLock) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
            }
            Iterator it = this.zzahu.iterator();
            while (it.hasNext()) {
                try {
                    if (((zzgw) it.next()).zza(activity)) {
                        it.remove();
                    }
                } catch (Throwable e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzane.zzb("", e);
                }
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.mLock) {
            for (zzgw onActivityPaused : this.zzahu) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Throwable e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzane.zzb("", e);
                }
            }
        }
        this.zzahs = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        Handler handler = zzakk.zzcrm;
        Runnable com_google_android_gms_internal_ads_zzgi = new zzgi(this);
        this.zzahv = com_google_android_gms_internal_ads_zzgi;
        handler.postDelayed(com_google_android_gms_internal_ads_zzgi, this.zzahw);
    }

    public final void onActivityResumed(Activity activity) {
        boolean z = false;
        setActivity(activity);
        this.zzahs = false;
        if (!this.zzahr) {
            z = true;
        }
        this.zzahr = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        synchronized (this.mLock) {
            for (zzgw onActivityResumed : this.zzahu) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Throwable e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzane.zzb("", e);
                }
            }
            if (z) {
                for (zzgj zzh : this.zzaht) {
                    try {
                        zzh.zzh(true);
                    } catch (Throwable e2) {
                        zzane.zzb("", e2);
                    }
                }
            } else {
                zzakb.zzck("App is still foreground.");
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzzv) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.mContext = application;
            this.zzahw = ((Long) zzkb.zzik().zzd(zznk.zzayh)).longValue();
            this.zzzv = true;
        }
    }

    public final void zza(zzgj com_google_android_gms_internal_ads_zzgj) {
        synchronized (this.mLock) {
            this.zzaht.add(com_google_android_gms_internal_ads_zzgj);
        }
    }
}
