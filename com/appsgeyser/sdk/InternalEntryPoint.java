package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.appsgeyser.sdk.AppsgeyserSDK.OfferWallEnabledListener;
import com.appsgeyser.sdk.AppsgeyserSDK.OnAboutDialogEnableListener;
import com.appsgeyser.sdk.AppsgeyserSDK.OnAdditionalJsLoaded;
import com.appsgeyser.sdk.ads.AdView;
import com.appsgeyser.sdk.ads.FullScreenBanner;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fullscreenSdk.FullscreenSdkHelper;
import com.appsgeyser.sdk.ads.rewardedVideo.RewardedAdHelper;
import com.appsgeyser.sdk.ads.sdk.SdkController;
import com.appsgeyser.sdk.analytics.Analytics;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.Constants.BannerLoadTags;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.deviceidparser.IDeviceIdParserListener;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.ConfigPhpRequestListener;
import com.appsgeyser.sdk.server.network.NetworkAvailableReceiver;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;
import com.appsgeyser.sdk.ui.AboutDialogActivity;
import com.appsgeyser.sdk.ui.AdvertisingTermsDialog;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.gson.JsonSyntaxException;
import com.yandex.metrica.YandexMetrica;
import java.util.HashMap;

public final class InternalEntryPoint implements IDeviceIdParserListener {
    private static final String CONFIG_PHP_KEY = "config_php_key";
    private static final InternalEntryPoint INSTANCE = new InternalEntryPoint();
    private static final String METRICA_ON_START_EVENT = "on_start_event";
    private OnAboutDialogEnableListener aboutDialogEnabledListener;
    private AdView adView = null;
    private AdvertisingTermsDialog advertisingTermsDialog;
    private Application application;
    private Configuration configuration = null;
    private boolean doneDeviceParser;
    private boolean enablePull = false;
    private FastTrackAdsController fastTrackAdsController;
    private FullScreenBanner fullScreenBanner;
    private FullscreenSdkHelper fullscreenSdkHelper;
    private DeviceIdParameters idParameters;
    private boolean isApplicationVisible = true;
    private boolean isConsentRequestProcessActive;
    private boolean isOnResumeFSEnabled;
    private OnAdditionalJsLoaded jsLoader;
    private OnNetworkStateChangedListener networkAvailableListener;
    private NetworkAvailableReceiver networkReceiver;
    private RewardedAdHelper rewardedAdHelper;
    private boolean saveDialogEnableListener;
    private HashMap<String, Boolean> wasFSShownInOnResume = new HashMap(6);

    public static InternalEntryPoint getInstance() {
        return INSTANCE;
    }

    void takeOff(@NonNull Activity activity, @NonNull String APIkey, @NonNull String appMetricaOnStartEvent, @NonNull String templateVersion) {
        if (!TextUtils.isEmpty(APIkey)) {
            if (checkPermissions(activity)) {
                init(activity, templateVersion, APIkey, appMetricaOnStartEvent);
                PausedContentInfoActivity.checkBanApp(activity);
                YandexMetrica.activate(activity, Constants.APP_METRICA_ID);
                YandexMetrica.enableActivityAutoTracking(activity.getApplication());
                try {
                    String metricaOnStartEvent = this.configuration.getMetricaOnStartEvent();
                    if (metricaOnStartEvent != null) {
                        YandexMetrica.reportEvent(METRICA_ON_START_EVENT, metricaOnStartEvent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
        if (NetworkManager.isOnline(activity)) {
            DeviceIdParser.getInstance().rescan(activity, this);
            this.doneDeviceParser = true;
        }
    }

    private void init(@NonNull Activity activity, @NonNull String templateVersion, @NonNull String APIkey, @NonNull String appMetricaOnStartEvent) {
        this.configuration = Configuration.getInstance(activity);
        this.configuration.setTemplateVersion(templateVersion);
        this.configuration.loadConfiguration();
        if (!(this.configuration.getApplicationId() != null ? this.configuration.getApplicationId() : "").equals(APIkey)) {
            this.configuration.clearApplicationSettings();
            this.configuration.setApplicationId(APIkey);
            this.configuration.setMetricaOnStartEvent(appMetricaOnStartEvent, templateVersion);
        }
        new Analytics(activity).activityStarted(activity);
        this.fullScreenBanner = new FullScreenBanner(activity);
        if (this.fastTrackAdsController == null) {
            this.fastTrackAdsController = new FastTrackAdsController();
        }
        this.advertisingTermsDialog = new AdvertisingTermsDialog(activity);
        if (this.networkReceiver == null) {
            this.networkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(activity);
        }
        this.networkAvailableListener = this.networkReceiver.createNetworkAvailableListener(activity);
        this.networkReceiver.addListener(this.networkAvailableListener);
        SdkController.initSdk(activity);
    }

    public void retryParsers(Context context) {
        if (!this.doneDeviceParser) {
            DeviceIdParser.getInstance().rescan(context, this);
            this.doneDeviceParser = true;
        }
    }

    public FullScreenBanner getFullScreenBanner(Context context) {
        FullScreenBanner fullScreenBanner;
        synchronized (InternalEntryPoint.class) {
            if (this.fullScreenBanner == null) {
                this.fullScreenBanner = new FullScreenBanner(context);
            } else if (this.fullScreenBanner.getContext() == null) {
                this.fullScreenBanner.setContext(context);
            }
            fullScreenBanner = this.fullScreenBanner;
        }
        return fullScreenBanner;
    }

    public boolean pullEnabled() {
        return this.enablePull;
    }

    public void enablePull() {
        this.enablePull = true;
    }

    void setAdView(AdView adView) {
        this.adView = adView;
    }

    AdView getAdView() {
        return this.adView;
    }

    private boolean checkPermissions(Activity activity) {
        if (activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && activity.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            return true;
        }
        ExceptionHandler.handleException(new Exception("Invalid permission. You have to grant ACCESS_NETWORK_STATE and INTERNET permissions to work properly"));
        return false;
    }

    void onPause(Context context) {
        String activityClassName = "";
        if (context instanceof Activity) {
            activityClassName = ((Activity) context).getLocalClassName();
        }
        if (this.adView != null && activityClassName.equals(this.adView.getActivity().getLocalClassName())) {
            this.adView.onPause();
        }
        if (this.networkReceiver != null) {
            try {
                context.unregisterReceiver(this.networkReceiver);
            } catch (IllegalArgumentException e) {
            }
        }
        if (this.rewardedAdHelper != null) {
            this.rewardedAdHelper.onPause();
        }
        if (this.fullScreenBanner != null) {
            this.fullScreenBanner.setActivityInForeground(false);
        }
        if (this.fullscreenSdkHelper != null) {
            this.fullscreenSdkHelper.onPause();
        }
        if (this.fastTrackAdsController != null) {
            this.fastTrackAdsController.onPause();
        }
        INSTANCE.setApplicationVisible(false);
    }

    void onResume(Context context) {
        String activityClassName = "";
        if (context instanceof Activity) {
            activityClassName = ((Activity) context).getLocalClassName();
        }
        if (this.adView != null && activityClassName.equals(this.adView.getActivity().getLocalClassName())) {
            this.adView.onResume();
        }
        this.configuration = Configuration.getInstance(context);
        PausedContentInfoActivity.checkBanApp(context);
        if (this.networkReceiver == null) {
            this.networkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(context);
            this.networkAvailableListener = this.networkReceiver.createNetworkAvailableListener(context);
            this.networkReceiver.addListener(this.networkAvailableListener);
        } else {
            context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (this.fullScreenBanner != null) {
            this.fullScreenBanner.setContext(context);
            this.fullScreenBanner.setActivityInForeground(true);
        }
        if (this.rewardedAdHelper != null) {
            this.rewardedAdHelper.setContext(context);
            this.rewardedAdHelper.onResume();
        }
        if (this.fullscreenSdkHelper != null) {
            this.fullscreenSdkHelper.onResume();
        }
        if (this.isOnResumeFSEnabled && this.fullScreenBanner != null && (context instanceof Activity) && !isFSWasAlreadyShown((Activity) context)) {
            this.wasFSShownInOnResume.put(((Activity) context).getClass().getCanonicalName(), Boolean.valueOf(true));
            this.fullScreenBanner.load(BannerLoadTags.ON_RESUME);
        }
        if (this.fastTrackAdsController != null) {
            this.fastTrackAdsController.onResume(context);
        }
        INSTANCE.setApplicationVisible(true);
    }

    private boolean isFSWasAlreadyShown(Activity activity) {
        String key = activity.getClass().getCanonicalName();
        if (this.wasFSShownInOnResume.containsKey(key)) {
            return ((Boolean) this.wasFSShownInOnResume.get(key)).booleanValue();
        }
        return false;
    }

    public void onDeviceIdParametersObtained(final Context context, DeviceIdParameters result) {
        this.idParameters = result;
        AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new ConfigPhpRequestListener() {
            public void receivedConfigPhp(ConfigPhp configPhp) {
                InternalEntryPoint.this.fullScreenBanner.initWithDeviceIdParameters(InternalEntryPoint.this.idParameters);
                if (configPhp != null) {
                    InternalEntryPoint.this.advertisingTermsDialog.show(configPhp.isAdvertisingTermsDialog());
                    if (InternalEntryPoint.this.saveDialogEnableListener) {
                        InternalEntryPoint.this.aboutDialogEnabledListener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    }
                    if (InternalEntryPoint.this.rewardedAdHelper == null) {
                        InternalEntryPoint.this.rewardedAdHelper = new RewardedAdHelper(configPhp, context);
                    }
                    if (InternalEntryPoint.this.fullscreenSdkHelper == null) {
                        InternalEntryPoint.this.fullscreenSdkHelper = new FullscreenSdkHelper(configPhp, context);
                    }
                    Constants.setFullScreenDelay(configPhp.getFullScreenDelay());
                    InternalEntryPoint.this.fullScreenBanner.setTimesToShow(configPhp.getFullscreenBannerCountToShow());
                    InternalEntryPoint.this.fullScreenBanner.setOnTouchFSEnabled(configPhp.isOnTouchFSEnabled());
                    if (configPhp.isTakeOffFSEnabled()) {
                        InternalEntryPoint.this.fullScreenBanner.load(BannerLoadTags.ON_TAKE_OFF);
                    }
                    InternalEntryPoint.this.isOnResumeFSEnabled = configPhp.isOnResumeFSEnabled();
                    if (!InternalEntryPoint.this.fastTrackAdsController.isActive()) {
                        InternalEntryPoint.this.fastTrackAdsController.requestInit(configPhp, context);
                    }
                }
            }
        });
    }

    void showAboutDialog(Activity activity) {
        final Intent intent = new Intent(activity, AboutDialogActivity.class);
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(activity, this.idParameters, new ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    intent.putExtra(InternalEntryPoint.CONFIG_PHP_KEY, configPhp);
                }
            });
        } else {
            String lastResponse = Configuration.getInstance(activity).getSettingsCoder().getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
            if (TextUtils.isEmpty(lastResponse)) {
                intent.putExtra(CONFIG_PHP_KEY, "");
            } else {
                try {
                    intent.putExtra(CONFIG_PHP_KEY, ConfigPhp.parseFromJson(lastResponse));
                } catch (JsonSyntaxException e) {
                }
            }
        }
        activity.startActivity(intent);
    }

    void getNewConfigPhp(Context context, final OnAboutDialogEnableListener listener) {
        this.aboutDialogEnabledListener = listener;
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    listener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    InternalEntryPoint.this.saveDialogEnableListener = false;
                }
            });
            return;
        }
        String lastResponse = Configuration.getInstance(context).getSettingsCoder().getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
        if (TextUtils.isEmpty(lastResponse)) {
            this.saveDialogEnableListener = true;
            return;
        }
        try {
            listener.onDialogEnableReceived(ConfigPhp.parseFromJson(lastResponse).isAboutScreenEnabled());
            this.saveDialogEnableListener = false;
        } catch (JsonSyntaxException e) {
            this.saveDialogEnableListener = true;
        }
    }

    public void setAdditionalJsCode(String jsCode) {
        if (this.jsLoader != null) {
            this.jsLoader.onJsLoaded(jsCode);
        }
    }

    void getAdditionalJsCode(OnAdditionalJsLoaded loader) {
        this.jsLoader = loader;
    }

    public void addNetworkListener(OnNetworkStateChangedListener networkListener, Context context) {
        if (this.networkReceiver != null) {
            this.networkReceiver.addListener(networkListener);
            return;
        }
        this.networkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(context);
        this.networkReceiver.addListener(networkListener);
    }

    public void removeNetworkListener(OnNetworkStateChangedListener networkListener) {
        if (this.networkReceiver != null) {
            this.networkReceiver.removeListener(networkListener);
        }
    }

    void checkIsOfferWallEnabled(Context context, final OfferWallEnabledListener listener) {
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    listener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                }
            });
        } else {
            DeviceIdParser.getInstance().rescan(context, new IDeviceIdParserListener() {

                class C11911 implements ConfigPhpRequestListener {
                    C11911() {
                    }

                    public void receivedConfigPhp(ConfigPhp configPhp) {
                        listener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                    }
                }

                public void onDeviceIdParametersObtained(Context context, DeviceIdParameters result) {
                    InternalEntryPoint.this.idParameters = result;
                    AppsgeyserServerClient.getInstance().getConfigPhp(context, result, new C11911());
                    InternalEntryPoint.this.fullScreenBanner.initWithDeviceIdParameters(InternalEntryPoint.this.idParameters);
                }
            });
        }
    }

    RewardedAdHelper getRewardedAdHelper() {
        return this.rewardedAdHelper;
    }

    public void onDestroy(Context context) {
        if (this.rewardedAdHelper != null) {
            this.rewardedAdHelper.onDestroy();
        }
        if (this.fastTrackAdsController != null) {
            this.fastTrackAdsController.onDestroy();
        }
    }

    public FullscreenSdkHelper getFullscreenSdkHelper() {
        return this.fullscreenSdkHelper;
    }

    FastTrackAdsController getFastTrackAdsController() {
        return this.fastTrackAdsController;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public boolean isApplicationVisible() {
        return this.isApplicationVisible;
    }

    public void setApplicationVisible(boolean applicationVisible) {
        this.isApplicationVisible = applicationVisible;
    }

    public boolean isConsentRequestProcessActive() {
        return this.isConsentRequestProcessActive;
    }

    public void setConsentRequestProcessActive(boolean consentRequestProcessActive) {
        this.isConsentRequestProcessActive = consentRequestProcessActive;
    }
}
