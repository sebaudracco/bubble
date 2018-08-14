package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.reflect.Method;
import java.util.Map;

class ah implements bb<NativeVideoTracker> {
    private static final C3378a<Method> f8539a;
    private static final C3378a<Method> f8540b;
    private static final C3378a<Method> f8541c;
    private static final C3378a<Method> f8542d;
    private static final C3378a<Method> f8543e;

    static {
        C3378a a;
        Exception e;
        C3378a a2 = C3378a.m11558a();
        C3378a a3 = C3378a.m11558a();
        C3378a a4 = C3378a.m11558a();
        C3378a a5 = C3378a.m11558a();
        C3378a a6 = C3378a.m11558a();
        try {
            Class cls = NativeVideoTracker.class;
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, MediaPlayer.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            Method method5 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            a2 = C3378a.m11559a(method);
            a3 = C3378a.m11559a(method2);
            a4 = C3378a.m11559a(method3);
            a = C3378a.m11559a(method4);
            try {
                a5 = C3378a.m11559a(method5);
            } catch (NoSuchMethodException e2) {
                e = e2;
                C3376a.m11557a(e);
                f8539a = a2;
                f8540b = a3;
                f8541c = a4;
                f8542d = a;
                f8543e = a5;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a6;
            e = exception;
            C3376a.m11557a(e);
            f8539a = a2;
            f8540b = a3;
            f8541c = a4;
            f8542d = a;
            f8543e = a5;
        }
        f8539a = a2;
        f8540b = a3;
        f8541c = a4;
        f8542d = a;
        f8543e = a5;
    }

    ah() {
    }

    public Class<NativeVideoTracker> mo6471a() {
        return NativeVideoTracker.class;
    }
}
