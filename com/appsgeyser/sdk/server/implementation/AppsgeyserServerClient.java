package com.appsgeyser.sdk.server.implementation;

import android.content.Context;
import android.net.Uri.Builder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.appsgeyser.sdk.ads.nativeAd.AppsgeyserNativeAdModel;
import com.appsgeyser.sdk.ads.nativeAd.AppsgeyserSDKNativeAdResponse;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.datasdk.DataSdkController;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.push.OneSignalCreator;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.server.network.NetworkManager.RequestType;
import com.appsgeyser.sdk.utils.DeviceInfoGetter;
import com.appsgeyser.sdk.utils.VersionManager;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.onesignal.OneSignal;
import com.onesignal.OneSignal.OSInFocusDisplayOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppsgeyserServerClient {
    private static final String INSTALL_ACTION_URL_TAG = "install";
    private static final String UPDATE_ACTION_URL_TAG = "update";
    private static final String USAGE_ACTION_URL_TAG = "usage";
    private ConfigPhp configPhpModel;
    List<ConfigPhpRequestListener> listenerList;
    private final NetworkManager networkManager;

    public interface ConfigPhpRequestListener {
        void receivedConfigPhp(ConfigPhp configPhp);
    }

    public interface AppsgeyserNativeAdsListener {
        void nativeAdsReceived(ArrayList<AppsgeyserNativeAdModel> arrayList);

        void onError(String str);
    }

    private static class SingletonHolder {
        static final AppsgeyserServerClient HOLDER_INSTANCE = new AppsgeyserServerClient();

        private SingletonHolder() {
        }
    }

    private AppsgeyserServerClient() {
        this.listenerList = new ArrayList();
        this.networkManager = NetworkManager.getInstance();
    }

    public static AppsgeyserServerClient getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private String getInstallerMarket(Context context) {
        String installerMarket;
        try {
            installerMarket = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            if (installerMarket == null) {
                return "";
            }
            return installerMarket;
        } catch (Exception e) {
            installerMarket = RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR;
            if (TextUtils.isEmpty(e.getMessage())) {
                return installerMarket;
            }
            return installerMarket + ":" + e.getMessage();
        }
    }

    public void sendAfterInstallInfo(@NonNull Context context) {
        sendRequestWithAllArgs(INSTALL_ACTION_URL_TAG, RequestType.AFTERINSTALL.ordinal(), context);
    }

    public void sendUsageInfo(@NonNull Context context) {
        sendRequestWithAllArgs(USAGE_ACTION_URL_TAG, RequestType.USAGE.ordinal(), context);
    }

    public void sendAboutDialogImpression(@NonNull Context context) {
        sendRequestSdkStatisticsWithArgs(StatController.KEY_ABOUT_DIALOG_IMPRESSION, StatController.KEY_ABOUT_DIALOG_IMPRESSION.hashCode(), context);
    }

    public void sendAboutDialogVisitSite(@NonNull Context context) {
        sendRequestSdkStatisticsWithArgs(StatController.KEY_ABOUT_DIALOG_VISIT_SITE, StatController.KEY_ABOUT_DIALOG_VISIT_SITE.hashCode(), context);
    }

    public void sendUpdateInfo(@NonNull Context context) {
        int currentVersion = VersionManager.getCurrentVersion(context);
        int previousVersion = VersionManager.getPreviousVersion(context);
        if (previousVersion == -1) {
            VersionManager.updateVersion(context, currentVersion);
        } else if (currentVersion > previousVersion) {
            VersionManager.updateVersion(context, currentVersion);
            sendRequestWithAllArgs(UPDATE_ACTION_URL_TAG, RequestType.UPDATE.ordinal(), context);
        }
    }

    public void getConfigPhp(@NonNull final Context context, @Nullable DeviceIdParameters result, @NonNull ConfigPhpRequestListener listener) {
        if (this.configPhpModel != null) {
            listener.receivedConfigPhp(this.configPhpModel);
        } else if (this.listenerList.size() > 0) {
            this.listenerList.add(listener);
        } else {
            Configuration configuration = Configuration.getInstance(context);
            this.listenerList.add(listener);
            String advId = "";
            if (result != null) {
                advId = result.getAdvId();
            }
            String deviceIdSection = "";
            if (!TextUtils.isEmpty(advId)) {
                deviceIdSection = "&advid=" + advId;
            }
            String requestURL = "http://config.appsgeyser.com/?widgetId=" + configuration.getApplicationId() + "&guid=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&market=" + getInstallerMarket(context) + deviceIdSection;
            final PreferencesCoder coder = new PreferencesCoder(context);
            coder.savePrefString(Constants.PREFS_CONFIG_PHP_URL, requestURL);
            this.networkManager.sendRequestAsync(requestURL, Integer.valueOf(RequestType.CONFIG_PHP.ordinal()), context, new OnRequestDoneListener() {
                public void onRequestDone(String requestUrl, int tag, String response) {
                    coder.savePrefString(Constants.PREFS_SERVER_RESPONSE, response);
                    try {
                        AppsgeyserServerClient.this.configPhpModel = ConfigPhp.parseFromJson(response);
                    } catch (JsonSyntaxException e) {
                        for (ConfigPhpRequestListener configPhpRequestListener : AppsgeyserServerClient.this.listenerList) {
                            AppsgeyserServerClient.this.onGetConfigErrorResponse(context, configPhpRequestListener, coder);
                        }
                    }
                    if (AppsgeyserServerClient.this.configPhpModel.getStatUrls() != null) {
                        StatController.getInstance().init(new HashMap(AppsgeyserServerClient.this.configPhpModel.getStatUrls()));
                    }
                    DataSdkController.startDataSdkController(context, AppsgeyserServerClient.this.configPhpModel);
                    for (ConfigPhpRequestListener configPhpRequestListener2 : AppsgeyserServerClient.this.listenerList) {
                        configPhpRequestListener2.receivedConfigPhp(AppsgeyserServerClient.this.configPhpModel);
                    }
                    AppsgeyserServerClient.this.listenerList.clear();
                    AppsgeyserServerClient.this.initPush(context, AppsgeyserServerClient.this.configPhpModel);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    for (ConfigPhpRequestListener configPhpRequestListener : AppsgeyserServerClient.this.listenerList) {
                        AppsgeyserServerClient.this.onGetConfigErrorResponse(context, configPhpRequestListener, coder);
                    }
                    AppsgeyserServerClient.this.listenerList.clear();
                }
            });
        }
    }

    public void sendClickInfo(@NonNull String reportUrl, @NonNull Context context) {
        this.networkManager.sendRequestAsync(reportUrl, Integer.valueOf(RequestType.CLICK.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(RequestType.CLICK.ordinal()), context));
    }

    public void sendImpression(@NonNull String reportUrl, @NonNull Context context) {
        this.networkManager.sendRequestAsync(reportUrl, Integer.valueOf(RequestType.IMPRESSION.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(RequestType.IMPRESSION.ordinal()), context));
    }

    public void sendApplicationMode(@NonNull Context context) {
        String requestURL = "http://ads.appsgeyser.com/checkstatus.php?wid=" + Configuration.getInstance(context).getApplicationId();
        this.networkManager.sendRequestAsync(requestURL, Integer.valueOf(RequestType.APPMODE.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(RequestType.APPMODE.ordinal()), context));
    }

    private void sendRequestWithAllArgs(@NonNull String action, int requestType, @NonNull Context context) {
        String requestURL;
        Configuration configuration = Configuration.getInstance(context);
        String advId = DeviceIdParser.getInstance().getDeviceIdParameters().getAdvId();
        if (TextUtils.isEmpty(advId)) {
            requestURL = "http://stat.appioapp.com/statistics.php?action=" + action + "&name=" + configuration.getApplicationId() + "&id=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android" + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + configuration.getTemplateVersion();
        } else {
            requestURL = "http://stat.appioapp.com/statistics.php?action=" + action + "&name=" + configuration.getApplicationId() + "&id=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android&advid=" + advId + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + configuration.getTemplateVersion();
        }
        this.networkManager.sendRequestAsync(requestURL, Integer.valueOf(requestType), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(requestType), context));
    }

    private void sendRequestSdkStatisticsWithArgs(@NonNull String action, int requestType, @NonNull Context context) {
        Configuration configuration = Configuration.getInstance(context);
        String requestURL = "http://stat.appsgeyser.com/sdk_statistics.php?action=" + action + "&wdid=" + configuration.getApplicationId() + "&guid=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android" + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + configuration.getTemplateVersion();
        this.networkManager.sendRequestAsync(requestURL, Integer.valueOf(requestType), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(requestType), context));
    }

    private void onGetConfigErrorResponse(Context context, ConfigPhpRequestListener listener, PreferencesCoder coder) {
        String lastResponse = coder.getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
        if (!lastResponse.equals("")) {
            try {
                this.configPhpModel = ConfigPhp.parseFromJson(lastResponse);
                listener.receivedConfigPhp(this.configPhpModel);
            } catch (JsonParseException e) {
                Log.d("JsonParseException", e.toString());
            }
        }
        DataSdkController.onGetConfigErrorResponse(context);
    }

    private void initPush(@NonNull Context context, @NonNull ConfigPhp configPhp) {
        String oneSignalAppId = configPhp.getOneSignalAppId();
        if (oneSignalAppId != null) {
            OneSignalCreator.init(oneSignalAppId, context.getApplicationContext());
        } else {
            OneSignal.startInit(context).inFocusDisplaying(OSInFocusDisplayOption.Notification).unsubscribeWhenNotificationsAreDisabled(true).init();
        }
    }

    public void requestNativeAds(Context context, int adsCount, final AppsgeyserNativeAdsListener appsgeyserNativeAdsListener) {
        Configuration configuration = Configuration.getInstance(context);
        Builder builder = new Builder();
        builder.scheme("http").authority("ads.appsgeyser.com").appendPath("get_native_ads").appendQueryParameter("count", String.valueOf(adsCount)).appendQueryParameter("widgetId", configuration.getApplicationId()).appendQueryParameter("guid", configuration.getAppGuid()).appendQueryParameter("v", Constants.PLATFORM_VERSION);
        this.networkManager.sendRequestAsync(builder.build().toString(), Integer.valueOf(RequestType.NATIVE_ADS.ordinal()), context, new OnRequestDoneListener() {
            public void onRequestDone(String requestUrl, int tag, String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        appsgeyserNativeAdsListener.nativeAdsReceived(new ArrayList(AppsgeyserSDKNativeAdResponse.parseFromJson(response).getAppsgeyserNativeAdModels()));
                    } catch (Exception e) {
                        appsgeyserNativeAdsListener.onError("can not parse response from ads");
                    }
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                appsgeyserNativeAdsListener.onError(volleyError.getMessage());
            }
        });
    }

    public void setConfigPhpModel(ConfigPhp configPhpModel) {
        this.configPhpModel = configPhpModel;
    }
}
