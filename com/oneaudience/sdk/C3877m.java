package com.oneaudience.sdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.NotificationCompat;
import com.moat.analytics.mobile.mpub.BuildConfig;
import com.mopub.common.Constants;
import com.oneaudience.sdk.p135c.C3834e;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class C3877m {

    public static final class C3876a {
        public static final String[] f9290a = new String[]{"email", "carrier_data", "browser_type_data"};
        public static final String[] f9291b = new String[]{BuildConfig.FLAVOR, NotificationCompat.CATEGORY_SOCIAL, "game_statistics_data"};
        public static final String[] f9292c = new String[]{"contacts", "installed_apps", "call_logs_data", "wifi_data", "bluetooth_data", "location_data", "cell_tower_data", "tv_input_data", "beacon_data", "current_apps_data"};
        public static final String[] f9293d = new String[]{"wifi_data", "bluetooth_data", "location_data", "beacon_data", "tv_input_data", Constants.VIDEO_TRACKING_EVENTS_KEY, "current_apps_data"};
    }

    public static Object m12380a(SharedPreferences sharedPreferences, String str, String str2) {
        try {
            return C3834e.m12259a(sharedPreferences.getString(str, str2));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList m12381a(SharedPreferences sharedPreferences, String str) {
        try {
            Object a = C3877m.m12380a(sharedPreferences, str, C3834e.m12260a(new ArrayList()));
            if (a instanceof ArrayList) {
                return (ArrayList) a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public static boolean m12382a(SharedPreferences sharedPreferences, String str, Serializable serializable) {
        Editor edit = sharedPreferences.edit();
        try {
            String a = C3834e.m12260a(serializable);
            if (a != null) {
                edit.putString(str, a);
                return edit.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
