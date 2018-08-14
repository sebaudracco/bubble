package com.inmobi.commons.core.utilities;

import android.content.Context;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PermissionUtils */
public class C3156e {
    public static boolean m10410a(Context context, String str, String str2) {
        try {
            if (context.checkCallingOrSelfPermission(str2) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                hashMap.put("message", e.getMessage() + "");
                C3135c.m10255a().m10280a(str, "ExceptionCaught", hashMap);
                return false;
            } catch (Exception e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, "PermissionUtils", "Error in submitting telemetry event : (" + e.getMessage() + ")");
                return false;
            }
        }
    }
}
