package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zza extends ActivityLifecycleObserver {
    private final WeakReference<zza> zzds;

    @VisibleForTesting(otherwise = 2)
    static class zza extends LifecycleCallback {
        private List<Runnable> zzdt = new ArrayList();

        private zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        private static zza zza(Activity activity) {
            zza com_google_android_gms_common_api_internal_zza_zza;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                com_google_android_gms_common_api_internal_zza_zza = (zza) fragment.getCallbackOrNull("LifecycleObserverOnStop", zza.class);
                if (com_google_android_gms_common_api_internal_zza_zza == null) {
                    com_google_android_gms_common_api_internal_zza_zza = new zza(fragment);
                }
            }
            return com_google_android_gms_common_api_internal_zza_zza;
        }

        private final synchronized void zza(Runnable runnable) {
            this.zzdt.add(runnable);
        }

        @MainThread
        public void onStop() {
            synchronized (this) {
                List<Runnable> list = this.zzdt;
                this.zzdt = new ArrayList();
            }
            for (Runnable run : list) {
                run.run();
            }
        }
    }

    public zza(Activity activity) {
        this(zza.zza(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zza(zza com_google_android_gms_common_api_internal_zza_zza) {
        this.zzds = new WeakReference(com_google_android_gms_common_api_internal_zza_zza);
    }

    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        zza com_google_android_gms_common_api_internal_zza_zza = (zza) this.zzds.get();
        if (com_google_android_gms_common_api_internal_zza_zza == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        com_google_android_gms_common_api_internal_zza_zza.zza(runnable);
        return this;
    }
}
