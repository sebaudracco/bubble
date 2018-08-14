package com.inmobi.commons.p112a;

import android.content.Context;
import com.inmobi.commons.core.p116c.C3116c;

/* compiled from: SdkInfo */
public class C3106b {
    public static String m10092a() {
        String str = "pr-SAND";
        char[] toCharArray = C3106b.m10098c().toCharArray();
        str = "";
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == '.') {
                str = str + "T";
            } else {
                str = str + ((char) ((toCharArray[i] - 48) + 65));
            }
        }
        return "pr-SAND-" + str + "-" + C3106b.m10101f();
    }

    public static int m10096b() {
        return 14;
    }

    public static String m10098c() {
        return "6.2.4";
    }

    public static String m10099d() {
        return "2.0";
    }

    public static String m10100e() {
        return "android";
    }

    public static String m10101f() {
        return "20170717";
    }

    public static String m10102g() {
        return "http://www.inmobi.com/products/sdk/#downloads";
    }

    public static String m10093a(Context context) {
        return C3116c.m10141a(context, "sdk_version_store").m10150b("sdk_version", null);
    }

    public static void m10094a(Context context, String str) {
        C3116c.m10141a(context, "sdk_version_store").m10146a("sdk_version", str);
    }

    public static boolean m10097b(Context context) {
        return C3116c.m10141a(context, "sdk_version_store").m10151b("db_deletion_failed", false);
    }

    public static void m10095a(Context context, boolean z) {
        C3116c.m10141a(context, "sdk_version_store").m10147a("db_deletion_failed", z);
    }
}
