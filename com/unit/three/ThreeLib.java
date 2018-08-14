package com.unit.three;

import android.content.Context;
import com.unit.three.p138b.C4053c;

public final class ThreeLib {
    public static String getUpdateLogAction() {
        return "action_update_log";
    }

    public static int getVersionCode() {
        return 117;
    }

    public static String getVersionName() {
        return "1.1.7";
    }

    public static void init(Context context, String str) {
        C4053c.m12503a().m12514a(context, "load.elephantdata.net", 11113, str);
    }

    public static void onCreate(Context context) {
        C4053c.m12503a();
        C4053c.m12504a(context);
    }

    public static void onDestory(Context context) {
        C4053c.m12503a();
        C4053c.m12507b(context);
    }
}
