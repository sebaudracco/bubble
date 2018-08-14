package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.internal.measurement.zzka;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.connector.AnalyticsConnector.ConditionalUserProperty;
import java.util.Arrays;
import java.util.List;

public final class zzb {
    private static final List<String> zzboj = Arrays.asList(new String[]{"_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", Event.APP_OPEN});
    private static final List<String> zzbok = Arrays.asList(new String[]{"auto", "app", "am"});
    private static final List<String> zzbol = Arrays.asList(new String[]{"_r", "_dbg"});
    private static final List<String> zzbom = Arrays.asList((String[]) ArrayUtils.concat(AppMeasurement$UserProperty.zzadb, AppMeasurement$UserProperty.zzadc));
    private static final List<String> zzbon = Arrays.asList(new String[]{"^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$"});

    public static boolean zza(ConditionalUserProperty conditionalUserProperty) {
        if (conditionalUserProperty == null) {
            return false;
        }
        String str = conditionalUserProperty.origin;
        return (str == null || str.isEmpty()) ? false : ((conditionalUserProperty.value == null || zzka.zzf(conditionalUserProperty.value) != null) && zzfd(str) && zzfe(conditionalUserProperty.name)) ? ((!conditionalUserProperty.name.equals("_ce1") && !conditionalUserProperty.name.equals("_ce2")) || str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc")) ? (conditionalUserProperty.expiredEventName == null || (zza(conditionalUserProperty.expiredEventName, conditionalUserProperty.expiredEventParams) && zzb(str, conditionalUserProperty.expiredEventName, conditionalUserProperty.expiredEventParams))) ? (conditionalUserProperty.triggeredEventName == null || (zza(conditionalUserProperty.triggeredEventName, conditionalUserProperty.triggeredEventParams) && zzb(str, conditionalUserProperty.triggeredEventName, conditionalUserProperty.triggeredEventParams))) ? conditionalUserProperty.timedOutEventName == null || (zza(conditionalUserProperty.timedOutEventName, conditionalUserProperty.timedOutEventParams) && zzb(str, conditionalUserProperty.timedOutEventName, conditionalUserProperty.timedOutEventParams)) : false : false : false : false;
    }

    public static boolean zza(@NonNull String str, @Nullable Bundle bundle) {
        if (zzboj.contains(str)) {
            return false;
        }
        if (bundle != null) {
            for (String containsKey : zzbol) {
                if (bundle.containsKey(containsKey)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static AppMeasurement$ConditionalUserProperty zzb(ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty = new AppMeasurement$ConditionalUserProperty();
        appMeasurement$ConditionalUserProperty.mOrigin = conditionalUserProperty.origin;
        appMeasurement$ConditionalUserProperty.mActive = conditionalUserProperty.active;
        appMeasurement$ConditionalUserProperty.mCreationTimestamp = conditionalUserProperty.creationTimestamp;
        appMeasurement$ConditionalUserProperty.mExpiredEventName = conditionalUserProperty.expiredEventName;
        if (conditionalUserProperty.expiredEventParams != null) {
            appMeasurement$ConditionalUserProperty.mExpiredEventParams = new Bundle(conditionalUserProperty.expiredEventParams);
        }
        appMeasurement$ConditionalUserProperty.mName = conditionalUserProperty.name;
        appMeasurement$ConditionalUserProperty.mTimedOutEventName = conditionalUserProperty.timedOutEventName;
        if (conditionalUserProperty.timedOutEventParams != null) {
            appMeasurement$ConditionalUserProperty.mTimedOutEventParams = new Bundle(conditionalUserProperty.timedOutEventParams);
        }
        appMeasurement$ConditionalUserProperty.mTimeToLive = conditionalUserProperty.timeToLive;
        appMeasurement$ConditionalUserProperty.mTriggeredEventName = conditionalUserProperty.triggeredEventName;
        if (conditionalUserProperty.triggeredEventParams != null) {
            appMeasurement$ConditionalUserProperty.mTriggeredEventParams = new Bundle(conditionalUserProperty.triggeredEventParams);
        }
        appMeasurement$ConditionalUserProperty.mTriggeredTimestamp = conditionalUserProperty.triggeredTimestamp;
        appMeasurement$ConditionalUserProperty.mTriggerEventName = conditionalUserProperty.triggerEventName;
        appMeasurement$ConditionalUserProperty.mTriggerTimeout = conditionalUserProperty.triggerTimeout;
        if (conditionalUserProperty.value != null) {
            appMeasurement$ConditionalUserProperty.mValue = zzka.zzf(conditionalUserProperty.value);
        }
        return appMeasurement$ConditionalUserProperty;
    }

    public static boolean zzb(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        if (!"_cmp".equals(str2)) {
            return true;
        }
        if (!zzfd(str)) {
            return false;
        }
        if (bundle == null) {
            return false;
        }
        for (String containsKey : zzbol) {
            if (bundle.containsKey(containsKey)) {
                return false;
            }
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case 101200:
                if (str.equals(AppMeasurement.FCM_ORIGIN)) {
                    obj = null;
                    break;
                }
                break;
            case 101230:
                if (str.equals("fdl")) {
                    int i = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                bundle.putString("_cis", "fcm_integration");
                return true;
            case 1:
                bundle.putString("_cis", "fdl_integration");
                return true;
            default:
                return false;
        }
    }

    public static ConditionalUserProperty zzd(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.origin = appMeasurement$ConditionalUserProperty.mOrigin;
        conditionalUserProperty.active = appMeasurement$ConditionalUserProperty.mActive;
        conditionalUserProperty.creationTimestamp = appMeasurement$ConditionalUserProperty.mCreationTimestamp;
        conditionalUserProperty.expiredEventName = appMeasurement$ConditionalUserProperty.mExpiredEventName;
        if (appMeasurement$ConditionalUserProperty.mExpiredEventParams != null) {
            conditionalUserProperty.expiredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mExpiredEventParams);
        }
        conditionalUserProperty.name = appMeasurement$ConditionalUserProperty.mName;
        conditionalUserProperty.timedOutEventName = appMeasurement$ConditionalUserProperty.mTimedOutEventName;
        if (appMeasurement$ConditionalUserProperty.mTimedOutEventParams != null) {
            conditionalUserProperty.timedOutEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTimedOutEventParams);
        }
        conditionalUserProperty.timeToLive = appMeasurement$ConditionalUserProperty.mTimeToLive;
        conditionalUserProperty.triggeredEventName = appMeasurement$ConditionalUserProperty.mTriggeredEventName;
        if (appMeasurement$ConditionalUserProperty.mTriggeredEventParams != null) {
            conditionalUserProperty.triggeredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTriggeredEventParams);
        }
        conditionalUserProperty.triggeredTimestamp = appMeasurement$ConditionalUserProperty.mTriggeredTimestamp;
        conditionalUserProperty.triggerEventName = appMeasurement$ConditionalUserProperty.mTriggerEventName;
        conditionalUserProperty.triggerTimeout = appMeasurement$ConditionalUserProperty.mTriggerTimeout;
        if (appMeasurement$ConditionalUserProperty.mValue != null) {
            conditionalUserProperty.value = zzka.zzf(appMeasurement$ConditionalUserProperty.mValue);
        }
        return conditionalUserProperty;
    }

    public static boolean zzfd(@NonNull String str) {
        return !zzbok.contains(str);
    }

    public static boolean zzfe(@NonNull String str) {
        if (zzbom.contains(str)) {
            return false;
        }
        for (String matches : zzbon) {
            if (str.matches(matches)) {
                return false;
            }
        }
        return true;
    }
}
