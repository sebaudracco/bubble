package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class zzcp implements zzcv {
    private final /* synthetic */ Activity val$activity;

    zzcp(zzcn com_google_android_gms_internal_ads_zzcn, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStarted(this.val$activity);
    }
}
