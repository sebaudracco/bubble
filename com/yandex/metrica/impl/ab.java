package com.yandex.metrica.impl;

import android.location.Location;
import android.text.TextUtils;
import com.appnext.base.p023b.C1048i;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.YandexMetricaConfig;
import com.yandex.metrica.YandexMetricaConfig.Builder;
import com.yandex.metrica.impl.utils.C4525g;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class ab {
    public String m14478a(YandexMetricaConfig yandexMetricaConfig) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apikey", yandexMetricaConfig.getApiKey());
            jSONObject.put("app_version", yandexMetricaConfig.getAppVersion());
            jSONObject.put("session_timeout", yandexMetricaConfig.getSessionTimeout());
            jSONObject.put("error_env", C4525g.m16271a(yandexMetricaConfig.getErrorEnvironment()));
            jSONObject.put("location", m14473a(yandexMetricaConfig.getLocation()));
            jSONObject.put("preload_info", m14474a(yandexMetricaConfig.getPreloadInfo()));
            jSONObject.put("collect_apps", yandexMetricaConfig.isCollectInstalledApps());
            jSONObject.put("logs", yandexMetricaConfig.isLogEnabled());
            jSONObject.put("crash_enabled", yandexMetricaConfig.isReportCrashEnabled());
            jSONObject.put("crash_native_enabled", yandexMetricaConfig.isReportNativeCrashEnabled());
            jSONObject.put("location_enabled", yandexMetricaConfig.isTrackLocationEnabled());
            return jSONObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    public YandexMetricaConfig m14477a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Builder newConfigBuilder = YandexMetricaConfig.newConfigBuilder(jSONObject.getString("apikey"));
                if (jSONObject.has("app_version")) {
                    newConfigBuilder.setAppVersion(jSONObject.optString("app_version"));
                }
                if (jSONObject.has("session_timeout")) {
                    newConfigBuilder.setSessionTimeout(jSONObject.getInt("session_timeout"));
                }
                Map a = C4525g.m16272a(jSONObject.optString("error_env"));
                if (a != null && a.size() > 0) {
                    for (Entry entry : a.entrySet()) {
                        newConfigBuilder.putErrorEnvironmentValue((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                newConfigBuilder.setLocation(m14476c(jSONObject.optString("location")));
                newConfigBuilder.setPreloadInfo(m14475b(jSONObject.optString("preload_info")));
                if (jSONObject.has("collect_apps")) {
                    newConfigBuilder.setCollectInstalledApps(jSONObject.optBoolean("collect_apps"));
                }
                if (jSONObject.has("logs") && jSONObject.optBoolean("logs")) {
                    newConfigBuilder.setLogEnabled();
                }
                if (jSONObject.has("crash_enabled")) {
                    newConfigBuilder.setReportCrashesEnabled(jSONObject.optBoolean("crash_enabled"));
                }
                if (jSONObject.has("crash_native_enabled")) {
                    newConfigBuilder.setReportNativeCrashesEnabled(jSONObject.optBoolean("crash_native_enabled"));
                }
                if (jSONObject.has("location_enabled")) {
                    newConfigBuilder.setTrackLocationEnabled(jSONObject.optBoolean("location_enabled"));
                }
                return newConfigBuilder.build();
            } catch (JSONException e) {
            }
        }
        return null;
    }

    private static String m14474a(PreloadInfo preloadInfo) {
        String str = null;
        if (preloadInfo != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("trackid", preloadInfo.getTrackingId());
                jSONObject.put("params", C4525g.m16271a(preloadInfo.getAdditionalParams()));
                str = jSONObject.toString();
            } catch (JSONException e) {
            }
        }
        return str;
    }

    private static PreloadInfo m14475b(String str) throws JSONException {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("trackid")) {
            str2 = jSONObject.optString("trackid");
        }
        PreloadInfo.Builder newBuilder = PreloadInfo.newBuilder(str2);
        HashMap a = C4525g.m16272a(jSONObject.optString("params"));
        if (a != null && a.size() > 0) {
            for (Entry entry : a.entrySet()) {
                newBuilder.setAdditionalParams((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return newBuilder.build();
    }

    private static String m14473a(Location location) {
        String str = null;
        if (location != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("provider", location.getProvider());
                jSONObject.put("time", location.getTime());
                jSONObject.put("accuracy", (double) location.getAccuracy());
                jSONObject.put("alt", location.getAltitude());
                jSONObject.put("lng", location.getLongitude());
                jSONObject.put(C1048i.ko, location.getLatitude());
                str = jSONObject.toString();
            } catch (JSONException e) {
            }
        }
        return str;
    }

    private static Location m14476c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String optString;
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("provider")) {
                optString = jSONObject.optString("provider");
            } else {
                optString = null;
            }
            Location location = new Location(optString);
            location.setLongitude(jSONObject.getDouble("lng"));
            location.setLatitude(jSONObject.getDouble(C1048i.ko));
            location.setTime(jSONObject.optLong("time"));
            location.setAccuracy((float) jSONObject.optDouble("accuracy"));
            location.setAltitude((double) ((float) jSONObject.optDouble("alt")));
            return location;
        } catch (JSONException e) {
            return null;
        }
    }
}
