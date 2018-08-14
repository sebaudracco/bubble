package com.cuebiq.cuebiqsdk.utils;

import android.util.Log;
import com.cuebiq.cuebiqsdk.api.ApiConfiguration;
import com.cuebiq.cuebiqsdk.api.Environment;
import com.google.android.gms.wearable.WearableStatusCodes;

public class Logger {
    private static final String PREFIX = "[CuebiqSDK]";

    public static void error(String str) {
        if (str.length() > WearableStatusCodes.TARGET_NODE_NOT_CONNECTED) {
            Log.e(PREFIX, str.substring(0, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
            error(str.substring(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
            return;
        }
        Log.e(PREFIX, str);
    }

    public static void log(String str) {
        if (ApiConfiguration.workingEnvironment != Environment.PRODUCTION) {
            if (str.length() > WearableStatusCodes.TARGET_NODE_NOT_CONNECTED) {
                Log.i(PREFIX, str.substring(0, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
                log(str.substring(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
                return;
            }
            Log.i(PREFIX, str);
        }
    }
}
