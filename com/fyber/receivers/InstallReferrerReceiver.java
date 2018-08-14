package com.fyber.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import com.fyber.reporters.p109a.C2585a;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;

public class InstallReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            String str = "";
            Bundle extras = intent.getExtras();
            if (extras != null) {
                str = extras.getString("referrer", str);
            }
            FyberLogger.m8451i("InstallReferrerReceiver", "Received install referrer. Persisting data. Package name: " + context.getPackageName() + ". Install referrer: " + str);
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.parseQuery(str);
            String value = urlQuerySanitizer.getValue("utm_content");
            FyberLogger.m8451i("InstallReferrerReceiver", "SubID extracted from received referrer: " + value);
            if (StringUtils.notNullNorEmpty(value)) {
                C2585a c2585a = new C2585a(context);
                c2585a.m8268d(str);
                c2585a.m8267c(value);
                return;
            }
            FyberLogger.m8451i("InstallReferrerReceiver", "The SubID was null or empty, not storing it...");
        }
    }
}
