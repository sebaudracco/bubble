package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

public class zzad extends zzk {
    private GoogleApiManager zzcq;
    private final ArraySet<zzh<?>> zzhb = new ArraySet();

    private zzad(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public static void zza(Activity activity, GoogleApiManager googleApiManager, zzh<?> com_google_android_gms_common_api_internal_zzh_) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zzad com_google_android_gms_common_api_internal_zzad = (zzad) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zzad.class);
        if (com_google_android_gms_common_api_internal_zzad == null) {
            com_google_android_gms_common_api_internal_zzad = new zzad(fragment);
        }
        com_google_android_gms_common_api_internal_zzad.zzcq = googleApiManager;
        Preconditions.checkNotNull(com_google_android_gms_common_api_internal_zzh_, "ApiKey cannot be null");
        com_google_android_gms_common_api_internal_zzad.zzhb.add(com_google_android_gms_common_api_internal_zzh_);
        googleApiManager.zza(com_google_android_gms_common_api_internal_zzad);
    }

    private final void zzan() {
        if (!this.zzhb.isEmpty()) {
            this.zzcq.zza(this);
        }
    }

    public final void onResume() {
        super.onResume();
        zzan();
    }

    public final void onStart() {
        super.onStart();
        zzan();
    }

    public void onStop() {
        super.onStop();
        this.zzcq.zzb(this);
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzcq.zza(connectionResult, i);
    }

    final ArraySet<zzh<?>> zzam() {
        return this.zzhb;
    }

    protected final void zzr() {
        this.zzcq.zzr();
    }
}
