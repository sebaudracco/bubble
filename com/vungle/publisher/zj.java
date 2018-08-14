package com.vungle.publisher;

import android.content.Context;
import android.os.Build.VERSION;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;

/* compiled from: vungle */
public class zj {
    public static boolean m14204a(Context context) {
        return VERSION.SDK_INT >= 19 || context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean m14205b(Context context) {
        return m14207d(context).length == 0;
    }

    public static boolean m14206c(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public static String[] m14207d(Context context) {
        Iterable arrayList = new ArrayList();
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            arrayList.add("android.permission.INTERNET");
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        }
        if (!m14204a(context)) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (arrayList.size() > 0) {
            Logger.m13638e("Vungle", "Make sure to add <uses-permission> for \"" + zk.m14211a(", ", arrayList) + "\" in your AndroidManifest.xml? AND request if revoked in the runtime, which might be possible on Android Marshmallow (API 23) and above.");
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
