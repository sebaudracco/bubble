package com.vungle.publisher.log;

import android.text.TextUtils;
import android.util.Log;

/* compiled from: vungle */
public class Logger {
    public static final String AD_TAG = "VungleAd";
    public static final String ASYNC_TAG = "VungleAsync";
    public static final String CONFIG_TAG = "VungleConfig";
    public static final String DATABASE_DUMP_TAG = "VungleDumpDatabase";
    public static final String DATABASE_TAG = "VungleDatabase";
    public static final String DATA_TAG = "VungleData";
    public static final String DEVICE_TAG = "VungleDevice";
    public static final String EVENT_TAG = "VungleEvent";
    public static final String FILE_TAG = "VungleFile";
    public static final String INJECT_TAG = "VungleInject";
    public static final String LOCATION_TAG = "VungleLocation";
    public static final String NETWORK_TAG = "VungleNetwork";
    public static final String PREPARE_TAG = "VunglePrepare";
    public static final String PROTOCOL_TAG = "VungleProtocol";
    public static final String REPORT_TAG = "VungleReport";
    public static final String VUNGLE_TAG = "Vungle";

    public static void m13644v(String tag, String message) {
        m13631a(2, tag, message);
    }

    public static void m13646v(String tag, Throwable throwable) {
        m13633a(2, tag, throwable);
    }

    public static void m13645v(String tag, String message, Throwable throwable) {
        m13632a(2, tag, message, throwable);
    }

    public static void m13635d(String tag, String message) {
        m13631a(3, tag, message);
    }

    public static void m13637d(String tag, Throwable throwable) {
        m13633a(3, tag, throwable);
    }

    public static void m13636d(String tag, String message, Throwable throwable) {
        m13632a(3, tag, message, throwable);
    }

    public static void m13641i(String tag, String message) {
        m13631a(4, tag, message);
    }

    public static void m13643i(String tag, Throwable throwable) {
        m13633a(4, tag, throwable);
    }

    public static void m13642i(String tag, String message, Throwable throwable) {
        m13632a(4, tag, message, throwable);
    }

    public static void m13647w(String tag, String message) {
        m13631a(5, tag, message);
    }

    public static void m13649w(String tag, Throwable throwable) {
        m13633a(5, tag, throwable);
    }

    public static void m13648w(String tag, String message, Throwable throwable) {
        m13632a(5, tag, message, throwable);
    }

    public static void m13638e(String tag, String message) {
        m13631a(6, tag, message);
    }

    public static void m13640e(String tag, Throwable throwable) {
        m13633a(6, tag, throwable);
    }

    public static void m13639e(String tag, String message, Throwable throwable) {
        m13632a(6, tag, message, throwable);
    }

    static void m13631a(int i, String str, String str2) {
        m13632a(i, str, str2, null);
    }

    static void m13633a(int i, String str, Throwable th) {
        m13632a(i, str, null, th);
    }

    static void m13632a(int i, String str, String str2, Throwable th) {
        Object obj = 1;
        boolean a = m13634a("VungleDebug", 3);
        if ((!a && i >= 5) || (a && isLoggable(i))) {
            Object obj2 = !TextUtils.isEmpty(str2) ? 1 : null;
            if (th == null) {
                obj = null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (obj2 != null) {
                stringBuilder.append(str2);
            }
            if (!(obj2 == null || obj == null)) {
                stringBuilder.append("\n");
            }
            if (obj != null) {
                stringBuilder.append(Log.getStackTraceString(th));
            }
            Log.println(i, str, stringBuilder.toString());
        }
    }

    public static boolean isLoggable(int level) {
        return Log.isLoggable("Vungle", level);
    }

    static boolean m13634a(String str, int i) {
        return Log.isLoggable(str, i);
    }

    private Logger() {
    }
}
