package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.support.annotation.FloatRange;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;

class C3515s {
    private static String f8974a;

    static class C3514a {
        private boolean f8971a = false;
        private String f8972b = "_unknown_";
        private String f8973c = "_unknown_";

        C3514a() {
        }

        private void m11982c() {
            try {
                Context c = C3515s.m11989c();
                if (c != null) {
                    PackageManager packageManager = c.getPackageManager();
                    this.f8973c = c.getPackageName();
                    this.f8972b = packageManager.getApplicationLabel(c.getApplicationInfo()).toString();
                    this.f8971a = true;
                    return;
                }
                C3511p.m11976a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }

        String m11983a() {
            if (this.f8971a) {
                return this.f8972b;
            }
            m11982c();
            return this.f8972b;
        }

        String m11984b() {
            if (this.f8971a) {
                return this.f8973c;
            }
            m11982c();
            return this.f8973c;
        }
    }

    C3515s() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    static double m11985a() {
        try {
            AudioManager audioManager = (AudioManager) C3475a.m11847a().getSystemService("audio");
            return ((double) C3515s.m11991e()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            C3502m.m11942a(e);
            return 0.0d;
        }
    }

    static void m11987a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    try {
                        AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            C3511p.m11976a(3, "Util", (Object) this, "User has limited ad tracking");
                            return;
                        }
                        C3515s.f8974a = advertisingIdInfo.getId();
                        C3511p.m11976a(3, "Util", (Object) this, "Retrieved Advertising ID = " + C3515s.f8974a);
                    } catch (Exception e) {
                        C3502m.m11942a(e);
                    }
                }
            });
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    static String m11988b() {
        return f8974a;
    }

    static Context m11989c() {
        return (Context) ((C3500k) MoatAnalytics.getInstance()).f8944e.get();
    }

    private static int m11991e() {
        try {
            return ((AudioManager) C3475a.m11847a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return 0;
        }
    }
}
