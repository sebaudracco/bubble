package com.inmobi.signals.activityrecognition;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3267h;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActivityRecognitionManager extends IntentService {
    private static final String f8171a = ActivityRecognitionManager.class.getSimpleName();
    private static Object f8172b = null;
    private static Object f8173c = null;

    private static class C3250a implements InvocationHandler {
        private C3250a() {
        }

        public void m10863a(Bundle bundle) {
            if (C3105a.m10076a()) {
                PendingIntent service = PendingIntent.getService(C3105a.m10078b(), 0, new Intent(C3105a.m10078b(), ActivityRecognitionManager.class), 134217728);
                try {
                    Field declaredField = Class.forName("com.google.android.gms.location.ActivityRecognition").getDeclaredField("ActivityRecognitionApi");
                    Class cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                    Class.forName("com.google.android.gms.location.ActivityRecognitionApi").getMethod("requestActivityUpdates", new Class[]{cls, Long.TYPE, PendingIntent.class}).invoke(declaredField.get(null), new Object[]{ActivityRecognitionManager.f8173c, Integer.valueOf(1000), service});
                } catch (Throwable e) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f8171a, "Unable to request activity updates from ActivityRecognition client", e);
                } catch (Throwable e2) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f8171a, "Unable to request activity updates from ActivityRecognition client", e2);
                } catch (Throwable e22) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f8171a, "Unable to request activity updates from ActivityRecognition client", e22);
                } catch (Throwable e222) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f8171a, "Unable to request activity updates from ActivityRecognition client", e222);
                } catch (Throwable e2222) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f8171a, "Unable to request activity updates from ActivityRecognition client", e2222);
                }
            }
        }

        public void m10862a(int i) {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str = "onConnected";
            str = "onConnectionSuspended";
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    m10863a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    m10862a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    public ActivityRecognitionManager() {
        super("Activity service");
    }

    static void m10864a() {
        if (C3267h.m10943a() && f8173c == null && C3105a.m10076a()) {
            m10865a(C3105a.m10078b());
        }
    }

    static void m10867b() {
        if (C3267h.m10943a() && f8173c != null) {
            m10871f();
        }
    }

    private static void m10865a(Context context) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8171a, "Connecting activity recognition manager.");
        f8173c = C3267h.m10941a(context, new C3250a(), new C3250a(), "com.google.android.gms.location.ActivityRecognition");
        C3267h.m10942a(f8173c);
    }

    private static void m10871f() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8171a, "Disconnecting activity recognition manager.");
        C3267h.m10944b(f8173c);
        f8172b = null;
        f8173c = null;
    }

    protected void onHandleIntent(Intent intent) {
        if (f8173c != null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8171a, "Got activity recognition intent.");
            m10866a(intent);
        }
    }

    private static void m10866a(Intent intent) {
        try {
            Class cls = Class.forName("com.google.android.gms.location.ActivityRecognitionResult");
            if (((Boolean) cls.getMethod("hasResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent})).booleanValue()) {
                f8172b = cls.getMethod("getMostProbableActivity", (Class[]) null).invoke(cls.getMethod("extractResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent}), (Object[]) null);
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "HandleIntent: Google play services not included. Cannot get current activity.", e);
        } catch (Throwable e2) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "HandleIntent: Google play services not included. Cannot get current activity.", e2);
        } catch (Throwable e22) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "HandleIntent: Google play services not included. Cannot get current activity.", e22);
        } catch (Throwable e222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "HandleIntent: Google play services not included. Cannot get current activity.", e222);
        }
    }

    public static int m10868c() {
        int intValue;
        Throwable e;
        Throwable th;
        String str = "getType";
        if (f8172b == null) {
            return -1;
        }
        try {
            intValue = ((Integer) Class.forName("com.google.android.gms.location.DetectedActivity").getMethod("getType", (Class[]) null).invoke(f8172b, (Object[]) null)).intValue();
            try {
                f8172b = null;
                Logger.m10359a(InternalLogLevel.INTERNAL, f8171a, "Getting detected activity:" + intValue);
                return intValue;
            } catch (ClassNotFoundException e2) {
                e = e2;
            } catch (NoSuchMethodException e3) {
                e = e3;
                Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            } catch (InvocationTargetException e4) {
                e = e4;
                Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            } catch (IllegalAccessException e5) {
                e = e5;
                Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            }
        } catch (Throwable e6) {
            th = e6;
            intValue = -1;
            e = th;
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e62) {
            th = e62;
            intValue = -1;
            e = th;
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e622) {
            th = e622;
            intValue = -1;
            e = th;
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e6222) {
            th = e6222;
            intValue = -1;
            e = th;
            Logger.m10360a(InternalLogLevel.INTERNAL, f8171a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        }
    }
}
