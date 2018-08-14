package com.inmobi.commons.core.utilities;

import android.util.Log;
import com.google.android.gms.wearable.WearableStatusCodes;

public final class Logger {
    private static InternalLogLevel f7760a = ("row".equalsIgnoreCase("staging") ? InternalLogLevel.INTERNAL : InternalLogLevel.NONE);

    public enum InternalLogLevel {
        NONE,
        ERROR,
        DEBUG,
        INTERNAL
    }

    public static void m10359a(InternalLogLevel internalLogLevel, String str, String str2) {
        if (internalLogLevel.ordinal() <= f7760a.ordinal()) {
            switch (internalLogLevel) {
                case ERROR:
                    Log.e("[InMobi]", str2);
                    return;
                case DEBUG:
                    Log.d("[InMobi]", str2);
                    return;
                case INTERNAL:
                    if (str2.length() > WearableStatusCodes.TARGET_NODE_NOT_CONNECTED) {
                        m10361a(str, str2);
                        return;
                    } else {
                        Log.d(str, str2);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public static void m10360a(InternalLogLevel internalLogLevel, String str, String str2, Throwable th) {
        if (internalLogLevel.ordinal() <= f7760a.ordinal()) {
            switch (internalLogLevel) {
                case ERROR:
                    Log.e("[InMobi]", str2, th);
                    return;
                case DEBUG:
                    Log.d("[InMobi]", str2, th);
                    return;
                case INTERNAL:
                    Log.d(str, str2, th);
                    return;
                default:
                    return;
            }
        }
    }

    public static void m10361a(String str, String str2) {
        if (str2.length() > WearableStatusCodes.TARGET_NODE_NOT_CONNECTED) {
            Log.d(str, str2.substring(0, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
            m10361a(str, str2.substring(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
            return;
        }
        Log.d(str, str2);
    }

    public static void m10358a(InternalLogLevel internalLogLevel) {
        f7760a = internalLogLevel;
    }
}
