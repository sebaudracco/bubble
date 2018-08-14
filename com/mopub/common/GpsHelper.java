package com.mopub.common;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.factories.MethodBuilderFactory;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Reflection;

public class GpsHelper {
    public static final String ADVERTISING_ID_KEY = "advertisingId";
    public static final int GOOGLE_PLAY_SUCCESS_CODE = 0;
    public static final String IS_LIMIT_AD_TRACKING_ENABLED_KEY = "isLimitAdTrackingEnabled";
    private static String sAdvertisingIdClientClassName = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
    private static String sPlayServicesUtilClassName = "com.google.android.gms.common.GooglePlayServicesUtil";

    public static boolean isPlayServicesAvailable(Context context) {
        try {
            Object result = MethodBuilderFactory.create(null, "isGooglePlayServicesAvailable").setStatic(Class.forName(sPlayServicesUtilClassName)).addParam(Context.class, context).execute();
            if (result == null || ((Integer) result).intValue() != 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLimitAdTrackingEnabled(Context context) {
        if (isPlayServicesAvailable(context)) {
            return SharedPreferencesHelper.getSharedPreferences(context).getBoolean(IS_LIMIT_AD_TRACKING_ENABLED_KEY, false);
        }
        return false;
    }

    static boolean isClientMetadataPopulated(Context context) {
        return ClientMetadata.getInstance(context).isAdvertisingInfoSet();
    }

    public static void fetchAdvertisingInfoAsync(Context context, GpsHelperListener gpsHelperListener) {
        boolean playServicesIsAvailable = isPlayServicesAvailable(context);
        if (!playServicesIsAvailable || isClientMetadataPopulated(context)) {
            if (gpsHelperListener != null) {
                gpsHelperListener.onFetchAdInfoCompleted();
            }
            if (playServicesIsAvailable) {
                internalFetchAdvertisingInfoAsync(context, null);
                return;
            }
            return;
        }
        internalFetchAdvertisingInfoAsync(context, gpsHelperListener);
    }

    @Nullable
    public static AdvertisingInfo fetchAdvertisingInfoSync(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Object adInfo = MethodBuilderFactory.create(null, "getAdvertisingIdInfo").setStatic(Class.forName(sAdvertisingIdClientClassName)).addParam(Context.class, context).execute();
            return new AdvertisingInfo(reflectedGetAdvertisingId(adInfo, null), reflectedIsLimitAdTrackingEnabled(adInfo, false));
        } catch (Exception e) {
            MoPubLog.d("Unable to obtain Google AdvertisingIdClient.Info via reflection.");
            return null;
        }
    }

    private static void internalFetchAdvertisingInfoAsync(Context context, GpsHelperListener gpsHelperListener) {
        if (Reflection.classFound(sAdvertisingIdClientClassName)) {
            try {
                AsyncTasks.safeExecuteOnExecutor(new FetchAdvertisingInfoTask(context, gpsHelperListener), new Void[0]);
            } catch (Exception exception) {
                MoPubLog.d("Error executing FetchAdvertisingInfoTask", exception);
                if (gpsHelperListener != null) {
                    gpsHelperListener.onFetchAdInfoCompleted();
                }
            }
        } else if (gpsHelperListener != null) {
            gpsHelperListener.onFetchAdInfoCompleted();
        }
    }

    static void updateClientMetadata(Context context, Object adInfo) {
        ClientMetadata.getInstance(context).setAdvertisingInfo(reflectedGetAdvertisingId(adInfo, null), reflectedIsLimitAdTrackingEnabled(adInfo, false));
    }

    static String reflectedGetAdvertisingId(Object adInfo, String defaultValue) {
        try {
            return (String) MethodBuilderFactory.create(adInfo, "getId").execute();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    static boolean reflectedIsLimitAdTrackingEnabled(Object adInfo, boolean defaultValue) {
        try {
            Boolean result = (Boolean) MethodBuilderFactory.create(adInfo, IS_LIMIT_AD_TRACKING_ENABLED_KEY).execute();
            if (result != null) {
                defaultValue = result.booleanValue();
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Deprecated
    public static void setClassNamesForTesting() {
        String className = "java.lang.Class";
        sPlayServicesUtilClassName = className;
        sAdvertisingIdClientClassName = className;
    }
}
