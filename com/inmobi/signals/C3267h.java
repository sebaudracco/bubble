package com.inmobi.signals;

import android.content.Context;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.google.android.gms.common.GoogleApiAvailability;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* compiled from: GoogleApiClientWrapper */
public class C3267h {
    private static final String f8218a = C3267h.class.getSimpleName();

    public static boolean m10943a() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(b) == 0) {
                return true;
            }
            return false;
        } catch (Error e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8218a, "Google Play Services is not installed!");
            return false;
        } catch (Exception e2) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                hashMap.put("message", e2.getMessage() + "");
                C3135c.m10255a().m10280a("signals", "ExceptionCaught", hashMap);
                return false;
            } catch (Exception e22) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8218a, "Error in submitting telemetry event : (" + e22.getMessage() + ")");
                return false;
            }
        }
    }

    public static Object m10941a(Context context, InvocationHandler invocationHandler, InvocationHandler invocationHandler2, String str) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8218a, "Build object for GoogleApiClient");
        Object obj = null;
        Object obj2 = null;
        try {
            Class cls;
            Class cls2 = null;
            Class[] declaredClasses = Class.forName("com.google.android.gms.common.api.GoogleApiClient").getDeclaredClasses();
            int length = declaredClasses.length;
            int i = 0;
            while (i < length) {
                Object obj3;
                cls = declaredClasses[i];
                if (cls.getSimpleName().equalsIgnoreCase("ConnectionCallbacks")) {
                    Class cls3 = cls2;
                    obj3 = obj2;
                    obj2 = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
                    cls = cls3;
                } else if (cls.getSimpleName().equalsIgnoreCase("OnConnectionFailedListener")) {
                    Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler2);
                    obj2 = obj;
                    Object obj4 = newProxyInstance;
                    cls = cls2;
                    obj3 = obj4;
                } else if (cls.getSimpleName().equalsIgnoreCase("Builder")) {
                    obj3 = obj2;
                    obj2 = obj;
                } else {
                    cls = cls2;
                    obj3 = obj2;
                    obj2 = obj;
                }
                i++;
                obj = obj2;
                obj2 = obj3;
                cls2 = cls;
            }
            if (cls2 != null) {
                Object newInstance = cls2.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                cls = Class.forName("com.google.android.gms.common.api.Api");
                Class cls4 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks");
                Class cls5 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener");
                Method method = cls2.getMethod("addApi", new Class[]{cls});
                Method method2 = cls2.getMethod("addConnectionCallbacks", new Class[]{cls4});
                Method method3 = cls2.getMethod("addOnConnectionFailedListener", new Class[]{cls5});
                Method method4 = cls2.getMethod(C1403a.f2088r, (Class[]) null);
                Field declaredField = Class.forName(str).getDeclaredField("API");
                method.invoke(newInstance, new Object[]{declaredField.get(null)});
                method2.invoke(newInstance, new Object[]{obj});
                method3.invoke(newInstance, new Object[]{obj2});
                return method4.invoke(newInstance, (Object[]) null);
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e);
        } catch (Throwable e2) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e2);
        } catch (Throwable e22) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e22);
        } catch (Throwable e222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e222);
        } catch (Throwable e2222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e2222);
        } catch (Throwable e22222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e22222);
        } catch (Throwable e222222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Cannot build Google API client object", e222222);
        }
        return null;
    }

    public static void m10942a(Object obj) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8218a, "Connecting Google API client.");
        if (obj != null) {
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient").getMethod("connect", (Class[]) null).invoke(obj, (Object[]) null);
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call connect on GoogleApiClient", e);
            } catch (Throwable e2) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call connect on GoogleApiClient", e2);
            } catch (Throwable e22) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call connect on GoogleApiClient", e22);
            } catch (Throwable e222) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call connect on GoogleApiClient", e222);
            }
        }
    }

    public static void m10944b(Object obj) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8218a, "Disconnecting Google API client.");
        if (obj != null) {
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient").getMethod("disconnect", (Class[]) null).invoke(obj, (Object[]) null);
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call disconnect on GoogleApiClient", e);
            } catch (Throwable e2) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call disconnect on GoogleApiClient", e2);
            } catch (Throwable e22) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call disconnect on GoogleApiClient", e22);
            } catch (Throwable e222) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8218a, "Failed to call disconnect on GoogleApiClient", e222);
            }
        }
    }
}
