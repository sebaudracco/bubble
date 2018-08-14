package com.google.android.gms.gcm;

import android.os.Bundle;
import android.support.annotation.RequiresPermission;

public class PeriodicTask$Builder extends Task$Builder {
    private long zzam;
    private long zzan;

    public PeriodicTask$Builder() {
        this.zzam = -1;
        this.zzan = -1;
        this.isPersisted = true;
    }

    public PeriodicTask build() {
        checkConditions();
        return new PeriodicTask(this, null);
    }

    protected void checkConditions() {
        super.checkConditions();
        if (this.zzam == -1) {
            throw new IllegalArgumentException("Must call setPeriod(long) to establish an execution interval for this periodic task.");
        } else if (this.zzam <= 0) {
            throw new IllegalArgumentException("Period set cannot be less than or equal to 0: " + this.zzam);
        } else if (this.zzan == -1) {
            this.zzan = (long) (((float) this.zzam) * 0.1f);
        } else if (this.zzan > this.zzam) {
            this.zzan = this.zzam;
        }
    }

    public PeriodicTask$Builder setExtras(Bundle bundle) {
        this.extras = bundle;
        return this;
    }

    public PeriodicTask$Builder setFlex(long j) {
        this.zzan = j;
        return this;
    }

    public PeriodicTask$Builder setPeriod(long j) {
        this.zzam = j;
        return this;
    }

    @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
    public PeriodicTask$Builder setPersisted(boolean z) {
        this.isPersisted = z;
        return this;
    }

    public PeriodicTask$Builder setRequiredNetwork(int i) {
        this.requiredNetworkState = i;
        return this;
    }

    public PeriodicTask$Builder setRequiresCharging(boolean z) {
        this.requiresCharging = z;
        return this;
    }

    public PeriodicTask$Builder setService(Class<? extends GcmTaskService> cls) {
        this.gcmTaskService = cls.getName();
        return this;
    }

    public PeriodicTask$Builder setTag(String str) {
        this.tag = str;
        return this;
    }

    public PeriodicTask$Builder setUpdateCurrent(boolean z) {
        this.updateCurrent = z;
        return this;
    }
}
