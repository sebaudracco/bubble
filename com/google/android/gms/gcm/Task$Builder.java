package com.google.android.gms.gcm;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Set;

public abstract class Task$Builder {
    protected Bundle extras;
    protected String gcmTaskService;
    protected boolean isPersisted;
    protected int requiredNetworkState;
    protected boolean requiresCharging;
    protected String tag;
    protected boolean updateCurrent;
    protected Set<Uri> zzau = Collections.emptySet();
    protected zzl zzaw = zzl.zzao;

    public abstract Task build();

    @CallSuper
    protected void checkConditions() {
        Preconditions.checkArgument(this.gcmTaskService != null, "Must provide an endpoint for this task by calling setService(ComponentName).");
        GcmNetworkManager.zzd(this.tag);
        zzl com_google_android_gms_gcm_zzl = this.zzaw;
        if (com_google_android_gms_gcm_zzl != null) {
            int zzh = com_google_android_gms_gcm_zzl.zzh();
            if (zzh == 1 || zzh == 0) {
                int zzi = com_google_android_gms_gcm_zzl.zzi();
                int zzj = com_google_android_gms_gcm_zzl.zzj();
                if (zzh == 0 && zzi < 0) {
                    throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + zzi);
                } else if (zzh == 1 && zzi < 10) {
                    throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                } else if (zzj < zzi) {
                    throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + com_google_android_gms_gcm_zzl.zzj());
                }
            }
            throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + zzh);
        }
        if (this.isPersisted) {
            Task.zzg(this.extras);
        }
        if (this.zzau.isEmpty() || this.requiredNetworkState != 2) {
            for (Uri zze : this.zzau) {
                Task.zze(zze);
            }
            return;
        }
        throw new IllegalArgumentException("Required URIs may not be used with NETWORK_STATE_ANY");
    }

    public abstract Task$Builder setExtras(Bundle bundle);

    @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
    public abstract Task$Builder setPersisted(boolean z);

    public abstract Task$Builder setRequiredNetwork(int i);

    public abstract Task$Builder setRequiresCharging(boolean z);

    public abstract Task$Builder setService(Class<? extends GcmTaskService> cls);

    public abstract Task$Builder setTag(String str);

    public abstract Task$Builder setUpdateCurrent(boolean z);
}
