package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.configuration.models.ConfigPhpSdkModel;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.areametrics.areametricssdk.AreaMetricsSDK;
import com.bgjd.ici.partner.IMarketPartner.EULA;
import com.bgjd.ici.partner.MarketPartner.Builder;
import com.cuebiq.cuebiqsdk.CuebiqSDK;
import com.elephant.data.ElephantLib;
import com.oneaudience.sdk.OneAudience;
import com.stepleaderdigital.reveal.Reveal;

public class DataSdkController {
    private static final String ACCEPTED_SDK_KEY = "sdkIsAccepted";
    private static final String AREAMETRICS_SDK_APIKEY_KEY = "areaMetricsSdk_key";
    private static final String AREAMETRICS_SDK_APPID_KEY = "areaMetricsSdk_id";
    private static final String COUNT_OF_TRY_KEY = "countOfTry";
    private static final String CUEBIG_SDK_ID_KEY = "cuebiqSdk_id";
    private static final String ELEPHANT_DATA_SDK_ID_KEY = "elephantDataSdk_id";
    private static final int INCORRECT_VALUE = -1;
    private static final String INSTANT_COFFEE_SDK_ID_KEY = "instantCoffeeSdk_id";
    private static final String ONE_AUDIENCE_SDK_ID_KEY = "oneAudienceSdk_id";
    public static final String PREFS_ELAPSED_TIME = "elapsedTime";
    private static final String PROFILER42MATTERS_DATA_SDK_ID_KEY = "profiler42mattersSdk_id";
    private static final String REVEAL_SDK_API_ENDPOINT_KEY = "revealSdk_api_endpoint";
    private static final String REVEAL_SDK_ID_KEY = "revealSdk_id";
    private static final String SERVER_ERROR_LOG = "dataSDKServerErr";
    private static final String START_LOG = "startDataSDK";
    private static final long TWO_HOURS_IN_MILLIS = 7200000;

    public static void startDataSdkController(@NonNull Context context, @NonNull ConfigPhp configPhp) {
        PreferencesCoder coder = new PreferencesCoder(context);
        int countOfTryFromPrefs = coder.getPrefInt(COUNT_OF_TRY_KEY, -1);
        int countOfTryFromJson = configPhp.getCountOfTry();
        if (-1 == countOfTryFromPrefs) {
            coder.savePrefInt(COUNT_OF_TRY_KEY, countOfTryFromJson);
        }
        ConfigPhpSdkModel oneAudienceSdk = configPhp.getOneAudienceSdk();
        ConfigPhpSdkModel cuebiqSdk = configPhp.getCuebiqSdk();
        ConfigPhpSdkModel mobiInfoSdk = configPhp.getMobiInfoSdk();
        ConfigPhpSdkModel instantCoffeeSdk = configPhp.getInstantCoffeeSdk();
        ConfigPhpSdkModel elephantDataSdk = configPhp.getElephantDataSdk();
        ConfigPhpSdkModel profiler42mattersSdk = configPhp.getProfiler42mattersSdk();
        ConfigPhpSdkModel areaMetricsSdk = configPhp.getAreaMetricsSdk();
        ConfigPhpSdkModel revealSdk = configPhp.getRevealSdk();
        ConfigPhpSdkModel appsgeyserSdk = configPhp.getAppsgeyserSdk();
        String str = Constants.PREFS_ONE_AUDIENCE_SDK_ACTIVATED;
        boolean z = oneAudienceSdk.isActive() && (oneAudienceSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_CUEBIQ_SDK_ACTIVATED;
        z = cuebiqSdk.isActive() && (cuebiqSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_MOBI_INFO_SDK_ACTIVATED;
        z = mobiInfoSdk.isActive() && (mobiInfoSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_MOBI_INFO_SDK_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_INSTANT_COFFEE_SDK_ACTIVATED;
        z = instantCoffeeSdk.isActive() && (instantCoffeeSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_ELEPHANT_DATA_SDK_ACTIVATED;
        z = elephantDataSdk.isActive() && (elephantDataSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED;
        z = profiler42mattersSdk.isActive() && (profiler42mattersSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_AREAMETRICS_SDK_ACTIVATED;
        z = areaMetricsSdk.isActive() && (areaMetricsSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_REVEAL_SDK_ACTIVATED;
        z = revealSdk.isActive() && (revealSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        str = Constants.PREFS_APPSGEYSER_SDK_ACTIVATED;
        z = appsgeyserSdk.isActive() && (appsgeyserSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_APPSGEYSER_EULA_ACCEPTED, false));
        coder.savePrefBoolean(str, z);
        countOfTryFromPrefs = coder.getPrefInt(COUNT_OF_TRY_KEY, -1);
        long timeFromPrefs = coder.getPrefLong(PREFS_ELAPSED_TIME, -1);
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedTime = currentTimeMillis - timeFromPrefs;
        if (countOfTryFromPrefs <= 0 || (-1 != timeFromPrefs && elapsedTime <= TWO_HOURS_IN_MILLIS)) {
            initSdk(configPhp, context);
        } else if (isSdkAccepted(context)) {
            checkPermissions(context, configPhp, null);
        } else {
            String mobiInfoSdkTextOfPolicy = mobiInfoSdk.getTextOfPolicy();
            String oneAudienceSdkTextOfPolicy = oneAudienceSdk.getTextOfPolicy();
            String cuebiqSdkTextOfPolicy = cuebiqSdk.getTextOfPolicy();
            String instantCoffeeSdkTextOfPolicy = instantCoffeeSdk.getTextOfPolicy();
            String elephantDataSdkTextOfPolicy = elephantDataSdk.getTextOfPolicy();
            String profiler42mattersSdkTextOfPolicy = profiler42mattersSdk.getTextOfPolicy();
            String areaMetricsSdkTextOfPolicy = areaMetricsSdk.getTextOfPolicy();
            String revealSdkTextOfPolicy = revealSdk.getTextOfPolicy();
            String appsgeyserSdkTextOfPolicy = appsgeyserSdk.getTextOfPolicy();
            StringBuilder stringBuilder = new StringBuilder();
            if (!(!mobiInfoSdk.isActive() || mobiInfoSdk.isActiveByDefault() || TextUtils.isEmpty(mobiInfoSdkTextOfPolicy))) {
                stringBuilder.append(mobiInfoSdkTextOfPolicy);
            }
            if (!(!oneAudienceSdk.isActive() || oneAudienceSdk.isActiveByDefault() || TextUtils.isEmpty(oneAudienceSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(oneAudienceSdkTextOfPolicy);
            }
            if (!(!cuebiqSdk.isActive() || cuebiqSdk.isActiveByDefault() || TextUtils.isEmpty(cuebiqSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(cuebiqSdkTextOfPolicy);
            }
            if (!(!instantCoffeeSdk.isActive() || instantCoffeeSdk.isActiveByDefault() || TextUtils.isEmpty(instantCoffeeSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(instantCoffeeSdkTextOfPolicy);
            }
            if (!(!elephantDataSdk.isActive() || elephantDataSdk.isActiveByDefault() || TextUtils.isEmpty(elephantDataSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(elephantDataSdkTextOfPolicy);
            }
            if (!(!profiler42mattersSdk.isActive() || profiler42mattersSdk.isActiveByDefault() || TextUtils.isEmpty(profiler42mattersSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(profiler42mattersSdkTextOfPolicy);
            }
            if (!(!areaMetricsSdk.isActive() || areaMetricsSdk.isActiveByDefault() || TextUtils.isEmpty(areaMetricsSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(areaMetricsSdkTextOfPolicy);
            }
            if (!(!revealSdk.isActive() || revealSdk.isActiveByDefault() || TextUtils.isEmpty(revealSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(revealSdkTextOfPolicy);
            }
            if (!(!appsgeyserSdk.isActive() || appsgeyserSdk.isActiveByDefault() || TextUtils.isEmpty(appsgeyserSdkTextOfPolicy))) {
                if (stringBuilder.toString().length() > 0) {
                    stringBuilder.append("\n\n");
                }
                stringBuilder.append(appsgeyserSdkTextOfPolicy);
            }
            checkPermissions(context, configPhp, stringBuilder.toString());
        }
        coder.savePrefLong(PREFS_ELAPSED_TIME, currentTimeMillis);
    }

    public static void onGetConfigErrorResponse(@NonNull Context context) {
        PreferencesCoder coder = new PreferencesCoder(context);
        boolean lastServerResponseOneAudienceSdkPermission = coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_ACTIVATED, false);
        String lastServerResponseOneAudienceSdkId = coder.getPrefString(ONE_AUDIENCE_SDK_ID_KEY, "");
        boolean lastServerResponseCuebiqSdkPermission = coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_ACTIVATED, false);
        String lastServerResponseCuebiqSdkId = coder.getPrefString(CUEBIG_SDK_ID_KEY, "");
        boolean lastServerResponseInstantCoffeeSdkPermission = coder.getPrefBoolean(Constants.PREFS_INSTANT_COFFEE_SDK_ACTIVATED, false);
        String lastServerResponseInstantCoffeeSdkId = coder.getPrefString(INSTANT_COFFEE_SDK_ID_KEY, "");
        boolean lastServerResponseElephantDataSdkPermission = coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_ACTIVATED, false);
        String lastServerResponseElephantDataSdkId = coder.getPrefString(ELEPHANT_DATA_SDK_ID_KEY, "");
        boolean lastServerResponseMobiInfoSdkPermission = coder.getPrefBoolean(Constants.PREFS_MOBI_INFO_SDK_ACTIVATED, false);
        String lastServerResponseProfiler42mattersSdkId = coder.getPrefString(PROFILER42MATTERS_DATA_SDK_ID_KEY, "");
        boolean lastServerResponseProfiler42mattersSdkPermission = coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED, false);
        String lastServerResponseAreaMetricsSdkAppId = coder.getPrefString(AREAMETRICS_SDK_APPID_KEY, "");
        String lastServerResponseAreaMetricsSdkApiKey = coder.getPrefString(AREAMETRICS_SDK_APIKEY_KEY, "");
        boolean lastServerResponseAreaMetricsSdkPermission = coder.getPrefBoolean(Constants.PREFS_AREAMETRICS_SDK_ACTIVATED, false);
        String lastServerResponseRevealSdkId = coder.getPrefString(REVEAL_SDK_ID_KEY, "");
        String lastServerResponseRevealSdkApiEndpoint = coder.getPrefString(REVEAL_SDK_API_ENDPOINT_KEY, "");
        boolean lastServerResponseRevealSdkPermission = coder.getPrefBoolean(Constants.PREFS_REVEAL_SDK_ACTIVATED, false);
        boolean lastServerResponseAppsgeyserSdkPermission = coder.getPrefBoolean(Constants.PREFS_APPSGEYSER_SDK_ACTIVATED, false);
        if (!lastServerResponseOneAudienceSdkPermission || lastServerResponseOneAudienceSdkId.equals("")) {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for OneAudienceSdk was NOT granted");
        } else {
            startOneAudienceSdk(context, lastServerResponseOneAudienceSdkId);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for OneAudienceSdk was granted");
        }
        if (!lastServerResponseCuebiqSdkPermission || lastServerResponseCuebiqSdkId.equals("")) {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for CuebiqSdk was NOT granted");
        } else {
            startCuebiqSdk(context, lastServerResponseCuebiqSdkId);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for CuebiqSdk was granted");
        }
        if (!lastServerResponseInstantCoffeeSdkPermission || lastServerResponseInstantCoffeeSdkId.equals("")) {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for instantCoffeeSdk was NOT granted");
        } else {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for instantCoffeeSdk was granted");
        }
        if (!lastServerResponseElephantDataSdkPermission || lastServerResponseElephantDataSdkId.equals("")) {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for instantCoffeeSdk was NOT granted");
        } else {
            startElephantDataSdk(context, lastServerResponseElephantDataSdkId);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for instantCoffeeSdk was granted");
        }
        if (lastServerResponseMobiInfoSdkPermission) {
            startMobiInfoSdk(context);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for MobiInfo was granted");
        } else {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for MobiInfo was NOT granted");
        }
        if (lastServerResponseProfiler42mattersSdkPermission) {
            startProfiler42mattersSdk(context, lastServerResponseProfiler42mattersSdkId);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for Profiler42matters was granted");
        } else {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for Profiler42matters was NOT granted");
        }
        if (lastServerResponseAreaMetricsSdkPermission) {
            startAreaMetricsSdk(lastServerResponseAreaMetricsSdkAppId, lastServerResponseAreaMetricsSdkApiKey);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for AreaMetrics was granted");
        } else {
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for AreaMetrics was NOT granted");
        }
        if (lastServerResponseRevealSdkPermission) {
            startRevealSdk(lastServerResponseRevealSdkId, lastServerResponseRevealSdkApiEndpoint);
            Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for Reveal was granted");
            return;
        }
        Log.d(SERVER_ERROR_LOG, "Server Error. Last permission for Reveal was NOT granted");
    }

    public static void revokeDataCollectionConsent(Context context) {
        PreferencesCoder coder = new PreferencesCoder(context);
        coder.savePrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_MOBI_INFO_SDK_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, false);
        coder.savePrefBoolean(Constants.PREFS_APPSGEYSER_EULA_ACCEPTED, false);
        if (coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_ACTIVATED, false)) {
            OneAudience.optOut();
        }
        if (coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_ACTIVATED, false)) {
            CuebiqSDK.disableSDKCollection(context);
        }
        if (coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_ACTIVATED, false)) {
            ElephantLib.revokeConsent(context);
        }
        if (coder.getPrefBoolean(Constants.PREFS_MOBI_INFO_SDK_ACTIVATED, false)) {
            new Builder(context).setPartnerEulaType(EULA.DIALOG).build().onEulaDeclined();
        }
        if (coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED, false)) {
            coder.savePrefBoolean("com.core42matters.android.registrar.user_consent", false);
        }
    }

    static void acceptAllActiveSdk(@NonNull Context context, @NonNull ConfigPhp configPhp) {
        acceptSdk(context, configPhp);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_ACCEPT_SDK_DIALOG);
    }

    static void declineAllActiveSdk(@NonNull final Context context, @NonNull final ConfigPhp configPhp, AppCompatActivity dataActivity, final String textOfPolicy) {
        if (configPhp.getStartupELUAConfirmationDialogAllow()) {
            declineActiveSdk(context, configPhp, new PreferencesCoder(context));
            if (VERSION.SDK_INT < 23 || !PermissionsRequester.isPermissionsRequired(configPhp, context)) {
                initSdk(configPhp, context);
                dataActivity.finish();
                return;
            }
            PermissionsRequester.requestAllActiveByDefaultPermissions((Activity) context, configPhp, 78);
            return;
        }
        final DataSdkActivity dataSdkActivity = (DataSdkActivity) dataActivity;
        AlertDialog.Builder builder = new AlertDialog.Builder(dataSdkActivity);
        builder.setMessage(C1195R.string.appsgeysersdk_are_you_sure_decline_sdk);
        builder.setCancelable(false);
        builder.setPositiveButton(C1195R.string.appsgeysersdk_close_app, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PreferencesCoder coder = new PreferencesCoder(dataSdkActivity);
                DataSdkController.declineActiveSdk(context, configPhp, coder);
                AppsgeyserServerClient.getInstance().setConfigPhpModel(null);
                coder.savePrefLong(DataSdkController.PREFS_ELAPSED_TIME, 0);
                if (VERSION.SDK_INT >= 16) {
                    dataSdkActivity.finishAffinity();
                } else {
                    ActivityCompat.finishAffinity(dataSdkActivity);
                }
            }
        });
        builder.setNegativeButton(C1195R.string.appsgeysersdk_back, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dataSdkActivity.showEulaDialog(textOfPolicy, configPhp);
            }
        });
        builder.create().show();
    }

    private static void declineActiveSdk(Context context, ConfigPhp configPhp, PreferencesCoder coder) {
        int newValueCountOfTry = coder.getPrefInt(COUNT_OF_TRY_KEY, -1) - 1;
        coder.savePrefInt(COUNT_OF_TRY_KEY, newValueCountOfTry);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_DECLINE_SDK_DIALOG);
        if (newValueCountOfTry == 0) {
            if (configPhp.getMobiInfoSdk().isActive()) {
                new Builder(context).setPartnerEulaType(EULA.DIALOG).build().onEulaDeclined();
                coder.savePrefBoolean(Constants.PREFS_MOBI_INFO_SDK_EULA_ACCEPTED, false);
            }
            if (configPhp.getCuebiqSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false);
            }
            if (configPhp.getOneAudienceSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false);
            }
            if (configPhp.getElephantDataSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, false);
            }
            if (configPhp.getProfiler42mattersSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, false);
            }
            if (configPhp.getInstantCoffeeSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, false);
            }
            if (configPhp.getAreaMetricsSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, false);
            }
            if (configPhp.getRevealSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, false);
            }
            if (configPhp.getAppsgeyserSdk().isActive()) {
                coder.savePrefBoolean(Constants.PREFS_APPSGEYSER_EULA_ACCEPTED, false);
            }
        }
    }

    static void initSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        AppsgeyserSDK.getFastTrackAdsController().consentRequestProcessFinished();
        initMobiInfoSdk(configPhp, context);
        initOneAudienceSdk(configPhp, context);
        initCuebigSdk(configPhp, context);
        initElephantDataSdk(configPhp, context);
        initProfiler42mattersSdk(configPhp, context);
        initAreaMetricsSdk(configPhp, context);
        initRevealSdk(configPhp, context);
    }

    static boolean isSdkAccepted(@NonNull Context context) {
        return new PreferencesCoder(context).getPrefBoolean(ACCEPTED_SDK_KEY, false);
    }

    private static void startOneAudienceSdk(Context context, String id) {
        OneAudience.init(context, id);
        Log.d(START_LOG, "startOneAudienceSDK at first time");
    }

    private static void startCuebiqSdk(Context context, String id) {
        CuebiqSDK.initialize(context, id);
        CuebiqSDK.userGaveGDPRConsent(context);
        Log.d(START_LOG, "startCuebiqSdk at first time");
    }

    private static void startInstantCoffeeSdk(Context context, String id) {
        Log.d(START_LOG, "startInstantCoffeeSdk at first time");
    }

    private static void startElephantDataSdk(Context context, String id) {
        ElephantLib.init((Application) context.getApplicationContext(), id);
        ElephantLib.grantConsent(context);
        Log.d(START_LOG, "startElephantDataSdk at first time");
    }

    private static void startMobiInfoSdk(@NonNull Context context) {
        new Builder(context).setPartnerEulaType(EULA.DIALOG).build().start();
        Log.d(START_LOG, "startMobiInfoSdk at first time");
    }

    private static void startProfiler42mattersSdk(@NonNull Context context, String id) {
        Log.d(START_LOG, "startProfiler42mattersSdk at first time");
    }

    private static void startAreaMetricsSdk(String appId, String apiKey) {
        AreaMetricsSDK.INSTANCE.startService(InternalEntryPoint.getInstance().getApplication(), appId, apiKey);
        Log.d(START_LOG, "startAreaMetricsSdk at first time");
    }

    private static void startRevealSdk(String id, String baseEndPoint) {
        Reveal reveal = Reveal.getInstance();
        reveal.setAPIKey(id);
        reveal.setAPIEndpointBase(baseEndPoint);
        reveal.start(InternalEntryPoint.getInstance().getApplication());
        Log.d(START_LOG, "startRevealSdk at first time");
    }

    private static void checkPermissions(@NonNull Context context, @NonNull ConfigPhp configPhp, @Nullable String text) {
        if (PermissionsRequester.isPermissionsRequired(configPhp, context) || !(isSdkAccepted(context) || TextUtils.isEmpty(text))) {
            InternalEntryPoint.getInstance().setConsentRequestProcessActive(true);
            DataSdkActivity.startRequestPermissions(context, configPhp, text);
            return;
        }
        initSdk(configPhp, context);
    }

    private static void acceptSdk(@NonNull Context context, @NonNull ConfigPhp configPhp) {
        new Builder(context).setPartnerEulaType(EULA.DIALOG).build().onEulaAccepted();
        PreferencesCoder coder = new PreferencesCoder(context);
        coder.savePrefBoolean(ACCEPTED_SDK_KEY, true);
        if (configPhp.getCuebiqSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, true);
        }
        if (configPhp.getOneAudienceSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, true);
        }
        if (configPhp.getElephantDataSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, true);
        }
        if (configPhp.getProfiler42mattersSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, true);
        }
        if (configPhp.getInstantCoffeeSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, true);
        }
        if (configPhp.getAreaMetricsSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, true);
        }
        if (configPhp.getRevealSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, true);
        }
        if (configPhp.getAppsgeyserSdk().isActive()) {
            coder.savePrefBoolean(Constants.PREFS_APPSGEYSER_EULA_ACCEPTED, true);
        }
    }

    private static void initMobiInfoSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel mobiInfoSdk = configPhp.getMobiInfoSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!mobiInfoSdk.isActive()) {
            return;
        }
        if (mobiInfoSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, false)) {
            startMobiInfoSdk(context);
        }
    }

    private static void initOneAudienceSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel oneAudienceSdk = configPhp.getOneAudienceSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!oneAudienceSdk.isActive()) {
            return;
        }
        if (oneAudienceSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED, false)) {
            coder.savePrefString(ONE_AUDIENCE_SDK_ID_KEY, oneAudienceSdk.getId());
            startOneAudienceSdk(context, oneAudienceSdk.getId());
        }
    }

    private static void initCuebigSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel cuebiqSdk = configPhp.getCuebiqSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!cuebiqSdk.isActive()) {
            return;
        }
        if (cuebiqSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_CUEBIQ_SDK_EULA_ACCEPTED, false)) {
            coder.savePrefString(CUEBIG_SDK_ID_KEY, cuebiqSdk.getId());
            startCuebiqSdk(context, cuebiqSdk.getId());
        }
    }

    private static void initInstantCoffeeSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel instantCoffeeSdk = configPhp.getInstantCoffeeSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!instantCoffeeSdk.isActive()) {
            return;
        }
        if (instantCoffeeSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_INSTANT_COFFEE_EULA_ACCEPTED, false)) {
            coder.savePrefString(INSTANT_COFFEE_SDK_ID_KEY, instantCoffeeSdk.getId());
            startInstantCoffeeSdk(context, instantCoffeeSdk.getId());
        }
    }

    private static void initElephantDataSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel elephantDataSdk = configPhp.getElephantDataSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!elephantDataSdk.isActive()) {
            return;
        }
        if (elephantDataSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED, false)) {
            coder.savePrefString(ELEPHANT_DATA_SDK_ID_KEY, elephantDataSdk.getId());
            startElephantDataSdk(context, elephantDataSdk.getId());
        }
    }

    private static void initProfiler42mattersSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel profiler42mattersSdk = configPhp.getProfiler42mattersSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!profiler42mattersSdk.isActive()) {
            return;
        }
        if (profiler42mattersSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED, false)) {
            coder.savePrefString(PROFILER42MATTERS_DATA_SDK_ID_KEY, profiler42mattersSdk.getId());
            startProfiler42mattersSdk(context, profiler42mattersSdk.getId());
        }
    }

    private static void initAreaMetricsSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel areaMetricsSdk = configPhp.getAreaMetricsSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!areaMetricsSdk.isActive()) {
            return;
        }
        if (areaMetricsSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_AREAMETRICS_EULA_ACCEPTED, false)) {
            coder.savePrefString(AREAMETRICS_SDK_APPID_KEY, areaMetricsSdk.getId());
            coder.savePrefString(AREAMETRICS_SDK_APIKEY_KEY, areaMetricsSdk.getTag());
            startAreaMetricsSdk(areaMetricsSdk.getId(), areaMetricsSdk.getTag());
        }
    }

    private static void initRevealSdk(@NonNull ConfigPhp configPhp, @NonNull Context context) {
        ConfigPhpSdkModel revealSdk = configPhp.getRevealSdk();
        PreferencesCoder coder = new PreferencesCoder(context);
        if (!revealSdk.isActive()) {
            return;
        }
        if (revealSdk.isActiveByDefault() || coder.getPrefBoolean(Constants.PREFS_REVEAL_EULA_ACCEPTED, false)) {
            coder.savePrefString(REVEAL_SDK_ID_KEY, revealSdk.getId());
            coder.savePrefString(REVEAL_SDK_API_ENDPOINT_KEY, revealSdk.getTag());
            startRevealSdk(revealSdk.getId(), revealSdk.getTag());
        }
    }
}
