package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.Random;

class OneSignalChromeTab {
    private static boolean opened;

    private static class OneSignalCustomTabsServiceConnection extends CustomTabsServiceConnection {
        private Context mContext;
        private String mParams;

        class C39031 extends CustomTabsCallback {
            C39031() {
            }

            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);
            }

            public void extraCallback(String callbackName, Bundle args) {
                super.extraCallback(callbackName, args);
            }
        }

        OneSignalCustomTabsServiceConnection(Context context, String params) {
            this.mContext = context;
            this.mParams = params;
        }

        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            if (customTabsClient != null) {
                customTabsClient.warmup(0);
                CustomTabsSession session = customTabsClient.newSession(new C39031());
                if (session != null) {
                    session.mayLaunchUrl(Uri.parse("https://onesignal.com/android_frame.html" + this.mParams), null, null);
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }

    OneSignalChromeTab() {
    }

    static void setup(Context context, String appId, String userId, String adId) {
        if (VERSION.SDK_INT >= 14 && !opened && !OneSignal.mEnterp && userId != null && adId != null && !adId.equals("OptedOut")) {
            try {
                Class.forName("android.support.customtabs.CustomTabsServiceConnection");
                opened = CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", new OneSignalCustomTabsServiceConnection(context, "?app_id=" + appId + "&user_id=" + userId + "&ad_id=" + adId + "&cbs_id=" + new Random().nextInt(Integer.MAX_VALUE)));
            } catch (ClassNotFoundException e) {
            }
        }
    }
}
