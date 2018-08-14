package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.reflect.Method;

class bf implements bb<WebAdTracker> {
    private static final C3378a<Method> f8586a;

    static {
        C3378a a = C3378a.m11558a();
        try {
            a = C3378a.m11559a(WebAdTracker.class.getMethod("track", new Class[0]));
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
        f8586a = a;
    }

    bf() {
    }

    public Class<WebAdTracker> mo6471a() {
        return WebAdTracker.class;
    }
}
