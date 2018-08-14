package com.appsgeyser.sdk.server;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.utils.DeviceInfoGetter;
import com.yandex.metrica.YandexMetrica;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class StatController {
    public static final String FT_NETWORK_ADMOB = "ft_admob";
    public static final String FT_NETWORK_ADMOB_CUSTOM = "ft_admob_custom";
    public static final String FT_NETWORK_FYBER = "ft_fyber";
    public static final String FT_NETWORK_FYBER_CUSTOM = "ft_fyber_custom";
    public static final String FT_NETWORK_MOPUB = "ft_mopub";
    public static final String FT_NETWORK_MOPUB_CUSTOM = "ft_mopub_custom";
    public static final String KEY_ABOUT_DIALOG_IMPRESSION = "about_dialog_impression";
    public static final String KEY_ABOUT_DIALOG_VISIT_SITE = "about_dialog_visit_site";
    public static final String KEY_ADCOLONY_INTERSTITIAL_CLICK_URL = "adcolony_interstitial_click_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_IMPRESSION_URL = "adcolony_interstitial_impression_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_NO_FILL_URL = "adcolony_interstitial_no_fill_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_REQUEST_URL = "adcolony_interstitial_request_url";
    public static final String KEY_ADMOB = "admobSdk";
    public static final String KEY_ADMOB_INTERSTITIAL_SDK_CLICK = "admob_interstitial_sdk_click";
    public static final String KEY_ADMOB_INTERSTITIAL_SDK_IMPRESSION = "admob_interstitial_sdk_impression";
    public static final String KEY_ADMOB_NATIVE_SDK_CLICK = "admob_native_sdk_click";
    public static final String KEY_ADMOB_NATIVE_SDK_IMPRESSION = "admob_native_sdk_impression";
    public static final String KEY_ADMOB_REWARDED_SDK_CLICK = "admob_rewarded_sdk_click";
    public static final String KEY_ADMOB_REWARDED_SDK_IMPRESSION = "admob_rewarded_sdk_impression";
    public static final String KEY_ADTAPSY_INTERSTITIAL_CLICK_URL = "adtapsy_interstitial_click_url";
    public static final String KEY_ADTAPSY_INTERSTITIAL_IMPRESSION_URL = "adtapsy_interstitial_impression_url";
    public static final String KEY_APPNEXT = "appnextSdk";
    public static final String KEY_APPNEXT_NATIVE_SDK_CLICK = "appnext_native_sdk_click";
    public static final String KEY_APPNEXT_NATIVE_SDK_IMPRESSION = "appnext_native_sdk_impression";
    public static final String KEY_APPNEXT_REWARDED_SDK_CLICK = "appnext_rewarded_sdk_click";
    public static final String KEY_APPNEXT_REWARDED_SDK_IMPRESSION = "appnext_rewarded_sdk_impression";
    public static final String KEY_APPSGEYSER = "appsgeyserSdk";
    public static final String KEY_APPSGEYSER_NATIVE_SDK_CLICK = "appsgeyser_native_sdk_click";
    public static final String KEY_APPSGEYSER_NATIVE_SDK_IMPRESSION = "appsgeyser_native_sdk_impression";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_CLICK_URL = "chartboost_interstitial_click_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_IMPRESSION_URL = "chartboost_interstitial_impression_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_NO_FILL_URL = "chartboost_interstitial_no_fill_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_REQUEST_URL = "chartboost_interstitial_request_url";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_ACCESS_COARSE_LOCATION = "click_accept_permission_access_coarse_location";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_ACCESS_FINE_LOCATION = "click_accept_permission_access_fine_location";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_GET_ACCOUNTS = "click_accept_permission_get_accounts";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_READ_EXTERNAL_STORAGE = "click_accept_permission_read_external_storage";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_READ_PHONE_STATE = "click_accept_permission_read_phone_state";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_WRITE_EXTERNAL_STORAGE = "click_accept_permission_write_external_storage";
    public static final String KEY_CLICK_ACCEPT_SDK_DIALOG = "click_accept_sdk_dialog";
    public static final String KEY_CLICK_APP_ALREADY_INSTALLED = "click_app_already_installed";
    public static final String KEY_CLICK_BANNER_BACK_KEY_PRESSED = "click_banner_back_key_pressed";
    public static final String KEY_CLICK_BROWSER_BACK_KEY_PRESSED = "click_browser_back_key_pressed";
    public static final String KEY_CLICK_CAN_NOT_START_DOWNLOAD = "click_can_not_start_download";
    public static final String KEY_CLICK_CROSS_BANNER = "click_cross_banner";
    public static final String KEY_CLICK_CROSS_MINI_BROWSER = "click_cross_mini_browser";
    public static final String KEY_CLICK_DATA_TEXT_HTML_LOADED_IN_BANNER = "click_data_text_html_loaded_in_banner";
    public static final String KEY_CLICK_DECLINE_PERMISSION_ACCESS_COARSE_LOCATION = "click_decline_permission_access_coarse_location";
    public static final String KEY_CLICK_DECLINE_PERMISSION_ACCESS_FINE_LOCATION = "click_decline_permission_access_fine_location";
    public static final String KEY_CLICK_DECLINE_PERMISSION_GET_ACCOUNTS = "click_decline_permission_get_accounts";
    public static final String KEY_CLICK_DECLINE_PERMISSION_READ_EXTERNAL_STORAGE = "click_decline_permission_read_external_storage";
    public static final String KEY_CLICK_DECLINE_PERMISSION_READ_PHONE_STATE = "click_decline_permission_read_phone_state";
    public static final String KEY_CLICK_DECLINE_PERMISSION_WRITE_EXTERNAL_STORAGE = "click_decline_permission_write_external_storage";
    public static final String KEY_CLICK_DECLINE_SDK_DIALOG = "click_decline_sdk_dialog";
    public static final String KEY_CLICK_EXTERNAL_BROWSER = "click_external_browser";
    public static final String KEY_CLICK_FINISH_DOWNLOAD = "click_finish_download";
    public static final String KEY_CLICK_FINISH_EMPTY_HTML = "click_finish_epmty_html";
    public static final String KEY_CLICK_FINISH_HTML = "click_finish_html";
    public static final String KEY_CLICK_FINISH_MARKET = "click_finish_market";
    public static final String KEY_CLICK_HTML_TAP_START = "click_html_tap_start";
    public static final String KEY_CLICK_LOADING_ERROR = "click_loading_error";
    public static final String KEY_CLICK_NO_MARKET_ON_DEVICE = "click_no_market_on_device";
    public static final String KEY_CLICK_REDIRECT_START = "click_redirect_start";
    public static final String KEY_CLICK_TIMER_BANNER = "click_timer_banner";
    public static final String KEY_CLICK_WEBWIEW_TAP = "click_webview_tap";
    public static final String KEY_FT_BANNER_SDK_CLICK = "ft_banner_sdk_click";
    public static final String KEY_FT_BANNER_SDK_ERROR = "ft_banner_sdk_error";
    public static final String KEY_FT_BANNER_SDK_IMPRESSION = "ft_banner_sdk_impression";
    public static final String KEY_FT_BANNER_SDK_NOFILL = "ft_banner_sdk_nofill";
    public static final String KEY_FT_BANNER_SDK_REQUEST = "ft_banner_sdk_request";
    public static final String KEY_FT_INTERSTITIAL_SDK_CLICK = "ft_interstitial_sdk_click";
    public static final String KEY_FT_INTERSTITIAL_SDK_ERROR = "ft_interstitial_sdk_error";
    public static final String KEY_FT_INTERSTITIAL_SDK_IMPRESSION = "ft_interstitial_sdk_impression";
    public static final String KEY_FT_INTERSTITIAL_SDK_NOFILL = "ft_interstitial_sdk_nofill";
    public static final String KEY_FT_INTERSTITIAL_SDK_REQUEST = "ft_interstitial_sdk_request";
    public static final String KEY_FT_REWARDED_SDK_CLICK = "ft_rewarded_sdk_click";
    public static final String KEY_FT_REWARDED_SDK_COMPLETION = "ft_rewarded_sdk_completion";
    public static final String KEY_FT_REWARDED_SDK_ERROR = "ft_rewarded_sdk_error";
    public static final String KEY_FT_REWARDED_SDK_IMPRESSION = "ft_rewarded_sdk_impression";
    public static final String KEY_FT_REWARDED_SDK_NOFILL = "ft_rewarded_sdk_nofill";
    public static final String KEY_FT_REWARDED_SDK_REQUEST = "ft_rewarded_sdk_request";
    public static final String KEY_FYBER = "fyberSdk";
    public static final String KEY_FYBER_NATIVE_SDK_CLICK = "fyber_native_sdk_click";
    public static final String KEY_FYBER_NATIVE_SDK_IMPRESSION = "fyber_native_sdk_impression";
    public static final String KEY_FYBER_REWARDED_SDK_CLICK = "fyber_rewarded_sdk_click";
    public static final String KEY_FYBER_REWARDED_SDK_IMPRESSION = "fyber_rewarded_sdk_impression";
    public static final String KEY_GET_PARAM_DETAILS = "details";
    public static final String KEY_GET_PARAM_URL = "url";
    public static final String KEY_INIT_ERROR = "init_error";
    public static final String KEY_INMOBI = "inmobiSdk";
    public static final String KEY_INMOBI_INTERSTITIAL_CLICK_URL = "inmobi_interstitial_click_url";
    public static final String KEY_INMOBI_INTERSTITIAL_FAIL_URL = "inmobi_interstitial_fail_url";
    public static final String KEY_INMOBI_INTERSTITIAL_IMPRESSION_URL = "inmobi_interstitial_impression_url";
    public static final String KEY_INMOBI_INTERSTITIAL_NO_FILL_URL = "inmobi_interstitial_no_fill_url";
    public static final String KEY_INMOBI_INTERSTITIAL_REQUEST_URL = "inmobi_interstitial_request_url";
    public static final String KEY_INMOBI_INTERSTITIAL_SUCCEEDED_URL = "inmobi_interstitial_succeeded_url";
    public static final String KEY_INMOBI_NATIVE_SDK_CLICK = "inmobi_native_sdk_click";
    public static final String KEY_INMOBI_NATIVE_SDK_IMPRESSION = "inmobi_native_sdk_impression";
    public static final String KEY_INMOBI_REWARDED_SDK_CLICK = "inmobi_rewarded_sdk_click";
    public static final String KEY_INMOBI_REWARDED_SDK_IMPRESSION = "inmobi_rewarded_sdk_impression";
    public static final String KEY_MOBFOX = "mobfoxSdk";
    public static final String KEY_MOBFOX_NATIVE_SDK_CLICK = "mobfox_native_sdk_click";
    public static final String KEY_MOBFOX_NATIVE_SDK_IMPRESSION = "mobfox_native_sdk_impression";
    public static final String KEY_MOBFOX_REWARDED_SDK_CLICK = "mobfox_rewarded_sdk_click";
    public static final String KEY_MOBFOX_REWARDED_SDK_IMPRESSION = "mobfox_rewarded_sdk_impression";
    public static final String KEY_MOPUB = "mopubSdk";
    public static final String KEY_MOPUB_INTERSTITIAL_SDK_CLICK = "mopub_interstitial_sdk_click";
    public static final String KEY_MOPUB_INTERSTITIAL_SDK_IMPESSION = "mopub_interstitial_sdk_impression";
    public static final String KEY_NATIVE_INTERSTITIAL_CLICK_URL = "native_interstitial_click_url";
    public static final String KEY_NATIVE_INTERSTITIAL_IMPRESSION_URL = "native_interstitial_impression_url";
    public static final String KEY_OFFERWALL_OPENED = "offer_wall_opened";
    public static final String KEY_SILVERMOB_INTERSTITIAL_CLICK_URL = "silvermob_interstitial_click_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_CLOSE_URL = "silvermob_interstitial_close_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_ERROR_URL = "silvermob_interstitial_error_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_IMPRESSION_URL = "silvermob_interstitial_impression_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_LOADED_URL = "silvermob_interstitial_loaded_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_NO_FILL_URL = "silvermob_interstitial_no_fill_url";
    public static final String KEY_SILVERMOB_INTERSTITIAL_REQUEST_URL = "silvermob_interstitial_request_url";
    public static final String KEY_SILVERMOB_VAST_CLICK_URL = "silvermob_vast_click_url";
    public static final String KEY_SILVERMOB_VAST_CLOSE_URL = "silvermob_vast_close_url";
    public static final String KEY_SILVERMOB_VAST_COMPLETE_URL = "silvermob_vast_complete_url";
    public static final String KEY_SILVERMOB_VAST_ERROR_URL = "silvermob_vast_error_url";
    public static final String KEY_SILVERMOB_VAST_IMPRESSION_URL = "silvermob_vast_impression_url";
    public static final String KEY_SILVERMOB_VAST_REQUEST_URL = "silvermob_vast_request_url";
    public static final String KEY_VUNGLE = "vungleSdk";
    public static final String KEY_VUNGLE_NATIVE_SDK_CLICK = "vungle_native_sdk_click";
    public static final String KEY_VUNGLE_NATIVE_SDK_IMPRESSION = "vungle_native_sdk_impression";
    public static final String KEY_VUNGLE_REWARDED_SDK_CLICK = "vungle_rewarded_sdk_click";
    public static final String KEY_VUNGLE_REWARDED_SDK_IMPRESSION = "vungle_rewarded_sdk_impression";
    private static final String LOG = "StatController";
    private static final String TEMPLATE_VERSION_URL_KEY = "templateversion";
    private static StatController instance;
    private HashMap<String, String> items;

    public enum AdsType {
        NATIVE,
        REWARDED,
        FULLSCREEN
    }

    private StatController() {
    }

    public static synchronized StatController getInstance() {
        StatController statController;
        synchronized (StatController.class) {
            if (instance == null) {
                instance = new StatController();
            }
            statController = instance;
        }
        return statController;
    }

    public void init(HashMap<String, String> values) {
        this.items = values;
    }

    public void sendRequestAsyncByKey(@NonNull String key) {
        sendRequestAsyncByKey(key, null, null, false);
    }

    public void sendRequestAsyncByKey(@NonNull String key, @Nullable final HashMap<String, String> details, @Nullable Context context, boolean shouldSendDeviceInfo) {
        if (!(!shouldSendDeviceInfo || context == null || details == null)) {
            HashMap<String, String> deviceInfo = DeviceInfoGetter.getDeviceInfoMap(context);
            if (deviceInfo != null) {
                details.putAll(deviceInfo);
            }
            String templateVersion = Configuration.getInstance(context).getTemplateVersion();
            if (templateVersion != null) {
                details.put(TEMPLATE_VERSION_URL_KEY, templateVersion);
            }
        }
        sendQueryToYandexMetrica(key, details);
        if (isInitialized()) {
            final String url = (String) this.items.get(key);
            if (url == null) {
                Log.d(LOG, "Stat url not set, skipping stat request on: " + key);
                return;
            } else {
                new Thread() {
                    public void run() {
                        String newUrl = url;
                        if (details != null) {
                            Builder builder = Uri.parse(url).buildUpon();
                            for (Entry<String, String> detail : details.entrySet()) {
                                builder.appendQueryParameter((String) detail.getKey(), (String) detail.getValue());
                            }
                            newUrl = builder.build().toString();
                        }
                        RequestQueueHolder.addUrl(newUrl);
                    }
                }.start();
                return;
            }
        }
        Log.d(LOG, "StatController not initialized, skipping stat request on: " + key);
    }

    private void sendQueryToYandexMetrica(String key, HashMap<String, String> queryParameters) {
        String jsonDetails = null;
        if (queryParameters != null) {
            try {
                jsonDetails = new JSONObject(queryParameters).toString();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        YandexMetrica.reportEvent(key, jsonDetails);
    }

    private boolean isInitialized() {
        return this.items != null;
    }

    public static HashMap<String, String> generateQueryParametersForSdk(ConfigPhp configPhp, @NonNull Context context, AdsType adsType, String details, String networkKey) {
        HashMap<String, String> queryParameters = new HashMap();
        Configuration configuration = Configuration.getInstance(context);
        queryParameters.put("wdid", configuration.getApplicationId());
        queryParameters.put("guid", configuration.getAppGuid());
        queryParameters.put("details", details);
        String uniqid = "";
        String bannerId = "";
        if (adsType == AdsType.NATIVE) {
            uniqid = ((AdNetworkSdkModel) configPhp.getAdsNetworkSdk().get(networkKey)).getUniqueId();
            bannerId = ((AdNetworkSdkModel) configPhp.getAdsNetworkSdk().get(networkKey)).getBannerId();
        } else if (adsType == AdsType.REWARDED) {
            uniqid = ((AdNetworkSdkModel) configPhp.getRewardedVideoSdk().get(networkKey)).getUniqueId();
            bannerId = ((AdNetworkSdkModel) configPhp.getRewardedVideoSdk().get(networkKey)).getBannerId();
        } else if (adsType == AdsType.FULLSCREEN) {
            uniqid = ((AdNetworkSdkModel) configPhp.getFullscreenSdk().get(networkKey)).getUniqueId();
            bannerId = ((AdNetworkSdkModel) configPhp.getFullscreenSdk().get(networkKey)).getBannerId();
        }
        queryParameters.put(BrowserActivity.KEY_UNIQ_ID, uniqid);
        queryParameters.put("bannerid", bannerId);
        return queryParameters;
    }
}
