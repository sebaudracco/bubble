package com.google.android.gms.gcm;

import android.os.Bundle;
import android.support.annotation.RequiresPermission;

public class OneoffTask$Builder extends Task$Builder {
    private long zzaj;
    private long zzak;

    public OneoffTask$Builder() {
        this.zzaj = -1;
        this.zzak = -1;
        this.isPersisted = false;
    }

    public OneoffTask build() {
        checkConditions();
        return new OneoffTask(this, null);
    }

    protected void checkConditions() {
        super.checkConditions();
        if (this.zzaj == -1 || this.zzak == -1) {
            throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
        } else if (this.zzaj >= this.zzak) {
            throw new IllegalArgumentException("Window start must be shorter than window end.");
        }
    }

    public OneoffTask$Builder setExecutionWindow(long j, long j2) {
        this.zzaj = j;
        this.zzak = j2;
        return this;
    }

    public OneoffTask$Builder setExtras(Bundle bundle) {
        this.extras = bundle;
        return this;
    }

    @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
    public OneoffTask$Builder setPersisted(boolean z) {
        this.isPersisted = z;
        return this;
    }

    public OneoffTask$Builder setRequiredNetwork(int i) {
        this.requiredNetworkState = i;
        return this;
    }

    public OneoffTask$Builder setRequiresCharging(boolean z) {
        this.requiresCharging = z;
        return this;
    }

    public OneoffTask$Builder setService(Class<? extends GcmTaskService> cls) {
        this.gcmTaskService = cls.getName();
        return this;
    }

    public OneoffTask$Builder setTag(String str) {
        this.tag = str;
        return this;
    }

    public OneoffTask$Builder setUpdateCurrent(boolean z) {
        this.updateCurrent = z;
        return this;
    }
}
