package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzga implements zzgc {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzrn;

    zzga(zzfu com_google_android_gms_internal_ads_zzfu, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzrn = bundle;
    }

    public final void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivitySaveInstanceState(this.val$activity, this.zzrn);
    }
}
