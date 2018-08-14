package com.mopub.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MoPub {
    private static final int DEFAULT_LOCATION_PRECISION = 6;
    private static final long DEFAULT_LOCATION_REFRESH_TIME_MILLIS = 60000;
    private static final String MOPUB_REWARDED_VIDEOS = "com.mopub.mobileads.MoPubRewardedVideos";
    private static final String MOPUB_REWARDED_VIDEO_LISTENER = "com.mopub.mobileads.MoPubRewardedVideoListener";
    private static final String MOPUB_REWARDED_VIDEO_MANAGER = "com.mopub.mobileads.MoPubRewardedVideoManager";
    private static final String MOPUB_REWARDED_VIDEO_MANAGER_REQUEST_PARAMETERS = "com.mopub.mobileads.MoPubRewardedVideoManager$RequestParameters";
    public static final String SDK_VERSION = "4.20.0";
    @NonNull
    private static volatile BrowserAgent sBrowserAgent = BrowserAgent.IN_APP;
    private static volatile boolean sIsBrowserAgentOverriddenByClient = false;
    @NonNull
    private static volatile LocationAwareness sLocationAwareness = LocationAwareness.NORMAL;
    private static volatile int sLocationPrecision = 6;
    private static volatile long sMinimumLocationRefreshTimeMillis = 60000;
    private static boolean sSearchedForUpdateActivityMethod = false;
    @Nullable
    private static Method sUpdateActivityMethod;

    @NonNull
    public static LocationAwareness getLocationAwareness() {
        Preconditions.checkNotNull(sLocationAwareness);
        return sLocationAwareness;
    }

    public static void setLocationAwareness(@NonNull LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(locationAwareness);
        sLocationAwareness = locationAwareness;
    }

    public static int getLocationPrecision() {
        return sLocationPrecision;
    }

    public static void setLocationPrecision(int precision) {
        sLocationPrecision = Math.min(Math.max(0, precision), 6);
    }

    public static void setMinimumLocationRefreshTimeMillis(long minimumLocationRefreshTimeMillis) {
        sMinimumLocationRefreshTimeMillis = minimumLocationRefreshTimeMillis;
    }

    public static long getMinimumLocationRefreshTimeMillis() {
        return sMinimumLocationRefreshTimeMillis;
    }

    public static void setBrowserAgent(@NonNull BrowserAgent browserAgent) {
        Preconditions.checkNotNull(browserAgent);
        sBrowserAgent = browserAgent;
        sIsBrowserAgentOverriddenByClient = true;
    }

    public static void setBrowserAgentFromAdServer(@NonNull BrowserAgent adServerBrowserAgent) {
        Preconditions.checkNotNull(adServerBrowserAgent);
        if (sIsBrowserAgentOverriddenByClient) {
            MoPubLog.w("Browser agent already overridden by client with value " + sBrowserAgent);
        } else {
            sBrowserAgent = adServerBrowserAgent;
        }
    }

    @NonNull
    public static BrowserAgent getBrowserAgent() {
        Preconditions.checkNotNull(sBrowserAgent);
        return sBrowserAgent;
    }

    @VisibleForTesting
    static boolean isBrowserAgentOverriddenByClient() {
        return sIsBrowserAgentOverriddenByClient;
    }

    @Deprecated
    @VisibleForTesting
    public static void resetBrowserAgent() {
        sBrowserAgent = BrowserAgent.IN_APP;
        sIsBrowserAgentOverriddenByClient = false;
    }

    public static void onCreate(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onCreate(activity);
        updateActivity(activity);
    }

    public static void onStart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStart(activity);
        updateActivity(activity);
    }

    public static void onPause(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onPause(activity);
    }

    public static void onResume(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onResume(activity);
        updateActivity(activity);
    }

    public static void onRestart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onRestart(activity);
        updateActivity(activity);
    }

    public static void onStop(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStop(activity);
    }

    public static void onDestroy(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onDestroy(activity);
    }

    public static void onBackPressed(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onBackPressed(activity);
    }

    public static void disableViewability(@NonNull ViewabilityVendor vendor) {
        Preconditions.checkNotNull(vendor);
        vendor.disable();
    }

    @Deprecated
    public static void initializeRewardedVideo(@NonNull Activity activity, MediationSettings... mediationSettings) {
        try {
            new MethodBuilder(null, "initializeRewardedVideo").setStatic(Class.forName(MOPUB_REWARDED_VIDEOS)).addParam(Activity.class, activity).addParam(MediationSettings[].class, mediationSettings).execute();
        } catch (ClassNotFoundException e) {
            MoPubLog.w("initializeRewardedVideo was called without the rewarded video module");
        } catch (NoSuchMethodException e2) {
            MoPubLog.w("initializeRewardedVideo was called without the rewarded video module");
        } catch (Exception e3) {
            MoPubLog.e("Error while initializing rewarded video", e3);
        }
    }

    @VisibleForTesting
    static void updateActivity(@NonNull Activity activity) {
        if (!sSearchedForUpdateActivityMethod) {
            sSearchedForUpdateActivityMethod = true;
            try {
                sUpdateActivityMethod = Reflection.getDeclaredMethodWithTraversal(Class.forName(MOPUB_REWARDED_VIDEO_MANAGER), "updateActivity", new Class[]{Activity.class});
            } catch (ClassNotFoundException e) {
            } catch (NoSuchMethodException e2) {
            }
        }
        if (sUpdateActivityMethod != null) {
            try {
                sUpdateActivityMethod.invoke(null, new Object[]{activity});
            } catch (IllegalAccessException e3) {
                MoPubLog.e("Error while attempting to access the update activity method - this should not have happened", e3);
            } catch (InvocationTargetException e4) {
                MoPubLog.e("Error while attempting to access the update activity method - this should not have happened", e4);
            }
        }
    }

    @Deprecated
    public static void setRewardedVideoListener(@Nullable Object listener) {
        try {
            new MethodBuilder(null, "setRewardedVideoListener").setStatic(Class.forName(MOPUB_REWARDED_VIDEOS)).addParam(Class.forName(MOPUB_REWARDED_VIDEO_LISTENER), listener).execute();
        } catch (ClassNotFoundException e) {
            MoPubLog.w("setRewardedVideoListener was called without the rewarded video module");
        } catch (NoSuchMethodException e2) {
            MoPubLog.w("setRewardedVideoListener was called without the rewarded video module");
        } catch (Exception e3) {
            MoPubLog.e("Error while setting rewarded video listener", e3);
        }
    }

    @Deprecated
    public static void loadRewardedVideo(@NonNull String adUnitId, @Nullable MediationSettings... mediationSettings) {
        loadRewardedVideo(adUnitId, null, mediationSettings);
    }

    @Deprecated
    public static void loadRewardedVideo(@NonNull String adUnitId, @Nullable Object requestParameters, @Nullable MediationSettings... mediationSettings) {
        try {
            new MethodBuilder(null, "loadRewardedVideo").setStatic(Class.forName(MOPUB_REWARDED_VIDEOS)).addParam(String.class, adUnitId).addParam(Class.forName(MOPUB_REWARDED_VIDEO_MANAGER_REQUEST_PARAMETERS), requestParameters).addParam(MediationSettings[].class, mediationSettings).execute();
        } catch (ClassNotFoundException e) {
            MoPubLog.w("loadRewardedVideo was called without the rewarded video module");
        } catch (NoSuchMethodException e2) {
            MoPubLog.w("loadRewardedVideo was called without the rewarded video module");
        } catch (Exception e3) {
            MoPubLog.e("Error while loading rewarded video", e3);
        }
    }

    @Deprecated
    public static boolean hasRewardedVideo(@NonNull String adUnitId) {
        try {
            return ((Boolean) new MethodBuilder(null, "hasRewardedVideo").setStatic(Class.forName(MOPUB_REWARDED_VIDEOS)).addParam(String.class, adUnitId).execute()).booleanValue();
        } catch (ClassNotFoundException e) {
            MoPubLog.w("hasRewardedVideo was called without the rewarded video module");
            return false;
        } catch (NoSuchMethodException e2) {
            MoPubLog.w("hasRewardedVideo was called without the rewarded video module");
            return false;
        } catch (Exception e3) {
            MoPubLog.e("Error while checking rewarded video", e3);
            return false;
        }
    }

    @Deprecated
    public static void showRewardedVideo(@NonNull String adUnitId) {
        try {
            new MethodBuilder(null, "showRewardedVideo").setStatic(Class.forName(MOPUB_REWARDED_VIDEOS)).addParam(String.class, adUnitId).execute();
        } catch (ClassNotFoundException e) {
            MoPubLog.w("showRewardedVideo was called without the rewarded video module");
        } catch (NoSuchMethodException e2) {
            MoPubLog.w("showRewardedVideo was called without the rewarded video module");
        } catch (Exception e3) {
            MoPubLog.e("Error while showing rewarded video", e3);
        }
    }
}
