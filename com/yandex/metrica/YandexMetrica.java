package com.yandex.metrica;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.bo;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.Map;

public final class YandexMetrica {
    private YandexMetrica() {
    }

    public static void activate(Context context, String apiKey) {
        bo.m15012a(context, C4295e.m14408a(apiKey).m14400b());
    }

    public static void activate(Context context, YandexMetricaConfig config) {
        bo.m15012a(context, C4295e.m14409a(config));
    }

    public static void onResumeActivity(Activity activity) {
        bo.m15020c().m16326b(activity);
    }

    public static void onPauseActivity(Activity activity) {
        bo.m15020c().m16329c(activity);
    }

    public static void enableActivityAutoTracking(Application application) {
        bo.m15020c().m16319a(application);
    }

    public static void reportEvent(String eventName) {
        bo.m15020c().reportEvent(eventName);
    }

    public static void reportError(String message, Throwable error) {
        bo.m15020c().reportError(message, error);
    }

    public static void reportUnhandledException(Throwable exception) {
        bo.m15020c().reportUnhandledException(exception);
    }

    public static void reportNativeCrash(String nativeCrash) {
        bo.m15020c().m14471d(nativeCrash);
    }

    public static void reportEvent(String eventName, String jsonValue) {
        bo.m15020c().reportEvent(eventName, jsonValue);
    }

    public static void reportEvent(String eventName, Map<String, Object> attributes) {
        bo.m15020c().reportEvent(eventName, (Map) attributes);
    }

    public static void reportAppOpen(Activity activity) {
        bo.m15020c().m16318a(activity);
    }

    public static void reportAppOpen(String deeplink) {
        bo.m15020c().m16333e(deeplink);
    }

    public static void setSessionTimeout(int sessionTimeout) {
        bo.m15010a(sessionTimeout);
    }

    public static void setReportCrashesEnabled(boolean enabled) {
        bo.m15015a(enabled);
    }

    public static void setReportNativeCrashesEnabled(boolean enabled) {
        bo.m15019b(enabled);
    }

    public static void setLocation(Location location) {
        bo.m15013a(location);
    }

    public static void setTrackLocationEnabled(boolean enabled) {
        bo.m15022c(enabled);
    }

    public static void setCustomAppVersion(String appVersion) {
        bo.m15021c(appVersion);
    }

    public static void setLogEnabled() {
        C4529j.m16280f().m16240a();
    }

    public static void setCollectInstalledApps(boolean collect) {
        bo.m15023d(collect);
    }

    public static IReporter getReporter(Context context, String apiKey) {
        bk.m14982a(apiKey);
        bo.m15011a(context);
        return bo.m15016b().m15027a(apiKey);
    }

    public static void setEnvironmentValue(String key, String value) {
        bo.m15014a(key, value);
    }

    public static String getLibraryVersion() {
        return "2.73";
    }

    public static int getLibraryApiLevel() {
        return 58;
    }

    public static boolean isCollectInstalledApps() {
        return bo.m15025e();
    }

    public static void registerReferrerBroadcastReceivers(BroadcastReceiver... anotherReferrerReceivers) {
        MetricaEventHandler.m14294a(anotherReferrerReceivers);
    }

    public static void requestDeferredDeeplinkParameters(DeferredDeeplinkParametersListener listener) {
        bo.m15016b().m15030a(listener);
    }
}
