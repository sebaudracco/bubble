package com.appsgeyser.sdk.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.server.network.NetworkManager.RequestType;
import com.areametrics.areametricssdk.AMLocationUpdatesBroadcastReceiver;
import com.bgjd.ici.MarketBootReceiver;
import com.bgjd.ici.MarketConnectivityChange;
import com.cuebiq.cuebiqsdk.receiver.CoverageReceiver;
import com.elephant.data.ElephantBroadcastReceiver;
import com.oneaudience.sdk.OneAudienceReceiver;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;

public class DataSdksReceiverChecker extends BroadcastReceiver {
    private static final String LOG_DATA_SDK_RECEIVER = "DataSdksReceiver";
    private static final String LOG_DATA_SDK_RECEIVER_PERMISSIONS = "DataSdksPermission";

    @SuppressLint({"UnsafeProtectedBroadcastReceiver"})
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            final PreferencesCoder coder = new PreferencesCoder(context);
            NetworkManager.getInstance().sendRequestAsync(coder.getPrefString(Constants.PREFS_CONFIG_PHP_URL, ""), Integer.valueOf(RequestType.CONFIG_PHP.ordinal()), context, new Listener<String>() {
                public void onResponse(String response) {
                    boolean revealSdkPermission = true;
                    try {
                        boolean oneAudienceSdkPermission;
                        boolean mobiInfoSdkPermission;
                        boolean cuebiqSdkPermission;
                        boolean instantCoffeeSdkPermission;
                        boolean elephantDataSdkPermission;
                        boolean profiler42mattersPermission;
                        boolean areaMetricsSdkPermission;
                        ConfigPhp configPhp = ConfigPhp.parseFromJson(response);
                        if (configPhp.getOneAudienceSdk().isActive() && (configPhp.getOneAudienceSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, false))) {
                            oneAudienceSdkPermission = true;
                        } else {
                            oneAudienceSdkPermission = false;
                        }
                        if (configPhp.getMobiInfoSdk().isActive() && (configPhp.getMobiInfoSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_MOBI_INFO_SDK_EULA_ACCEPTED, false))) {
                            mobiInfoSdkPermission = true;
                        } else {
                            mobiInfoSdkPermission = false;
                        }
                        if (configPhp.getCuebiqSdk().isActive() && (configPhp.getCuebiqSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false))) {
                            cuebiqSdkPermission = true;
                        } else {
                            cuebiqSdkPermission = false;
                        }
                        if (configPhp.getInstantCoffeeSdk().isActive() && (configPhp.getInstantCoffeeSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, false))) {
                            instantCoffeeSdkPermission = true;
                        } else {
                            instantCoffeeSdkPermission = false;
                        }
                        if (configPhp.getElephantDataSdk().isActive() && (configPhp.getElephantDataSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, false))) {
                            elephantDataSdkPermission = true;
                        } else {
                            elephantDataSdkPermission = false;
                        }
                        if (configPhp.getProfiler42mattersSdk().isActive() && (configPhp.getProfiler42mattersSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, false))) {
                            profiler42mattersPermission = true;
                        } else {
                            profiler42mattersPermission = false;
                        }
                        if (configPhp.getAreaMetricsSdk().isActive() && (configPhp.getAreaMetricsSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, false))) {
                            areaMetricsSdkPermission = true;
                        } else {
                            areaMetricsSdkPermission = false;
                        }
                        if (!(configPhp.getRevealSdk().isActive() && (configPhp.getRevealSdk().isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, false)))) {
                            revealSdkPermission = false;
                        }
                        coder.savePrefBoolean(Constants.PREFS_MOBI_INFO_SDK_ACTIVATED, mobiInfoSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_CUEBIQ_SDK_ACTIVATED, cuebiqSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_INSTANT_COFFEE_SDK_ACTIVATED, instantCoffeeSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_ACTIVATED, elephantDataSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_ACTIVATED, oneAudienceSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED, profiler42mattersPermission);
                        coder.savePrefBoolean(Constants.PREFS_AREAMETRICS_SDK_ACTIVATED, areaMetricsSdkPermission);
                        coder.savePrefBoolean(Constants.PREFS_REVEAL_SDK_ACTIVATED, revealSdkPermission);
                        DataSdksReceiverChecker.this.passDataToSdk(oneAudienceSdkPermission, cuebiqSdkPermission, mobiInfoSdkPermission, instantCoffeeSdkPermission, elephantDataSdkPermission, profiler42mattersPermission, areaMetricsSdkPermission, revealSdkPermission, context, intent);
                    } catch (Exception e) {
                        Log.e(DataSdksReceiverChecker.LOG_DATA_SDK_RECEIVER, "parsing response error...\n" + e.getMessage());
                        DataSdksReceiverChecker.this.onServerError(context, intent);
                    }
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    if (error != null) {
                        error.printStackTrace();
                        if (error.getMessage() != null) {
                            Log.e(DataSdksReceiverChecker.LOG_DATA_SDK_RECEIVER, "Volley request error: parsing response error...\n" + error.getMessage());
                        } else {
                            Log.e(DataSdksReceiverChecker.LOG_DATA_SDK_RECEIVER, "Volley request error: parsing response error...\n");
                        }
                    }
                    DataSdksReceiverChecker.this.onServerError(context, intent);
                }
            });
        }
    }

    private void onServerError(Context context, Intent intent) {
        PreferencesCoder coder = new PreferencesCoder(context);
        passDataToSdk(coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_MOBI_INFO_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_INSTANT_COFFEE_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_AREAMETRICS_SDK_ACTIVATED, true), coder.getPrefBoolean(Constants.PREFS_REVEAL_SDK_ACTIVATED, true), context, intent);
    }

    private void passDataToSdk(boolean oneAudienceSdkPermission, boolean cuebiqSdkPermission, boolean permissionMobiInfoSdk, boolean instantCoffeeSdkPermission, boolean elephantDataSdkPermission, boolean profiler42mattersSdkPermission, boolean areaMetricsSdkPermission, boolean revealSdkPermission, @NonNull Context context, @NonNull Intent intent) {
        String action = intent.getAction();
        if (cuebiqSdkPermission && (action.equals("android.location.PROVIDERS_CHANGED") || action.equals("android.intent.action.BOOT_COMPLETED"))) {
            new CoverageReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "CoverageReceiver start with action : " + intent.getAction());
        }
        if ((oneAudienceSdkPermission && (action.equals("android.intent.action.PACKAGE_REMOVED") || action.equals("android.intent.action.PACKAGE_REPLACED") || action.equals("android.intent.action.PACKAGE_ADDED"))) || action.equals("com.oneaudience.action.CONFIGURATION")) {
            new OneAudienceReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "OneAudienceReceiver start with action : " + intent.getAction());
        }
        if (elephantDataSdkPermission && (action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_REMOVED"))) {
            new ElephantBroadcastReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "elephantDataSdk start with action : " + intent.getAction());
        }
        if (permissionMobiInfoSdk && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            new MarketConnectivityChange().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "MbinfConnectivityChange start with action : " + intent.getAction());
        }
        if (permissionMobiInfoSdk && action.equals("android.intent.action.BOOT_COMPLETED")) {
            new MarketBootReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "MarketBootReceiver start with action : " + intent.getAction());
        }
        if ((permissionMobiInfoSdk || areaMetricsSdkPermission) && (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.ACTION_POWER_CONNECTED") || action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))) {
            new StartupBroadcastReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "BeaconBroadcastReceiver start with action : " + intent.getAction());
        }
        if (areaMetricsSdkPermission && (action.equals("com.areametrics.areametricssdk.AMLocationUpdatesBroadcastReceiver.ACTION_PROCESS_UPDATES") || action.equals("com.areametrics.areametricssdk.AMLocationUpdatesBroadcastReceiver.PROCESS_UPDATES"))) {
            new AMLocationUpdatesBroadcastReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "areaMetrics start with action : " + intent.getAction());
        }
        if (revealSdkPermission && (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.ACTION_POWER_CONNECTED") || action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))) {
            new com.stepleaderdigital.reveal.StartupBroadcastReceiver().onReceive(context, intent);
            Log.d(LOG_DATA_SDK_RECEIVER, "reveal start with action : " + intent.getAction());
        }
        if (permissionMobiInfoSdk) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for MobiInfoReceiver granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for MobiInfoReceiver NOT granted");
        }
        if (oneAudienceSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for OneAudienceReceiver granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for OneAudienceReceiver NOT granted");
        }
        if (cuebiqSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for cuebiqSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for cuebiqSdk NOT granted");
        }
        if (elephantDataSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for elephantDataSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for elephantDataSdk NOT granted");
        }
        if (instantCoffeeSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for instantCoffeeSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for instantCoffeeSdk NOT granted");
        }
        if (profiler42mattersSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for profiler42mattersSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for profiler42mattersSdk NOT granted");
        }
        if (areaMetricsSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for areaMetricsSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for areaMetricsSdk NOT granted");
        }
        if (revealSdkPermission) {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for revealSdk granted");
        } else {
            Log.d(LOG_DATA_SDK_RECEIVER_PERMISSIONS, "Permission for revealSdk NOT granted");
        }
    }
}
