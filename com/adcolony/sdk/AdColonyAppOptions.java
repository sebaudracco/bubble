package com.adcolony.sdk;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyAppOptions {
    public static final String ADMARVEL = "AdMarvel";
    public static final String ADMOB = "AdMob";
    public static final String ADOBEAIR = "Adobe AIR";
    public static final String AERSERVE = "AerServe";
    public static final int ALL = 2;
    public static final String APPODEAL = "Appodeal";
    public static final String COCOS2DX = "Cocos2d-x";
    public static final String CORONA = "Corona";
    public static final String FUSEPOWERED = "Fuse Powered";
    public static final String FYBER = "Fyber";
    public static final String IRONSOURCE = "ironSource";
    public static final int LANDSCAPE = 1;
    public static final String MOPUB = "MoPub";
    public static final int PORTRAIT = 0;
    @Deprecated
    public static final int SENSOR = 2;
    public static final String UNITY = "Unity";
    String f391a = "";
    String[] f392b;
    JSONArray f393c = C0802y.m1469b();
    JSONObject f394d = C0802y.m1453a();
    AdColonyUserMetadata f395e;

    public AdColonyAppOptions() {
        setOriginStore("google");
        if (C0594a.m612b()) {
            C0740l a = C0594a.m605a();
            if (a.m1275e()) {
                m549a(a.m1271d().f391a);
                m550a(a.m1271d().f392b);
            }
        }
    }

    public AdColonyAppOptions setAppVersion(@NonNull String app_version) {
        if (az.m894d(app_version)) {
            setOption("app_version", app_version);
        }
        return this;
    }

    public String getAppVersion() {
        return C0802y.m1468b(this.f394d, "app_version");
    }

    public AdColonyAppOptions setUserID(@NonNull String user_id) {
        if (az.m894d(user_id)) {
            setOption("user_id", user_id);
        }
        return this;
    }

    public String getUserID() {
        return C0802y.m1468b(this.f394d, "user_id");
    }

    public AdColonyAppOptions setOption(@NonNull String key, boolean value) {
        if (az.m894d(key)) {
            C0802y.m1465a(this.f394d, key, value);
        }
        return this;
    }

    public Object getOption(@NonNull String key) {
        return C0802y.m1450a(this.f394d, key);
    }

    public AdColonyAppOptions setOption(@NonNull String key, double value) {
        if (az.m894d(key)) {
            C0802y.m1460a(this.f394d, key, value);
        }
        return this;
    }

    public AdColonyAppOptions setOption(@NonNull String key, @NonNull String value) {
        if (key != null && az.m894d(key) && az.m894d(value)) {
            C0802y.m1462a(this.f394d, key, value);
        }
        return this;
    }

    public AdColonyAppOptions setOriginStore(@NonNull String origin_store) {
        if (az.m894d(origin_store)) {
            setOption("origin_store", origin_store);
        }
        return this;
    }

    public String getOriginStore() {
        return C0802y.m1468b(this.f394d, "origin_store");
    }

    public AdColonyAppOptions setRequestedAdOrientation(@IntRange(from = 0, to = 2) int orientation) {
        setOption("orientation", (double) orientation);
        return this;
    }

    public int getRequestedAdOrientation() {
        return C0802y.m1449a(this.f394d, "orientation", -1);
    }

    public AdColonyAppOptions setAppOrientation(@IntRange(from = 0, to = 2) int orientation) {
        setOption("app_orientation", (double) orientation);
        return this;
    }

    public int getAppOrientation() {
        return C0802y.m1449a(this.f394d, "app_orientation", -1);
    }

    public AdColonyAppOptions setUserMetadata(@NonNull AdColonyUserMetadata metadata) {
        this.f395e = metadata;
        C0802y.m1464a(this.f394d, "user_metadata", metadata.f449c);
        return this;
    }

    public AdColonyAppOptions setTestModeEnabled(boolean enabled) {
        C0802y.m1465a(this.f394d, "test_mode", enabled);
        return this;
    }

    public boolean getTestModeEnabled() {
        return C0802y.m1477d(this.f394d, "test_mode");
    }

    public AdColonyAppOptions setMultiWindowEnabled(boolean enabled) {
        C0802y.m1465a(this.f394d, "multi_window_enabled", enabled);
        return this;
    }

    public boolean getMultiWindowEnabled() {
        return C0802y.m1477d(this.f394d, "multi_window_enabled");
    }

    public AdColonyUserMetadata getUserMetadata() {
        return this.f395e;
    }

    public AdColonyAppOptions setMediationNetwork(@NonNull String name, @NonNull String version) {
        if (az.m894d(name) && az.m894d(version)) {
            C0802y.m1462a(this.f394d, "mediation_network", name);
            C0802y.m1462a(this.f394d, "mediation_network_version", version);
        }
        return this;
    }

    public JSONObject getMediationInfo() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "name", C0802y.m1468b(this.f394d, "mediation_network"));
        C0802y.m1462a(a, "version", C0802y.m1468b(this.f394d, "mediation_network_version"));
        return a;
    }

    public AdColonyAppOptions setPlugin(@NonNull String name, @NonNull String version) {
        if (az.m894d(name) && az.m894d(version)) {
            C0802y.m1462a(this.f394d, "plugin", name);
            C0802y.m1462a(this.f394d, "plugin_version", version);
        }
        return this;
    }

    public JSONObject getPluginInfo() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "name", C0802y.m1468b(this.f394d, "plugin"));
        C0802y.m1462a(a, "version", C0802y.m1468b(this.f394d, "plugin_version"));
        return a;
    }

    public AdColonyAppOptions setKeepScreenOn(boolean keepScreenOn) {
        C0802y.m1465a(this.f394d, "keep_screen_on", keepScreenOn);
        return this;
    }

    public boolean getKeepScreenOn() {
        return C0802y.m1477d(this.f394d, "keep_screen_on");
    }

    public static AdColonyAppOptions getMoPubAppOptions(@NonNull String clientOptions) {
        String str = "AdColonyMoPub";
        AdColonyAppOptions mediationNetwork = new AdColonyAppOptions().setMediationNetwork(MOPUB, "1.0");
        if (clientOptions == null || clientOptions.isEmpty()) {
            return mediationNetwork;
        }
        String[] split = clientOptions.split(",");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String[] split2 = split[i].split(":");
            if (split2.length == 2) {
                String str2 = split2[0];
                int i2 = -1;
                switch (str2.hashCode()) {
                    case 109770977:
                        if (str2.equals("store")) {
                            i2 = 0;
                            break;
                        }
                        break;
                    case 351608024:
                        if (str2.equals("version")) {
                            i2 = 1;
                            break;
                        }
                        break;
                }
                switch (i2) {
                    case 0:
                        mediationNetwork.setOriginStore(split2[1]);
                        break;
                    case 1:
                        mediationNetwork.setAppVersion(split2[1]);
                        break;
                    default:
                        Log.e(str, "AdColony client options in wrong format - please check your MoPub dashboard");
                        return mediationNetwork;
                }
                i++;
            } else {
                Log.e(str, "AdColony client options not recognized - please check your MoPub dashboard");
                return null;
            }
        }
        return mediationNetwork;
    }

    AdColonyAppOptions m549a(String str) {
        if (str != null) {
            this.f391a = str;
            C0802y.m1462a(this.f394d, "app_id", str);
        }
        return this;
    }

    AdColonyAppOptions m550a(String... strArr) {
        if (strArr != null) {
            this.f392b = strArr;
            this.f393c = C0802y.m1469b();
            for (String a : strArr) {
                C0802y.m1458a(this.f393c, a);
            }
        }
        return this;
    }

    String m551a() {
        return this.f391a;
    }

    String[] m552b() {
        return this.f392b;
    }

    JSONArray m553c() {
        return this.f393c;
    }

    JSONObject m554d() {
        return this.f394d;
    }

    void m555e() {
        if (C0802y.m1483i(this.f394d, "use_forced_controller")) {
            ADCVMModule.f338a = C0802y.m1477d(this.f394d, "use_forced_controller");
        }
        if (C0802y.m1483i(this.f394d, "use_staging_launch_server")) {
            C0740l.f1269f = C0802y.m1477d(this.f394d, "use_staging_launch_server") ? "https://adc3-launch-server-staging.herokuapp.com/v4/launch" : "https://adc3-launch.adcolony.com/v4/launch";
        }
    }
}
