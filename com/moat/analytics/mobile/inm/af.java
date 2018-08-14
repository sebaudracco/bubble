package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.reflect.Method;
import java.util.Map;

class af implements bb<NativeDisplayTracker> {
    private static final C3378a<Method> f8519a;
    private static final C3378a<Method> f8520b;

    static {
        C3378a a;
        Exception e;
        C3378a a2 = C3378a.m11558a();
        C3378a a3 = C3378a.m11558a();
        try {
            Method method = NativeDisplayTracker.class.getMethod("track", new Class[]{Map.class});
            Method method2 = NativeDisplayTracker.class.getMethod("stopTracking", new Class[0]);
            a = C3378a.m11559a(method);
            try {
                a3 = C3378a.m11559a(method2);
            } catch (NoSuchMethodException e2) {
                e = e2;
                C3376a.m11557a(e);
                f8519a = a;
                f8520b = a3;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a2;
            e = exception;
            C3376a.m11557a(e);
            f8519a = a;
            f8520b = a3;
        }
        f8519a = a;
        f8520b = a3;
    }

    af() {
    }

    public Class<NativeDisplayTracker> mo6471a() {
        return NativeDisplayTracker.class;
    }
}
