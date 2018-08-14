package com.google.android.gms.measurement;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.measurement.zzka;
import com.google.firebase.analytics.FirebaseAnalytics.UserProperty;

@KeepForSdk
public final class AppMeasurement$UserProperty extends UserProperty {
    @KeepForSdk
    public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
    public static final String[] zzadb = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement"};
    public static final String[] zzadc = new String[]{FIREBASE_LAST_NOTIFICATION, "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte"};

    private AppMeasurement$UserProperty() {
    }

    public static String zzak(String str) {
        return zzka.zza(str, zzadb, zzadc);
    }
}
