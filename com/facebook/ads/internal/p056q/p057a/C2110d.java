package com.facebook.ads.internal.p056q.p057a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.Executor;

public class C2110d {
    public static <P, PR, R> AsyncTask<P, PR, R> m6770a(Executor executor, AsyncTask<P, PR, R> asyncTask, P... pArr) {
        if (VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(executor, pArr);
        } else {
            asyncTask.execute(pArr);
        }
        return asyncTask;
    }

    public static void m6771a() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable th) {
        }
    }

    public static void m6772a(Context context, String str) {
        if (AdInternalSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", str + " (displayed for test ads only)");
        }
    }
}
