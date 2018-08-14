package com.google.android.gms.measurement;

import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzka;

@KeepForSdk
public class AppMeasurement$ConditionalUserProperty {
    @Keep
    @KeepForSdk
    public boolean mActive;
    @Keep
    @KeepForSdk
    public String mAppId;
    @Keep
    @KeepForSdk
    public long mCreationTimestamp;
    @Keep
    public String mExpiredEventName;
    @Keep
    public Bundle mExpiredEventParams;
    @Keep
    @KeepForSdk
    public String mName;
    @Keep
    @KeepForSdk
    public String mOrigin;
    @Keep
    @KeepForSdk
    public long mTimeToLive;
    @Keep
    public String mTimedOutEventName;
    @Keep
    public Bundle mTimedOutEventParams;
    @Keep
    @KeepForSdk
    public String mTriggerEventName;
    @Keep
    @KeepForSdk
    public long mTriggerTimeout;
    @Keep
    public String mTriggeredEventName;
    @Keep
    public Bundle mTriggeredEventParams;
    @Keep
    @KeepForSdk
    public long mTriggeredTimestamp;
    @Keep
    @KeepForSdk
    public Object mValue;

    public AppMeasurement$ConditionalUserProperty(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        Preconditions.checkNotNull(appMeasurement$ConditionalUserProperty);
        this.mAppId = appMeasurement$ConditionalUserProperty.mAppId;
        this.mOrigin = appMeasurement$ConditionalUserProperty.mOrigin;
        this.mCreationTimestamp = appMeasurement$ConditionalUserProperty.mCreationTimestamp;
        this.mName = appMeasurement$ConditionalUserProperty.mName;
        if (appMeasurement$ConditionalUserProperty.mValue != null) {
            this.mValue = zzka.zzf(appMeasurement$ConditionalUserProperty.mValue);
            if (this.mValue == null) {
                this.mValue = appMeasurement$ConditionalUserProperty.mValue;
            }
        }
        this.mActive = appMeasurement$ConditionalUserProperty.mActive;
        this.mTriggerEventName = appMeasurement$ConditionalUserProperty.mTriggerEventName;
        this.mTriggerTimeout = appMeasurement$ConditionalUserProperty.mTriggerTimeout;
        this.mTimedOutEventName = appMeasurement$ConditionalUserProperty.mTimedOutEventName;
        if (appMeasurement$ConditionalUserProperty.mTimedOutEventParams != null) {
            this.mTimedOutEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTimedOutEventParams);
        }
        this.mTriggeredEventName = appMeasurement$ConditionalUserProperty.mTriggeredEventName;
        if (appMeasurement$ConditionalUserProperty.mTriggeredEventParams != null) {
            this.mTriggeredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTriggeredEventParams);
        }
        this.mTriggeredTimestamp = appMeasurement$ConditionalUserProperty.mTriggeredTimestamp;
        this.mTimeToLive = appMeasurement$ConditionalUserProperty.mTimeToLive;
        this.mExpiredEventName = appMeasurement$ConditionalUserProperty.mExpiredEventName;
        if (appMeasurement$ConditionalUserProperty.mExpiredEventParams != null) {
            this.mExpiredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mExpiredEventParams);
        }
    }
}
