package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.configuration.models.ConfigPhpSdkModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class PermissionsRequester {
    private static final String[] areaMetricsPermissions = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
    private static final String[] cuebiqPermissions = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static final String[] elephantDataPermissions = new String[0];
    private static final String[] instantCoffeePermissions = new String[0];
    private static final String[] mobiInfoPermissions = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.GET_ACCOUNTS"};
    private static final String[] oneAudiencePermissions = new String[]{"android.permission.GET_ACCOUNTS"};
    private static final String[] profiler42mattersPermissions = new String[0];
    private static final String[] revealPermissions = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    PermissionsRequester() {
    }

    static boolean isPermissionsRequired(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        Set<String> requestPermissions = new HashSet();
        PackageManager packageManager = context.getPackageManager();
        for (String permission : getPermissionRequiredFromConfig(configPhp)) {
            if (packageManager.checkPermission(permission, context.getPackageName()) != 0) {
                requestPermissions.add(permission);
            }
        }
        if (requestPermissions.size() > 0) {
            return true;
        }
        return false;
    }

    @RequiresApi(23)
    static void requestAllActiveByDefaultPermissions(@NonNull Activity activity, @NonNull ConfigPhp configPhp, int requestCode) {
        Set<String> permissions = new HashSet();
        ConfigPhpSdkModel mobiInfoSdk = configPhp.getMobiInfoSdk();
        if (mobiInfoSdk.isActive() && mobiInfoSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(mobiInfoPermissions));
        }
        ConfigPhpSdkModel cuebiqSdk = configPhp.getCuebiqSdk();
        if (cuebiqSdk.isActive() && cuebiqSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(cuebiqPermissions));
        }
        ConfigPhpSdkModel instantCoffee = configPhp.getInstantCoffeeSdk();
        if (instantCoffee.isActive() && instantCoffee.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(instantCoffeePermissions));
        }
        ConfigPhpSdkModel elephantDataSdk = configPhp.getElephantDataSdk();
        if (elephantDataSdk.isActive() && elephantDataSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(elephantDataPermissions));
        }
        ConfigPhpSdkModel oneAudienceSdk = configPhp.getOneAudienceSdk();
        if (oneAudienceSdk.isActive() && oneAudienceSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(oneAudiencePermissions));
        }
        ConfigPhpSdkModel profiler42mattersSdk = configPhp.getProfiler42mattersSdk();
        if (profiler42mattersSdk.isActive() && profiler42mattersSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(profiler42mattersPermissions));
        }
        ConfigPhpSdkModel areaMetricsSdk = configPhp.getAreaMetricsSdk();
        if (areaMetricsSdk.isActive() && areaMetricsSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(areaMetricsPermissions));
        }
        ConfigPhpSdkModel revealSdk = configPhp.getRevealSdk();
        if (revealSdk.isActive() && revealSdk.isActiveByDefault()) {
            permissions.addAll(Arrays.asList(revealPermissions));
        }
        String[] requiredPermissions = getNeedRequestPermissions(activity, permissions);
        if (requiredPermissions != null) {
            activity.requestPermissions(requiredPermissions, requestCode);
        } else {
            activity.onRequestPermissionsResult(78, new String[0], new int[0]);
        }
    }

    @RequiresApi(23)
    static void requestAllActivePermissions(@NonNull Activity activity, @NonNull ConfigPhp configPhp, int requestCode) {
        String[] needRequestPermissions = getNeedRequestPermissions(activity, getPermissionRequiredFromConfig(configPhp));
        if (needRequestPermissions != null) {
            activity.requestPermissions(needRequestPermissions, requestCode);
        } else {
            activity.onRequestPermissionsResult(78, new String[0], new int[0]);
        }
    }

    @Nullable
    private static String[] getNeedRequestPermissions(@NonNull Activity activity, @NonNull Set<String> permissions) {
        Set<String> requestPermissions = new HashSet();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != 0) {
                requestPermissions.add(permission);
            }
        }
        if (requestPermissions.size() > 0) {
            return (String[]) requestPermissions.toArray(new String[requestPermissions.size()]);
        }
        return null;
    }

    private static Set<String> getPermissionRequiredFromConfig(ConfigPhp configPhp) {
        Set<String> permissions = new HashSet();
        if (configPhp.getMobiInfoSdk().isActive()) {
            permissions.addAll(Arrays.asList(mobiInfoPermissions));
        }
        if (configPhp.getCuebiqSdk().isActive()) {
            permissions.addAll(Arrays.asList(cuebiqPermissions));
        }
        if (configPhp.getInstantCoffeeSdk().isActive()) {
            permissions.addAll(Arrays.asList(instantCoffeePermissions));
        }
        if (configPhp.getElephantDataSdk().isActive()) {
            permissions.addAll(Arrays.asList(elephantDataPermissions));
        }
        if (configPhp.getOneAudienceSdk().isActive()) {
            permissions.addAll(Arrays.asList(oneAudiencePermissions));
        }
        if (configPhp.getProfiler42mattersSdk().isActive()) {
            permissions.addAll(Arrays.asList(profiler42mattersPermissions));
        }
        if (configPhp.getAreaMetricsSdk().isActive()) {
            permissions.addAll(Arrays.asList(areaMetricsPermissions));
        }
        if (configPhp.getRevealSdk().isActive()) {
            permissions.addAll(Arrays.asList(revealPermissions));
        }
        return permissions;
    }
}
