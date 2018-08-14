package com.facebook.ads.internal.p056q.p057a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class C2113e {
    public static volatile C2112a f5020a = C2112a.NOT_INITIALIZED;
    private static int f5021b = -1;

    enum C2112a {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    @VisibleForTesting(otherwise = 2)
    public static int m6774a(XmlPullParser xmlPullParser) {
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals("uses-sdk")) {
                for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                    if (xmlPullParser.getAttributeName(i).equals("minSdkVersion")) {
                        return Integer.parseInt(xmlPullParser.getAttributeValue(i));
                    }
                }
                continue;
            }
        }
        return 0;
    }

    public static void m6775a(Context context) {
        if (!C2113e.m6776a()) {
            C2113e.m6780e(context);
        }
    }

    public static boolean m6776a() {
        return f5020a == C2112a.INITIALIZED;
    }

    public static int m6777b(Context context) {
        if (f5020a == C2112a.NOT_INITIALIZED) {
            C2113e.m6775a(context);
        }
        return f5021b;
    }

    public static int m6778c(Context context) {
        try {
            return C2113e.m6774a(context.getAssets().openXmlResourceParser("AndroidManifest.xml"));
        } catch (XmlPullParserException e) {
            return 0;
        } catch (IOException e2) {
            return 0;
        }
    }

    private static void m6780e(final Context context) {
        if (f5020a == C2112a.NOT_INITIALIZED) {
            f5020a = C2112a.INITIALIZING;
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                public void run() {
                    if (C2113e.f5020a != C2112a.INITIALIZED) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                        int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
                        if (i != -1) {
                            C2113e.f5021b = i;
                            C2113e.f5020a = C2112a.INITIALIZED;
                            return;
                        }
                        i = VERSION.SDK_INT >= 24 ? C2113e.m6781f(context) : C2113e.m6778c(context);
                        C2113e.f5021b = i;
                        sharedPreferences.edit().putInt("AppMinSdkVersion", i).commit();
                        C2113e.f5020a = C2112a.INITIALIZED;
                    }
                }
            });
        }
    }

    private static int m6781f(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        } catch (NameNotFoundException e) {
            return i;
        }
    }
}
