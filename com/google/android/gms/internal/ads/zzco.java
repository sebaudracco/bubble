package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzco implements zzcv {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzrn;

    zzco(zzcn com_google_android_gms_internal_ads_zzcn, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzrn = bundle;
    }

    public final void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzrn);
    }
}
