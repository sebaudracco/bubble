package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzbt extends zzk {
    private TaskCompletionSource<Void> zzln = new TaskCompletionSource();

    private zzbt(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    public static zzbt zzd(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zzbt com_google_android_gms_common_api_internal_zzbt = (zzbt) fragment.getCallbackOrNull("GmsAvailabilityHelper", zzbt.class);
        if (com_google_android_gms_common_api_internal_zzbt == null) {
            return new zzbt(fragment);
        }
        if (!com_google_android_gms_common_api_internal_zzbt.zzln.getTask().isComplete()) {
            return com_google_android_gms_common_api_internal_zzbt;
        }
        com_google_android_gms_common_api_internal_zzbt.zzln = new TaskCompletionSource();
        return com_google_android_gms_common_api_internal_zzbt;
    }

    public final Task<Void> getTask() {
        return this.zzln.getTask();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzln.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzln.setException(ApiExceptionUtil.fromConnectionResult(connectionResult));
    }

    protected final void zzr() {
        int isGooglePlayServicesAvailable = this.zzdg.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        if (isGooglePlayServicesAvailable == 0) {
            this.zzln.setResult(null);
        } else if (!this.zzln.getTask().isComplete()) {
            zzb(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }
}
