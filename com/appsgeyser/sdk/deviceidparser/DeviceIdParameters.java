package com.appsgeyser.sdk.deviceidparser;

import android.support.annotation.Nullable;

public class DeviceIdParameters implements Cloneable {
    private String aId = null;
    private String advId = null;
    private LimitAdTrackingEnabledStates limitAdTrackingEnabledStates = null;

    DeviceIdParameters() {
    }

    public DeviceIdParameters(String hid, String advId, String aId, LimitAdTrackingEnabledStates limitAdTrackingEnabled) {
        this.advId = advId;
        this.aId = aId;
        this.limitAdTrackingEnabledStates = limitAdTrackingEnabled;
    }

    public boolean isEmpty() {
        return this.aId == null && this.advId == null && this.limitAdTrackingEnabledStates == null;
    }

    void clear() {
        this.advId = null;
        this.aId = null;
        this.limitAdTrackingEnabledStates = null;
    }

    @Nullable
    public String getAdvId() {
        return this.advId;
    }

    void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getaId() {
        return this.aId;
    }

    void setaId(String aId) {
        this.aId = aId;
    }

    public LimitAdTrackingEnabledStates getLimitAdTrackingEnabled() {
        return this.limitAdTrackingEnabledStates;
    }

    void setLimitAdTrackingEnabled(LimitAdTrackingEnabledStates limitAdTrackingEnabled) {
        this.limitAdTrackingEnabledStates = limitAdTrackingEnabled;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
