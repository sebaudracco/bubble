package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class zzcn implements ActivityLifecycleCallbacks {
    private final Application zzrk;
    private final WeakReference<ActivityLifecycleCallbacks> zzrl;
    private boolean zzrm = false;

    public zzcn(Application application, ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzrl = new WeakReference(activityLifecycleCallbacks);
        this.zzrk = application;
    }

    private final void zza(zzcv com_google_android_gms_internal_ads_zzcv) {
        try {
            ActivityLifecycleCallbacks activityLifecycleCallbacks = (ActivityLifecycleCallbacks) this.zzrl.get();
            if (activityLifecycleCallbacks != null) {
                com_google_android_gms_internal_ads_zzcv.zza(activityLifecycleCallbacks);
            } else if (!this.zzrm) {
                this.zzrk.unregisterActivityLifecycleCallbacks(this);
                this.zzrm = true;
            }
        } catch (Exception e) {
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzco(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zzcu(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzcr(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzcq(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzct(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzcp(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zzcs(this, activity));
    }
}
