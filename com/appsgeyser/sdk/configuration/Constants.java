package com.appsgeyser.sdk.configuration;

public class Constants {
    public static final String AD_SERVER_DOMAIN_URL = "http://ads.appsgeyser.com/";
    public static final String APP_METRICA_ID = "34e75064-5ba5-4fac-b092-dc10aa167be0";
    public static final String BANNER_POSITION = "TOP";
    public static final String CHECK_STATUS_URL = "http://ads.appsgeyser.com/checkstatus.php";
    public static final String CONFIG_DOMAIN_URL = "http://config.appsgeyser.com/";
    public static final String DOMAIN_URL = "http://www.appsgeyser.com";
    public static final int FULL_SCREEN_BANNER_DELAY = 1000;
    public static final boolean FULL_SCREEN_BANNER_ENABLED = false;
    public static final int GET_MORE_ADS_THRESHOLD = 1000;
    public static final int ITEMS_COUNT_IN_GRID = 6;
    public static final boolean LOG_DEBUG_ENABLED = true;
    public static final String LOG_DEBUG_TAG = "*** AppsgeyserSDK Debug";
    public static final boolean LOG_ERROR_ENABLED = true;
    public static final String LOG_ERROR_TAG = "AppsgeyserSDK";
    public static final String LOG_FS_BANNER_JS_ERRORS_TAG = "FSBannerJsError";
    public static final boolean LOG_INFO_ENABLED = true;
    public static final String LOG_INFO_TAG = "AppsgeyserSDK Info:";
    public static final String LOG_SMALL_BANNER_JS_ERRORS_TAG = "SmallBannerJsError";
    public static final String PAUSED_CONTENT_INFO_URL = "http://www.appsgeyser.com/paused/";
    public static final String PLATFORM_VERSION = "1.86.s";
    public static final String PREFS_APPSGEYSER_EULA_ACCEPTED = "appsgeyserSdk_eulaAccepted";
    public static final String PREFS_APPSGEYSER_SDK_ACTIVATED = "appsgeyserSdk_isActive";
    public static final String PREFS_AREAMETRICS_EULA_ACCEPTED = "areaMetricsSdk_eulaAccepted";
    public static final String PREFS_AREAMETRICS_SDK_ACTIVATED = "areaMetricsSdk_isActive";
    public static final String PREFS_BAN_APP = "Ban_App";
    public static final String PREFS_CONFIG_PHP_URL = "ConfigPhpURLWithParams";
    public static final String PREFS_CUEBIQ_SDK_ACTIVATED = "cuebiqSdk_isActive";
    public static final String PREFS_CUEBIQ_SDK_EULA_ACCEPTED = "cuebiqSdk_eulaAccepted";
    public static final String PREFS_ELEPHANT_DATA_SDK_ACTIVATED = "elephantDataSdk_isActive";
    public static final String PREFS_ELEPHANT_DATA_SDK_EULA_ACCEPTED = "elephantDataSdk_eulaAccepted";
    public static final String PREFS_INSTANT_COFFEE_EULA_ACCEPTED = "instantCoffeeSdk_eulaAccepted";
    public static final String PREFS_INSTANT_COFFEE_SDK_ACTIVATED = "instantCoffeeSdk_isActive";
    public static final String PREFS_MOBI_INFO_SDK_ACTIVATED = "mobiInfoSdk_isActive";
    public static final String PREFS_MOBI_INFO_SDK_EULA_ACCEPTED = "mobiInfoSdk_eulaAccepted";
    public static final String PREFS_ONE_AUDIENCE_SDK_ACTIVATED = "oneAudienceSdk_isActive";
    public static final String PREFS_ONE_AUDIENCE_SDK_EULA_ACCEPTED = "oneAudienceSdk_eulaAccepted";
    public static final String PREFS_PROFILER42MATTERS_DATA_EULA_ACCEPTED = "profiler42mattersSdk_eulaAccepted";
    public static final String PREFS_PROFILER42MATTERS_DATA_SDK_ACTIVATED = "profiler42mattersSdk_isActive";
    public static final String PREFS_REVEAL_EULA_ACCEPTED = "revealSdk_eulaAccepted";
    public static final String PREFS_REVEAL_SDK_ACTIVATED = "revealSdk_isActive";
    public static final String PREFS_SERVER_RESPONSE = "ServerResponse";
    public static final String PUBLISHER_NAME = "";
    public static final long PULL_ALARM_PERIOD = 14400000;
    public static final String PULL_DOMAIN_URL1 = "http://updapp.com/";
    public static final String PULL_DOMAIN_URL2 = "http://iccto.com/";
    public static final String PULL_DOMAIN_URL3 = "http://iccto.net/";
    public static final int REQUESTED_ADD_COUNT = 16;
    public static final String SDK_STATISTICS_URL = "http://stat.appsgeyser.com/sdk_statistics.php";
    public static final int SMALL_BANNER_DEFAULT_REFRESH_RATE = 10000;
    public static final String SPLASH_SERVER_DOMAIN_URL = "http://splash.appsgeyser.com/";
    public static final String SSL_ERROR_DIALOG_BUTTON_NEGATIVE = "Back to safety";
    public static final String SSL_ERROR_DIALOG_BUTTON_POSITIVE = "Proceed anyway";
    public static final String SSL_ERROR_DIALOG_MESSAGE = "The site's security certificate is not trusted!";
    public static final String SSL_ERROR_DIALOG_TITLE = "SSL connection error!";
    public static final String STATISTICS_URL = "http://stat.appioapp.com/statistics.php";
    public static final String STAT_DOMAIN_URL = "http://stat.appsgeyser.com/";
    private static long fullScreenDelay = -1;

    public class BannerLoadTags {
        public static final String ON_EXIT = "on_exit";
        public static final String ON_RESUME = "on_resume";
        public static final String ON_START = "on_start";
        public static final String ON_TAKE_OFF = "on_take_off";
        public static final String ON_TIMEOUT_PASSED = "on_timeout";
    }

    public static long getFullScreenDelay() {
        if (fullScreenDelay != -1) {
            return fullScreenDelay;
        }
        return 1000;
    }

    public static void setFullScreenDelay(long fullScreenDelay) {
        fullScreenDelay = fullScreenDelay;
    }
}
