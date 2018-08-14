package com.mopub.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;

public class ManifestUtils {
    private static final String MOPUB_ACTIVITY = "com.mopub.mobileads.MoPubActivity";
    private static final String MRAID_ACTIVITY = "com.mopub.mobileads.MraidActivity";
    private static final List<Class<? extends Activity>> REQUIRED_NATIVE_SDK_ACTIVITIES = new ArrayList(1);
    private static final List<Class<? extends Activity>> REQUIRED_WEB_VIEW_SDK_ACTIVITIES = new ArrayList(4);
    private static final String REWARDED_MRAID_ACTIVITY = "com.mopub.mobileads.RewardedMraidActivity";
    private static FlagCheckUtil sFlagCheckUtil = new FlagCheckUtil();

    private ManifestUtils() {
    }

    static {
        try {
            Class moPubActivityClass = Class.forName(MOPUB_ACTIVITY);
            Class mraidActivityClass = Class.forName(MRAID_ACTIVITY);
            Class rewardedMraidActivityClass = Class.forName(REWARDED_MRAID_ACTIVITY);
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(moPubActivityClass);
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(mraidActivityClass);
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(rewardedMraidActivityClass);
        } catch (ClassNotFoundException e) {
            MoPubLog.i("ManifestUtils running without interstitial module");
        }
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MraidVideoPlayerActivity.class);
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MoPubBrowser.class);
        REQUIRED_NATIVE_SDK_ACTIVITIES.add(MoPubBrowser.class);
    }

    public static void checkWebViewActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
        }
    }

    public static void checkNativeActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
        }
    }

    @VisibleForTesting
    static void displayWarningForMissingActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> requiredActivities) {
        List<Class<? extends Activity>> undeclaredActivities = filterDeclaredActivities(context, requiredActivities, false);
        if (!undeclaredActivities.isEmpty()) {
            logWarningToast(context);
            logMissingActivities(undeclaredActivities);
        }
    }

    @VisibleForTesting
    static void displayWarningForMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> requiredActivities) {
        List<Class<? extends Activity>> misconfiguredActivities = getMisconfiguredActivities(context, filterDeclaredActivities(context, requiredActivities, true));
        if (!misconfiguredActivities.isEmpty()) {
            logWarningToast(context);
            logMisconfiguredActivities(context, misconfiguredActivities);
        }
    }

    public static boolean isDebuggable(@NonNull Context context) {
        return Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, 2);
    }

    private static List<Class<? extends Activity>> filterDeclaredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> requiredActivities, boolean isDeclared) {
        List<Class<? extends Activity>> activities = new ArrayList();
        for (Class<? extends Activity> activityClass : requiredActivities) {
            if (Intents.deviceCanHandleIntent(context, new Intent(context, activityClass)) == isDeclared) {
                activities.add(activityClass);
            }
        }
        return activities;
    }

    @TargetApi(13)
    private static List<Class<? extends Activity>> getMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> activities) {
        List<Class<? extends Activity>> misconfiguredActivities = new ArrayList();
        for (Class<? extends Activity> activity : activities) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, activity);
                if (!activityConfigChanges.hasKeyboardHidden || !activityConfigChanges.hasOrientation || !activityConfigChanges.hasScreenSize) {
                    misconfiguredActivities.add(activity);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return misconfiguredActivities;
    }

    private static void logMissingActivities(@NonNull List<Class<? extends Activity>> undeclaredActivities) {
        StringBuilder stringBuilder = new StringBuilder("AndroidManifest permissions for the following required MoPub activities are missing:\n");
        for (Class<? extends Activity> activity : undeclaredActivities) {
            stringBuilder.append("\n\t").append(activity.getName());
        }
        stringBuilder.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(stringBuilder.toString());
    }

    private static void logMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> misconfiguredActivities) {
        StringBuilder stringBuilder = new StringBuilder("In AndroidManifest, the android:configChanges param is missing values for the following MoPub activities:\n");
        for (Class<? extends Activity> activity : misconfiguredActivities) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, activity);
                if (!activityConfigChanges.hasKeyboardHidden) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + activity.getName() + " must include keyboardHidden.");
                }
                if (!activityConfigChanges.hasOrientation) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + activity.getName() + " must include orientation.");
                }
                if (!activityConfigChanges.hasScreenSize) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + activity.getName() + " must include screenSize.");
                }
            } catch (NameNotFoundException e) {
            }
        }
        stringBuilder.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(stringBuilder.toString());
    }

    private static ActivityConfigChanges getActivityConfigChanges(@NonNull Context context, @NonNull Class<? extends Activity> activity) throws NameNotFoundException {
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context, activity.getName()), 0);
        ActivityConfigChanges activityConfigChanges = new ActivityConfigChanges(null);
        activityConfigChanges.hasKeyboardHidden = sFlagCheckUtil.hasFlag(activity, activityInfo.configChanges, 32);
        activityConfigChanges.hasOrientation = sFlagCheckUtil.hasFlag(activity, activityInfo.configChanges, 128);
        activityConfigChanges.hasScreenSize = true;
        activityConfigChanges.hasScreenSize = sFlagCheckUtil.hasFlag(activity, activityInfo.configChanges, 1024);
        return activityConfigChanges;
    }

    private static void logWarningToast(@NonNull Context context) {
        if (isDebuggable(context)) {
            String message = "ERROR: YOUR MOPUB INTEGRATION IS INCOMPLETE.\nCheck logcat and update your AndroidManifest.xml with the correct activities and configuration.";
            Toast toast = Toast.makeText(context, "ERROR: YOUR MOPUB INTEGRATION IS INCOMPLETE.\nCheck logcat and update your AndroidManifest.xml with the correct activities and configuration.", 1);
            toast.setGravity(7, 0, 0);
            toast.show();
        }
    }

    @Deprecated
    @VisibleForTesting
    static List<Class<? extends Activity>> getRequiredWebViewSdkActivities() {
        return REQUIRED_WEB_VIEW_SDK_ACTIVITIES;
    }

    @Deprecated
    @VisibleForTesting
    static List<Class<? extends Activity>> getRequiredNativeSdkActivities() {
        return REQUIRED_NATIVE_SDK_ACTIVITIES;
    }

    @Deprecated
    @VisibleForTesting
    static void setFlagCheckUtil(FlagCheckUtil flagCheckUtil) {
        sFlagCheckUtil = flagCheckUtil;
    }
}
