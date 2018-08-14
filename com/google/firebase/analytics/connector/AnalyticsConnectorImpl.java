package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import com.google.firebase.analytics.connector.AnalyticsConnector.ConditionalUserProperty;
import com.google.firebase.analytics.connector.internal.Adapter;
import com.google.firebase.analytics.connector.internal.zzb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzbof;
    private final AppMeasurement zzboe;
    @VisibleForTesting
    final Map<String, Adapter> zzbog = new ConcurrentHashMap();

    private AnalyticsConnectorImpl(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.zzboe = appMeasurement;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AnalyticsConnector getInstance(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzbof == null) {
            synchronized (AnalyticsConnector.class) {
                if (zzbof == null) {
                    zzbof = new AnalyticsConnectorImpl(AppMeasurement.getInstance(context));
                }
            }
        }
        return zzbof;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    private final boolean zzfc(@NonNull String str) {
        return (str.isEmpty() || !this.zzbog.containsKey(str) || this.zzbog.get(str) == null) ? false : true;
    }

    @KeepForSdk
    public void clearConditionalUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        if (str2 == null || zzb.zza(str2, bundle)) {
            this.zzboe.clearConditionalUserProperty(str, str2, bundle);
        } else {
            Log.d("FA-C", "Event or Params not allowed");
        }
    }

    @VisibleForTesting
    protected Adapter createAdapter(@NonNull String str, AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 3308:
                if (str.equals("gs")) {
                    obj = 3;
                    break;
                }
                break;
            case 101200:
                if (str.equals(AppMeasurement.FCM_ORIGIN)) {
                    obj = null;
                    break;
                }
                break;
            case 101230:
                if (str.equals("fdl")) {
                    obj = 1;
                    break;
                }
                break;
            case 101655:
                if (str.equals("frc")) {
                    obj = 2;
                    break;
                }
                break;
            case 94921639:
                if (str.equals(AppMeasurement.CRASH_ORIGIN)) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                Log.d("FA-C", "FCM Adapter not implemented");
                break;
            case 1:
                Log.d("FA-C", "FDL Adapter not implemented");
                break;
            case 2:
                Log.d("FA-C", "FRC Adapter not implemented");
                break;
            case 3:
                Log.d("FA-C", "Search Adapter not implemented");
                break;
            case 4:
                Log.d("FA-C", "Crash Adapter not implemented");
                break;
            default:
                String str2 = "FA-C";
                String str3 = "Adapter not defined for ";
                String valueOf = String.valueOf(str);
                Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                break;
        }
        return null;
    }

    @WorkerThread
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(@NonNull String str, @Nullable @Size(max = 23, min = 1) String str2) {
        List<ConditionalUserProperty> arrayList = new ArrayList();
        for (AppMeasurement$ConditionalUserProperty zzd : this.zzboe.getConditionalUserProperties(str, str2)) {
            arrayList.add(zzb.zzd(zzd));
        }
        return arrayList;
    }

    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size(min = 1) @NonNull String str) {
        return this.zzboe.getMaxUserProperties(str);
    }

    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzboe.getUserProperties(z);
    }

    @WorkerThread
    @KeepForSdk
    public void logEvent(@NonNull String str, @NonNull String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!zzb.zzfd(str)) {
            String str3 = "FA-C";
            String str4 = "Origin not allowed : ";
            String valueOf = String.valueOf(str);
            Log.d(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
        } else if (!zzb.zza(str2, bundle)) {
            Log.d("FA-C", "Event or Params not allowed");
        } else if (zzb.zzb(str, str2, bundle)) {
            this.zzboe.logEventInternal(str, str2, bundle);
        } else {
            Log.d("FA-C", "Campaign events not allowed");
        }
    }

    @WorkerThread
    @KeepForSdk
    public AnalyticsConnectorHandle registerAnalyticsConnectorListener(@NonNull String str, AnalyticsConnectorListener analyticsConnectorListener) {
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!zzb.zzfd(str)) {
            Log.d("FA-C", "Registering with non allowed origin");
            return null;
        } else if (zzfc(str)) {
            Log.d("FA-C", "Registering duplicate listener");
            return null;
        } else {
            Adapter createAdapter = createAdapter(str, this.zzboe, analyticsConnectorListener);
            if (createAdapter == null) {
                return null;
            }
            this.zzbog.put(str, createAdapter);
            return new zza(this, str);
        }
    }

    @KeepForSdk
    public void setConditionalUserProperty(@NonNull ConditionalUserProperty conditionalUserProperty) {
        if (zzb.zza(conditionalUserProperty)) {
            this.zzboe.setConditionalUserProperty(zzb.zzb(conditionalUserProperty));
        } else {
            Log.d("FA-C", "Conditional user property has invalid event or params");
        }
    }

    @KeepForSdk
    public void setUserProperty(@NonNull String str, @NonNull String str2, Object obj) {
        String str3;
        String str4;
        String valueOf;
        if (!zzb.zzfd(str)) {
            str3 = "FA-C";
            str4 = "Origin not allowed : ";
            valueOf = String.valueOf(str);
            Log.d(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
        } else if (!zzb.zzfe(str2)) {
            str3 = "FA-C";
            str4 = "User Property not allowed : ";
            valueOf = String.valueOf(str2);
            Log.d(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
        } else if ((!str2.equals("_ce1") && !str2.equals("_ce2")) || str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc")) {
            this.zzboe.setUserPropertyInternal(str, str2, obj);
        } else {
            str3 = "FA-C";
            str4 = "User Property not allowed for this origin: ";
            valueOf = String.valueOf(str2);
            Log.d(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
        }
    }
}
