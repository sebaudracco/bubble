package com.fyber.cache.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.fyber.cache.CacheVideoDownloadService;
import com.fyber.utils.FyberLogger;
import com.mobfox.sdk.services.MobFoxService;

public class ConfigurationBroadcastReceiver extends BroadcastReceiver {
    private static AlarmManager f6393a;

    public void onReceive(Context context, Intent intent) {
        try {
            if (f6393a == null) {
                f6393a = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            }
            PendingIntent service = PendingIntent.getService(context, 0, new Intent(context, CacheVideoDownloadService.class), 134217728);
            int intExtra = intent.getIntExtra("refresh.interval", 3600);
            int i = intExtra * 1000;
            if (i <= 0) {
                i = MobFoxService.INTERVAL_TIME_TO_SEND;
            }
            FyberLogger.m8451i("ConfigurationBroadcastReceiver", "Next precache query will occur in " + intExtra + " seconds");
            f6393a.cancel(service);
            f6393a.set(3, SystemClock.elapsedRealtime() + ((long) i), service);
        } catch (Throwable th) {
            FyberLogger.m8449e("ConfigurationBroadcastReceiver", "There was an error scheduling the next video cache refresh - " + th.getMessage());
        }
    }
}
