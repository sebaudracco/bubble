package com.elephant.data;

import android.app.Application;
import android.content.Context;
import com.elephant.data.p035a.C1723d;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.p050b.C1813b;

public final class ElephantLib {
    public static final String SDK_NAME = C1814b.gg;
    public static final int SDK_VERSIONCODE = 226;
    public static final String SDK_VERSIONNAME = (SDK_NAME + C1814b.gh);

    public static void grantConsent(Context context) {
        C1813b.m5257a(context, true);
    }

    public static void init(Application application, String str) {
        C1723d.m4976a(application.getApplicationContext()).m4977a(str, "");
    }

    public static void init(Application application, String str, String str2) {
        C1723d.m4976a(application.getApplicationContext()).m4977a(str, str2);
    }

    public static void revokeConsent(Context context) {
        C1813b.m5257a(context, false);
    }
}
